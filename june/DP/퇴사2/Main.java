package june.DP.퇴사2;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] arr = new int[N + 2][2];
        int[] dp = new int[N + 2];

        for (int i = 1; i <= N; i++) {
            int a = Integer.parseInt(sc.next());
            int b = Integer.parseInt(sc.next());
            arr[i][0] = a;
            arr[i][1] = b;
        }
//        for (int i = 0; i < N; i++) {
//            if (i + arr[i+1][0] <= N) {
//                dp[i + arr[i+1][0]] = Math.max(dp[i] + arr[i+1][1], dp[i + arr[i+1][0]]);
//            }
//        }

        int max = -1;
        for(int i=1; i<N+2; i++) {
            if(max < dp[i]) {
                max = dp[i];
            }

            int nxt = i +arr[i][0];
            if(nxt < N+2) {
                dp[nxt] = Math.max(dp[nxt], max+arr[i][1]);
            }
        }
        System.out.println(dp[N+1]);
    }

}
