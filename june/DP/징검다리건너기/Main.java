package june.DP.징검다리건너기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int[][] stone = new int[24][2];
        int[][] dp = new int[25][2];
        for (int i = 0; i < 25; i++) {
            Arrays.fill(dp[i], 100000);
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            stone[i + 1][0] = Integer.parseInt(st.nextToken());
            stone[i + 1][1] = Integer.parseInt(st.nextToken());
        }
        int K = Integer.parseInt(br.readLine());
        dp[1][0] = 0;
        dp[1][1] = 0;
        dp[2][0] = stone[1][0];
        dp[2][1] = 100000;
        for (int i = 3; i <= N; i++) {
            dp[i][0] = Math.min(dp[i - 1][0] + stone[i - 1][0], dp[i - 2][0] + stone[i - 2][1]);
            if (i > 3) {
                dp[i][1] = Math.min(Math.min(dp[i - 1][1] + stone[i - 1][0], dp[i - 2][1] + stone[i - 2][1]), dp[i - 3][0] + K);
            }
        }
        System.out.println(Math.min(dp[N][0], dp[N][1]));

    }

}
//5
//1 2
//2 3
//4 5
//6 7
//4