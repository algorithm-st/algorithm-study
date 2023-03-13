import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 반복문 구현이 난해하면 재귀로 푸는 것도 좋은 방법

public class Enzo {


    public static int N;
    public static int INF = 987654321;
    public static int [][] dp;
    public static int [][] table;
    // 0이 채워진 것 이라고 생각
    public static int dfs(int step, int flag){
        if(step == N){
            return flag == (1<<N)-1 ? 0 : INF;
        }
        if(dp[step][flag] !=-1) return dp[step][flag];
        dp[step][flag] = INF;
        for(int i=0;i<N;i++){
            if((flag & (1<<i)) !=0) continue;
            dp[step][flag] = Math.min(dfs(step+1,flag | (1<<i))+table[step][i],dp[step][flag]);
        }
        return dp[step][flag];
    }
    public static void getAnswer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        table = new int[N][];
        dp = new int[N][1<<N];
        for(int i=0;i<N;i++){
            table[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
        }
        for(int i=0;i<N;i++){
            Arrays.fill(dp[i],-1);
        }
        int answer =dfs(0,0);
        System.out.println(answer);
    }

    public static void main(String args[]) throws IOException {
        getAnswer();
    }
}
