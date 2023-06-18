package youngkwon_ned.w17;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * <a href="https://www.acmicpc.net/problem/11559">Puyo Puyo</a>
 */
public class Main {

    private static char[][] maps = new char[12][6];
    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};
    private static boolean isFinish = false;
    private static int pop = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 12; i++) {
            String s = br.readLine();
            for (int j = 0; j < 6; j++) {
                maps[i][j] = s.charAt(j);
            }
        }

        while (true) {
            isFinish = false;
            bfs();
            onFloor();
            if (!isFinish) {
                break;
            }
            pop++;
        }
        System.out.println(pop);
    }

    private static void onFloor() {
        for (int i = 0; i < 6; i++) {
            down(i);
        }
    }

    private static void down(int j) {
        Queue<Puyo> puyo = new LinkedList<>();
        int idx = 11;

        /*
         * 뿌요의 위치를 큐에 넣음, 가장 아래에 있는 빈 칸의 인덱스를 구함
         * -> 가장 바닥에 있는 뿌요도 큐에 넣어서 모두 빈 칸으로 만든 뒤
         * 가장 아래부터 큐에 있는 뿌요들을 차례로 채워나감
         */
        for (int i = 11; i >= 0; i--) {
            if (maps[i][j] != '.') {
                puyo.add(new Puyo(i, j, maps[i][j]));
                maps[i][j] = '.';
            }
        }
        // 뿌요를 가장 밑에 있는 빈 칸에 채워나감
        while (!puyo.isEmpty()) {
            Puyo p = puyo.poll();

            char color = p.color;

            maps[idx][j] = color;

            idx--;
        }
    }

    private static void bfs() {
        Queue<Puyo> q = new LinkedList();
        boolean[][] isVisited = new boolean[12][6];
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 6; j++) {
                if (maps[i][j] != '.' && !isVisited[i][j]) {
                    List<int[]> list = new ArrayList<>();

                    q.add(new Puyo(i, j, maps[i][j]));
                    list.add(new int[]{i, j});
                    isVisited[i][j] = true;

                    while (!q.isEmpty()) {
                        Puyo p = q.poll();
                        int currentX = p.x;
                        int currentY = p.y;
                        char currentColor = p.color;

                        for (int k = 0; k < 4; k++) {
                            int nx = currentX + dx[k];
                            int ny = currentY + dy[k];
                            if (nx < 0 || ny < 0 || nx >= 12 || ny >= 6) {
                                continue;
                            }
                            if (!isVisited[nx][ny] && maps[nx][ny] == currentColor) {
                                q.add(new Puyo(nx, ny, maps[nx][ny]));
                                list.add(new int[]{nx, ny});
                                isVisited[nx][ny] = true;
                            }
                        }
                    }

                    if (list.size() >= 4) {
                        for (int k = 0; k < list.size(); k++) {
                            int x = list.get(k)[0];
                            int y = list.get(k)[1];

                            maps[x][y] = '.';
                            isFinish = true;
                        }
                    }
                }
            }
        }

    }

    private static class Puyo {
        int x, y;
        char color;

        Puyo(int x, int y, char color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }
    }
}
