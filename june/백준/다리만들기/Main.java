package june.백준.다리만들기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    static int[][] arr;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int N;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];

        StringTokenizer st;
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int index = 2;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == 1) {
                    bfsColor(i, j, index++);
                }
            }
        }
        for (int k = 2; k < index; k++) {
            LinkedList<int[]> q = new LinkedList<>();
            visited = new boolean[N][N];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (arr[i][j] == k) {
                        for (int l = 0; l < 4; l++) {
                            int nx = i + dx[l];
                            int ny = j + dy[l];
                            if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                                continue;
                            }
                            if (arr[nx][ny] != 0) {
                                continue;
                            }
                            q.add(new int[]{nx, ny});
                            visited[nx][ny] = true;
                        }
                    }
                }
            }
            bfs(q, k);
        }

        System.out.println(min);
    }

    private static void bfs(LinkedList<int[]> q, int color) {
        int count = 0;
        while (!q.isEmpty()) {
            int len = q.size();
            count++;
            for (int i = 0; i < len; i++) {
                int[] now = q.pop();
                for (int j = 0; j < 4; j++) {
                    int nx = now[0] + dx[j];
                    int ny = now[1] + dy[j];

                    if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                        continue;
                    }
                    if (arr[nx][ny] == color || visited[nx][ny]) {
                        continue;
                    }
                    if (arr[nx][ny] != color && arr[nx][ny] != 0) {
                        min = Math.min(min, count);
                    }
                    visited[nx][ny] = true;
                    q.add(new int[]{nx, ny});
                }
            }
        }
    }

    private static void bfsColor(int x, int y, int color) {
        LinkedList<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        arr[x][y] = color;

        while (!q.isEmpty()) {
            int[] now = q.pop();
            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                    continue;
                }
                if (arr[nx][ny] != 1) {
                    continue;
                }

                arr[nx][ny] = color;
                q.add(new int[]{nx, ny});
            }
        }
    }

}
