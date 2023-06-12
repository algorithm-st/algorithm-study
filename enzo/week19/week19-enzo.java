import java.util.*;

class Solution {
    public List<Integer>[] edges;
    public int[][] dp;
    public void dfs(int parentNode, int node){
        dp[node][1] = 1;
        dp[node][0] = 0;
        for(int i : edges[node]){
            if(parentNode == i) continue;
            dfs(node,i);
            dp[node][0] += dp[i][1];
            dp[node][1] += Math.min(dp[i][0],dp[i][1]);
        }

    }
    public int solution(int n, int[][] lighthouse) {
        int answer = 0;
        int [] connectCount = new int[n+1];
        edges = new List[n+1];
        dp = new int[n+1][2];
        for(int i=0;i<=n;i++){
            edges[i] = new ArrayList<>();
        }
        for(int [] i : lighthouse){
            edges[i[1]].add(i[0]);
            edges[i[0]].add(i[1]);
        }
        dfs(0,1);
        answer = Math.min(dp[1][0],dp[1][1]);

        return answer;
    }
}