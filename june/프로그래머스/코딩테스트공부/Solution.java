package june.프로그래머스.코딩테스트공부;

public class Solution {

    // dp 문제인 것 같은데 ㅠㅠ 다녀와서 다시 풀어보겠슴다
    public int solution(int alp, int cop, int[][] problems) {
        
        int a = 0;
        int b = 0;
        for(int i = 0; i<problems.length; i++){
            a = Math.max(problems[i][0], a);
            b = Math.max(problems[i][1], b);
        }
        int[][] dp = new int[a+1][b+1];

        
        for(int i = alp+1; i <= a; i++){
            dp[i][cop] = dp[i-1][cop] + 1;
        }
        for(int j = cop+1; j <= b; j++){    
            dp[alp][j] = dp[alp][j-1] + 1;
        }
        for(int i = alp+1; i <= a; i++){
            dp[i][cop] = dp[i-1][cop] + 1;
        }

        for(int i = alp; i<=a; i++){
            for(int j = cop; j<=b; j++){
                System.out.print(dp[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println(dp[a][b]);
        System.out.println(a + " " + b);
        for(int i = alp; i <= a; i++){
            for(int j = cop; j <= b; j++){
                for(int k = 0; k < problems.length; k++){
                    if(problems[k][0] <= i && problems[k][1] <= j){
                        if (i + problems[k][2] > a || j + problems[k][3] > b) {
                            continue;
                        }
                        dp[i+problems[k][2]][j+problems[k][3]] = 
                            Math.min(dp[i+problems[k][2]][cop] + dp[alp][j+problems[k][3]], 
                                     dp[i+problems[k][2]][j+problems[k][3]] + problems[k][4]);
                    }
                }
            }
        }
        
        return dp[a][b];
    }
}