package june.프로그래머스.숫자타자대회;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Solution2 {

    static int[][] move = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int[][] diagonal = {{-1, -1}, {1, -1}, {-1, 1}, {1, 1}};
    static char[][] board = {{'1', '2', '3'}, {'4', '5', '6'}, {'7', '8', '9'}, {'*', '0', '#'}};
    static int[][] w = new int[10][10];
    static int INF = 10000000;
    static int len;
    static int[] number;
    static int[][][] dp;

    public static void main(String[] args) {
        solution("12345");
    }
    public static int solution(String numbers) {
        len = numbers.length();
        number = new int[len];
        dp = new int[len][10][10];
        for (int i = 0; i < len; i++) {
            number[i] = numbers.charAt(i) - '0';
            for (int j = 0; j < 10; j++) {
                Arrays.fill(dp[i][j], INF);
            }
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                w[i][j] = 100;
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] != '*' && board[i][j] != '#') {
                    BFS(i, j, board[i][j] - '0');
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                System.out.print(w[i][j] + " ");

            }
            System.out.println();

        }
        return getMinTime(0, 4, 6);
    }

    private static int getMinTime(int idx, int left, int right) {
        if (idx == len) {
            return 0;
        }

        if (dp[idx][left][right] == INF) {
            int first = INF;
            int second = INF;

            if (right != number[idx]) {
                first = w[left][number[idx]] + getMinTime(idx + 1, number[idx], right);
            }
            if (left != number[idx]) {
                second = w[right][number[idx]] + getMinTime(idx + 1, left, number[idx]);
            }

            dp[idx][left][right] = Math.min(first, second);
        }

        return dp[idx][left][right];
    }

    private static void BFS(int startR, int startC, int number) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startR, startC, 0});
        w[number][number] = 1;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nextR = now[0] + move[i][0];
                int nextC = now[1] + move[i][1];

                if (nextR < 0 || nextC < 0 || nextC >= 3 || nextR >= 4) {
                    continue;
                }
                if (board[nextR][nextC] == '*' || board[nextR][nextC] == '#') {
                    continue;
                }
                if (w[number][board[nextR][nextC] - '0'] <= now[2] + 2) {
                    continue;
                }
                w[number][board[nextR][nextC] - '0'] = now[2] + 2;
                w[board[nextR][nextC] - '0'][number] = now[2] + 2;
                queue.offer(new int[]{nextR, nextC, now[2] + 2});
            }

            for (int i = 0; i < 4; i++) {
                int nextR = now[0] + diagonal[i][0];
                int nextC = now[1] + diagonal[i][1];

                if (nextR < 0 || nextC < 0 || nextC >= 3 || nextR >= 4) {
                    continue;
                }
                if (board[nextR][nextC] == '*' || board[nextR][nextC] == '#') {
                    continue;
                }
                if (w[number][board[nextR][nextC] - '0'] <= now[2] + 3) {
                    continue;
                }
                w[number][board[nextR][nextC] - '0'] = now[2] + 3;
                w[board[nextR][nextC] - '0'][number] = now[2] + 3;
                queue.offer(new int[]{nextR, nextC, now[2] + 3});
            }


        }
    }
}
