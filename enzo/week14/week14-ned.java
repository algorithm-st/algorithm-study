import java.util.*;

class Solution {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int [][] arr = new int[n+1][n+1];
        for(int i=1;i<=n;i++){
            for(int j=1; j<=n;j++){
                if(i == j ) arr[i][j] = 0;
                else arr[i][j] = Integer.MAX_VALUE/4;
            }
        }
        for(int [] fare : fares){
            arr[fare[0]][fare[1]] = fare[2];
            arr[fare[1]][fare[0]] = fare[2];
        }
        for(int k=1; k<=n;k++){
            for(int i=1;i<=n;i++){
                for(int j=1;j<=n;j++){
                    arr[i][j] = Math.min(arr[i][j], arr[i][k] + arr[k][j]);
                }
            }
        }
        int answer = arr[s][a] + arr[s][b];
        for(int i=1;i<=n;i++){
            answer = Math.min(answer, arr[s][i] + arr[i][a] + arr[i][b]);
        }

        return answer;
    }
}