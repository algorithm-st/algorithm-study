package june.DP.함께블록쌓기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");


        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        int[][] height = new int[N + 1][M];
        int[][] dp = new int[N + 1][H + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int j = 0;
            while (st.hasMoreTokens()) {
                height[i][j++] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i=0; i<=N; i++){
            dp[i][0]=1;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= H; j++) {
                for (int h : height[i]) {
                    if (j >= h) {
                        dp[i][j] += dp[i - 1][j - h];
                        dp[i][j] %= 10007;
                    }
                }
                dp[i][j] += dp[i-1][j];
                dp[i][j] %=10007;
            }
        }
        System.out.println(dp[N][H]);
    }

}
