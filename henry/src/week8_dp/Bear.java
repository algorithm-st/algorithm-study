package week8_dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bear {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        Integer N = Integer.valueOf(bufferedReader.readLine());
        int[] dp = new int[1001];

        // 점화식
        // dp[i] = dp[i-1](이전거에 세로 벽돌 추가) + dp[i-1](이전 거에서 마지막이 세로 벽돌인거에 가로 2줄 추가)
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= N; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2])%10007;
        }

        System.out.println(dp[N]);
    }
}
