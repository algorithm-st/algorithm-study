
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Ned {

    public static void getAnswer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long [] dp = new long[K+1];
        int [][] table = new int[N][];
        for(int i=0;i<table.length;i++){
            table[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
        }
        for(int i=0;i<N;i++){
            for(int j=K;j>=0;j--){
                if(j+table[i][0]<=K){
                    dp[j+table[i][0]] = Math.max(dp[j+table[i][0]],dp[j]+table[i][1]);
                }
            }
        }
        System.out.println(dp[K]);

    }
    public static void main(String [] args) throws IOException {
        getAnswer();
    }
}
