package week19;

/**
 문제 설명
 - 도시 : m x n 크기의 격자 모양 배열 / 0 : 자동차가 통행 가능 / 1: 통행 금지 / 2 : 좌회전, 우회전 금지
 */
public class Ned {
    static class Solution {

        int MOD = 20170805;
        public int solution(int m, int n, int[][] cityMap) {
            int answer = 0;

            int[][][] dp = new int[m][n][2]; // 마지막 0 오른쪽 / 1 아래쪽

            dp[0][1][0] = 1; dp[1][0][1] = 1;

            for(int i=0; i<m ; i++){
                for(int j=0; j<n; j++){
                    // 오른쪽 블럭
                    if(j+1<n && cityMap[i][j+1]!=1 ){
                        dp[i][j+1][0] += dp[i][j][0];
                        // 좌회전
                        if(cityMap[i][j] != 2){
                            dp[i][j+1][0] += dp[i][j][1];
                        }
                        dp[i][j+1][0] %= MOD;
                        dp[i][j+1][1] %= MOD;
                    }


                    // 아래쪽 블럭
                    if(i+1<m && cityMap[i+1][j]!=1){
                        dp[i+1][j][1] += dp[i][j][1];
                        // 우회전
                        if(cityMap[i][j] != 2){
                            dp[i+1][j][1] += dp[i][j][0];
                        }
                        dp[i+1][j][0] %= MOD;
                        dp[i+1][j][1] %= MOD;
                    }
                }
            }

            return (dp[m-1][n-1][0] + dp[m-1][n-1][1])%MOD;
        }
    }
}
