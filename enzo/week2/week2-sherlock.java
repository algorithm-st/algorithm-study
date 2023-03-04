import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Sherlock {

    public static void getAnswer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N  = Integer.parseInt(br.readLine());
        int [][] array = new int[N][];
        for(int i =0; i<array.length;i++){
            array[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
        }
        int [] dp = new int[N];
        if(array[0][0]-1<N) dp[array[0][0]-1] = array[0][1];
        for(int i=1;i<N;i++){
            dp[i] = Math.max(dp[i-1],dp[i]);
            if(i+array[i][0]-1<N){
                dp[i+array[i][0]-1] = Math.max(dp[i+array[i][0]-1],dp[i-1]+array[i][1]);
            }
        }
        System.out.println(dp[N-1]);
    }

    public static void main(String args[]) throws Exception{
        getAnswer();
    }
}
