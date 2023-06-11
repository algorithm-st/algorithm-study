package june.프로그래머스.보행자천국;

public class Solution {
    int MOD = 20170805;
    int[][] sol;
    public int solution(int m, int n, int[][] cityMap) {
        int answer = 0;
        sol = new int[m][n];
        for(int i = 0; i<m; i++){
            if(cityMap[i][0] == 0 || cityMap[i][0] == 2){
                sol[i][0] = 1;    
            }else{
                break;
            }
        }
        for(int i = 0; i<n; i++){
            if(cityMap[0][i] == 0 || cityMap[0][i] == 2){
                sol[0][i] = 1;    
            }else{
                break;
            }
        }
        for(int i = 1; i< m; i++){
            for(int j =1; j<n; j++){
                int prevX = i-1;
                int prevY = j-1;
                
                int xv = 0;
                int yv = 0;
                
                while(cityMap[i][prevY] == 2){
                    if (prevY == 0) {
                        break;
                    }
                    prevY--;
                }
                
                while(cityMap[prevX][j] == 2){
                    if (prevX == 0) {
                        break;
                    }
                    prevX--;
                }
                
                if(cityMap[i][prevY] == 0){
                    xv = sol[i][prevY];
                }
                if(cityMap[prevX][j] == 0){
                    yv = sol[prevX][j];
                }
                
                sol[i][j] = (xv + yv) % MOD;
            }
        }
        
        return sol[m-1][n-1];
    }
}