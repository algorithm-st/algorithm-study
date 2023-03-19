import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class June {


    public static void getAnswer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int [][] dp = new int[2][N+1];
        Arrays.fill(dp[0],Integer.MAX_VALUE/2);
        Arrays.fill(dp[1],Integer.MAX_VALUE/2);
        int [][] table = new int[N][];
        for(int i=0;i<N-1;i++){
            table[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
        }
        int K = Integer.parseInt(br.readLine());
        dp[1][1] = 0;
        for(int i=0;i<N-1;i++){
            if(i+2<=N){
                dp[1][i+2] = Math.min(dp[1][i+2],dp[1][i+1] + table[i][0]);
                dp[0][i+2] = Math.min(dp[0][i+2],dp[0][i+1] + table[i][0]);
            }
            if(i+3<=N){
                dp[1][i+3] = Math.min(dp[1][i+3],dp[1][i+1] + table[i][1]);
                dp[0][i+3] = Math.min(dp[0][i+3],dp[0][i+1] + table[i][1]);
            }
            if(i+4<=N){
                dp[0][i+4] = Math.min(dp[0][i+4],dp[1][i+1] + K);
            }
        }
        System.out.println(Math.min(dp[0][N],dp[1][N]));
    }
    public static void main(String []args) throws IOException {
        getAnswer();
    }
}
