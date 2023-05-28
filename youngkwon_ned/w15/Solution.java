package youngkwon_ned.w15;

import java.util.Arrays;

/**
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/72413">합승 택시 요금</a>
 */
class Solution {
    static final int MAX = 20000001; // 200 * 100000 + 1
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] dp = new int[n + 1][n + 1];

        // 세팅
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], MAX);
            dp[i][i] = 0;
        }

        // 경로 마다 비용 2차원 배열에 저장
        for(int i = 0; i < fares.length; i++) {
            int start = fares[i][0];
            int end = fares[i][1];
            int cost = fares[i][2];

            dp[start][end] = cost;
            dp[end][start] = cost;
        }

        // i -> j 로 가는 최단거리 비용 찾기
        for(int k = 1; k < n+1; k++) {
            for(int i = 1; i < n+1; i++) {
                for(int j = 1; j < n+1; j++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
            }
        }

        // 시작점에서 각자 a,b 로 가는 경우의 합
        int answer = dp[s][a] + dp[s][b];

        // 경유지점을 끼는경우
        // s -> t t -> a + t -> b
        for(int i = 1; i <= n; i++) {
            answer = Math.min(answer, dp[s][i] + dp[i][a] +dp[i][b]);
        }
        return answer;
    }
}
