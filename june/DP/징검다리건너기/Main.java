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
        int[][] stone = new int[N][2];
        int[][] dp = new int[N][N];
        Arrays.fill(dp[0], 5000);
        Arrays.fill(dp[1], 5000);

        for (int i = 0; i < N-1; i++) {
            st = new StringTokenizer(br.readLine());
            stone[i][0] = Integer.parseInt(st.nextToken());
            stone[i][1] = Integer.parseInt(st.nextToken());
        }
        int K = Integer.parseInt(br.readLine());

        if (N > 1) {
            dp[0][1] = Math.min(stone[1][0] + dp[0][0], stone[0][1]);
            dp[1][1] = Math.min(stone[1][0] + dp[0][0], stone[0][1]);
        }
        if (N > 2) {
            dp[0][2] = Math.min(stone[2][0] + dp[0][2], stone[1][1] + dp[0][1]);
            dp[1][2] = Math.min(stone[2][0] + dp[1][2], stone[1][1] + dp[1][1]);
            for (int i = 1; i < N-2; i++) {
                dp[0][i + 2] = Math.min(stone[i + 1][0] + dp[0][i + 1], stone[i][1] + dp[0][i]);
                dp[1][i + 2] = Math.min(Math.min(stone[i + 1][0] + dp[0][i + 1], stone[i][1] + dp[0][i]), dp[0][i - 1] + K);
            }
        }

        System.out.println(Math.min(dp[0][N-2], dp[1][N-2]));
    }

}
//5
//1 2
//2 3
//4 5
//6 7
//4