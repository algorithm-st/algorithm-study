package june.백준.PuyoPuyo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {

    //......
//......
//......
//......
//......
//......
//......
//......
//.Y....
//.YG...
//RRYG..
//RRYGG.
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static String[][] arr;
    static boolean flag = true;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        arr = new String[12][6];
        for (int i = 0; i < 12; i++) {
            String[] s = br.readLine().split("");

            for (int j = 0; j < 6; j++) {
                arr[i][j] = s[j];
            }
        }
//        boolean[][] visited = new boolean[12][6];
        int answer = 0;
        while (flag) {
            flag = false;
            for (int i = 0; i < 12; i++) {

                for (int j = 0; j < 6; j++) {

                    if (!arr[i][j].equals(".")) {
                        bfs(i, j, arr[i][j]);
                    }
                }
            }
            // 정렬
            for (int i = 0; i < 6; i++) {
                sort(i);
            }
            answer++;
        }

        System.out.println(answer - 1);
    }

    private static void sort(int row) {
        LinkedList<String> q = new LinkedList<>();
        for (int i = 11; i >= 0; i--) {
            if (!arr[i][row].equals(".")) {
                q.add(arr[i][row]);
            }
            arr[i][row] = ".";
        }
        int size = q.size();
        for (int i = 0; i < size; i++) {
            arr[11- i][row] = q.poll();
        }
    }

    private static void bfs(int a, int b, String val) {
        LinkedList<Position> q = new LinkedList<>();
        ArrayList<Position> keep = new ArrayList<>();
        boolean[][] visited = new boolean[12][6];

        q.add(new Position(a,b,val));
        keep.add(new Position(a, b, val));
        visited[a][b] = true;
        while (!q.isEmpty()) {
            Position now = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (nx < 0 || ny < 0 || nx >= 12 || ny >= 6) {
                    continue;
                }
                if (!arr[nx][ny].equals(now.val) || visited[nx][ny]) {
                    continue;
                }
                visited[nx][ny] = true;
                q.add(new Position(nx, ny, val));
                keep.add(new Position(nx, ny, val));
            }
        }
        if (keep.size() >= 4) {
            flag = true;
            for (Position position : keep) {
                arr[position.x][position.y] = ".";
            }
        }
    }

    static class Position {

        int x;
        int y;
        String val;

        public Position(int x, int y, String val) {
            this.x = x;
            this.y = y;
            this.val = val;
        }
    }

}
