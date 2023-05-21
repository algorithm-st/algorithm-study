#include<bits/stdc++.h>
using namespace std;
const int INF = 0x3f3f3f3f;

int dp[201][201]{};

int solution(int n, int s, int a, int b, vector<vector<int>> fares) {
    memset(dp,0x3f,sizeof(dp));
    for(int i=0; i<fares.size(); i++){    
        dp[fares[i][0]][fares[i][1]] = fares[i][2];
        dp[fares[i][1]][fares[i][0]] = fares[i][2];
    }
    
    for(int i=1; i<=n; ++i) dp[i][i] = 0;
    for(int k=1; k<=n; ++k){
        for(int i=1; i<=n; ++i){
            for(int j=1; j<=n; ++j){
                if(dp[i][k] + dp[k][j] < dp[i][j])
                    dp[i][j] = dp[i][k] + dp[k][j];
            }
        }
    }

    int ans = 0x3f3f3f3f;
    for(int i=1; i<=n; i++){
        if(dp[s][i]==INF || dp[i][a]==INF || dp[i][b]==INF) continue;
        int tmp = dp[s][i] + dp[i][a] + dp[i][b];
        ans = min(ans, tmp);
    }
    
    return ans;
}