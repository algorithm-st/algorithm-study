import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Henry {

    public static void getAnswer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());
        int [][] table = new int[N][];
        for(int i=0;i<N;i++){
            table[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
        }
        int[][] dp = new int[N+1][H+1];
        dp[0][0] = 1;
        for(int i=1;i<=N;i++){
            for(int j=0;j<=H;j++){
                dp[i][j] = dp[i-1][j];
                for(int k=0;k<table[i-1].length;k++){
                    if(j-table[i-1][k]<0) continue;
                    dp[i][j] = (dp[i][j] + dp[i-1][j-table[i-1][k]])%10007;
                }
            }
        }
        System.out.println(dp[N][H]);
    }
    public static void main(String[] args) throws IOException {
        getAnswer();
    }
}
