package june.DP.설탕배달;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] dp = new int[N + 1];
        Arrays.fill(dp, 5000);
        dp[3] = 1;
        if (N == 3) {
            System.out.println(1);
        } else if (N == 4) {
            System.out.println(-1);
        } else {
            dp[5] = 1;
            for (int i = 6; i <= N; i++) {
                dp[i] = Math.min(dp[i - 3] + 1, dp[i - 5] + 1);
            }
            if (dp[N] >= 5000) {
                System.out.println(-1);
            } else {
                System.out.println(dp[N]);
            }
        }

    }

}
