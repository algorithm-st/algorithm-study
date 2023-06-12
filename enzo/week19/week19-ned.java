class Solution {
    int MOD = 20170805;
    public int [] dy = {1,0};
    public int [] dx = {0,1};
    public int [][][] dp;
    public int m_g;
    public int n_g;
    public int dfs(int[][] cityMap, int cury, int curx, int curdir){
        if(cury == m_g-1 && curx == n_g-1){
            dp[cury][curx][curdir] = 1;
            return dp[cury][curx][curdir];
        }
        if(dp[cury][curx][curdir] != -1) return dp[cury][curx][curdir];
        dp[cury][curx][curdir] = 0;
        if(cityMap[cury][curx] == 2 || cityMap[cury][curx] == 0){
            int nextY = cury + dy[curdir];
            int nextX = curx + dx[curdir];
            if(isIn(nextY,nextX)){
                dp[cury][curx][curdir] += dfs(cityMap,nextY,nextX,curdir)%MOD;
            }
        }
        if(cityMap[cury][curx] == 0){
            int odir = (curdir+1)%2;
            int nextY = cury + dy[odir];
            int nextX = curx + dx[odir];
            if(isIn(nextY,nextX)){
                dp[cury][curx][curdir] += dfs(cityMap,nextY,nextX,odir)%MOD;
            }
        }
        return dp[cury][curx][curdir];
    }
    public boolean isIn(int y, int x){
        return y>=0 && y<m_g && x>=0 && x<n_g;
    }
    public int solution(int m, int n, int[][] cityMap) {
        int answer = 0;
        m_g = m;
        n_g = n;
        dp = new int[m][n][2];
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                dp[i][j][0] = -1;
                dp[i][j][1] = -1;
            }
        }
        answer += dfs(cityMap,0,1,1)%MOD;
        answer += dfs(cityMap,1,0,0)%MOD;
        answer = answer%MOD;
        return answer;
    }
}