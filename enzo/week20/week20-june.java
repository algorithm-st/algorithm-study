public class June {
    public int solution(int [][]board)
    {
        int n = board.length;
        int m = board[0].length;

        int [][] dp = new int[n][m];
        int answer =0;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if((i == 0 || j == 0) && board[i][j] == 1){
                    dp[i][j] = 1;
                }
                else if(board[i][j] == 1){
                    int minNum = Math.min(dp[i-1][j-1], Math.min(dp[i-1][j],dp[i][j-1]));
                    dp[i][j] = minNum+1;
                }
                answer = Math.max(answer,dp[i][j]*dp[i][j]);
            }
        }
        return answer;
    }
}
