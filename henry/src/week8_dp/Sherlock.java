package week8_dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Sherlock {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        // init
        int n = Integer.valueOf(bufferedReader.readLine());
        Consulting[] consultingArr = new Consulting[n + 1];

        for (int i = 0; i < n; i++) {
            int[] input = Arrays.stream(bufferedReader.readLine().split(" ")).mapToInt(s -> Integer.valueOf(s))
                    .toArray();
            int spendTime = input[0];
            int point = input[1];
            consultingArr[i+1] = new Consulting(spendTime, point);
        }
        int[] dp = new int[n + 2];

        for (int i = 1; i <= n; i++) {
            dp[i] = Math.max(dp[i], dp[i - 1]);

            Consulting consulting = consultingArr[i];
            int newPoint = dp[i] + consulting.point;
            if (i + consulting.spendTime <= n+1) {
                dp[i + consulting.spendTime] = Math.max(dp[i + consulting.spendTime], newPoint);
            }
        }
        dp[n + 1] = Math.max(dp[n], dp[n + 1]);
        System.out.println(dp[n+1]);
    }

    static class Consulting{
        int spendTime;
        int point;

        public Consulting(int spendTime, int point) {
            this.spendTime = spendTime;
            this.point = point;
        }
    }
}
