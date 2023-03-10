import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void getAnswer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long [] dp =  new long[N+1];
        int [][] table = new int[N][];
        for(int i=0; i<N;i++){
            table[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
        }
        for(int i=0;i<N;i++){
            if(i>0){
                dp[i] = Math.max(dp[i],dp[i-1]);
            }
            if(i+table[i][0]>N) continue;
            dp[i+table[i][0]] = Math.max(dp[i+table[i][0]], dp[i]+table[i][1]);
        }
        System.out.println(Math.max(dp[N],dp[N-1]));
    }
    public static void main(String []args) throws IOException {
        getAnswer();
    }
}
