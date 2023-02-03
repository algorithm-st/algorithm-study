public class Han {
    public int mid = 1000;
    public int N = 0;
    public int targetNum  = mid;
    public int[][] dp = new int[21][2001];
    public int dfs(int step, int curNum, int[] numbers){
        if(step == N){
            if(curNum == targetNum) dp[step][curNum] =1;
            return dp[step][curNum];
        }
        if(dp[step][curNum]!=0) return dp[step][curNum];
        dp[step][curNum] = dfs(step+1,curNum-numbers[step],numbers) + dfs(step+1,curNum+numbers[step],numbers);
        return dp[step][curNum];
    }

    public int solution(int[] numbers, int target) {
        int answer = 0;
        N = numbers.length;
        targetNum += target;
        answer = dfs(0,mid,numbers);
        return answer;
    }
}
