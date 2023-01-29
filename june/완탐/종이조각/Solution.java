package june.완탐.종이조각;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
//2 3
//123
//312
    static int N;
    static int M;
    static int[][] arr;
    static boolean[][] visited;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String[] s = bufferedReader.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        M = Integer.parseInt(s[1]);
        arr = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String line = bufferedReader.readLine();
            for (int j = 0; j < M; j++) {
                arr[i][j] = line.charAt(j) - '0';
            }
        }

        dfs(0, 0);
        System.out.println(max);
    }

    private static void dfs(int x, int y) {
        // 탐색 종료
        if (x >= N) {
            sum();
            return;
        }

        // 하나의 행의 체크 종료 -> 다음 행으로 이동
        if (y >= M) {
            dfs(x + 1, 0);
            return;
        }

        visited[x][y] = true;
        dfs(x, y + 1);
        visited[x][y] = false;
        dfs(x, y + 1);
    }

    private static void sum() {
        int total = 0;
        int prev = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j]) {
                    prev *= 10;
                    prev += arr[i][j];
                } else {
                    total += prev;
                    prev = 0;
                }
            }
            total += prev;
            prev = 0;
        }

        for (int j = 0; j < M; j++) {
            for (int i = 0; i < N; i++) {
                if (!visited[i][j]) {
                    prev *= 10;
                    prev += arr[i][j];
                } else {
                    total += prev;
                    prev = 0;
                }
            }
            total += prev;
            prev = 0;
        }
        max = Math.max(total, max);
    }

}
