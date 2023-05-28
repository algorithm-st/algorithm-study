package youngkwon_ned.programmers;

import java.util.Arrays;

/**
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/136797">숫자 타자 대회</a>
 */
public class Solution1 {
    // 0~9부터 0~9 까지 각 가중치를 미리 계산
    public int[][] cost = {
            {1, 7, 6, 7, 5, 4, 5, 3, 2, 3},
            {7, 1, 2, 4, 2, 3, 5, 4, 5, 6},
            {6, 2, 1, 2, 3, 2, 3, 5, 4, 5},
            {7, 4, 2, 1, 5, 3, 2, 6, 5, 4},
            {5, 2, 3, 5, 1, 2, 4, 2, 3, 5},
            {4, 3, 2, 3, 2, 1, 2, 3, 2, 3},
            {5, 5, 3, 2, 4, 2, 1, 5, 3, 2},
            {3, 4, 5, 6, 2, 3, 5, 1, 2, 4},
            {2, 5, 4, 5, 3, 2, 3, 2, 1, 2},
            {3, 6, 5, 4, 5, 3, 2, 4, 2, 1}
    };

    public int[][][] dp; //dp[ind][left][right]
    public String arr;
    public int len;

    public static void main(String[] args) {
        Solution1 s = new Solution1();
        int solution = s.solution("1756");
        System.out.println("solution = " + solution);
    }

    public int solution(String numbers) {
        arr = numbers;
        len = numbers.length();
        dp = new int[len + 1][10][10];
        for (int i = 0; i < len + 1; i++) {
            for (int j = 0; j < 10; j++)
                Arrays.fill(dp[i][j], -1);
        }
        return solve(0, 4, 6);
    }

    private int solve(int i, int l, int r) {
        // 재귀 마지막 끝나는 부분
        if (i == len) {
            return 0;
        }
        if (dp[i][l][r] != -1) {
            return dp[i][l][r];
        }
        int number = arr.charAt(i) - '0';
        int result = Integer.MAX_VALUE;

        // 이미 숫자에 오른손이 있는경우가 아니면 왼손으로 움직이는 경우 계산
        if (number != r) {
            result = Math.min(solve(i + 1, number, r) + cost[l][number], result);
        }

        // 이미 숫자에 왼손이 있는경우가 아니면 오른손으로 움직이는 경우 계산
        if (number != l) {
            result = Math.min(solve(i + 1, l, number) + cost[r][number], result);
        }

        // dp 배열 채워서 같은 계산 안하도록
        dp[i][l][r] = result;
        return result;
    }
}
