
import java.util.*;
public class Enzo {
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int [][] array = new int[n+1][n+1];
        for(int i=0;i<fares.length;i++){
            array[fares[i][0]][fares[i][1]] = fares[i][2];
            array[fares[i][1]][fares[i][0]] = fares[i][2];
        }
        for(int i=0;i<=n;i++){
            for(int j=0;j<=n;j++){
                if(i==j) array[i][j] = 0;
                else if(array[i][j] ==0) array[i][j] = Integer.MAX_VALUE/3;
            }
        }
        for(int k=1;k<=n;k++){
            for(int i=1;i<=n;i++){
                for(int j=1;j<=n;j++){
                    array[i][j] = Math.min(array[i][j],array[i][k]+array[k][j]);
                }
            }
        }
        int answer = Integer.MAX_VALUE;
        for(int i=1;i<=n;i++){
            answer = Math.min(answer, array[s][i] + array[i][a]+array[i][b]);
        }
        return answer;
    }
}