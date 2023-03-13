package week8_dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Enzo {
    static int N = 0;
    static int[][] D;
    static int[][] dp;


    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.valueOf(bufferedReader.readLine());
        D = new int[N][N];
        for (int i = 0; i < N; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < N; j++) {
                D[i][j] = Integer.valueOf(stringTokenizer.nextToken());
            }
        }

        dp = new int[N+1][1 << N];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(dp[i], -1);
        }

        int answer = dfs(0, 0);
        System.out.println(answer);
    }

    static int dfs(int worker, int visited) {
        if (visited == ((1 << N) - 1)) { // 모든 작업 처리
            return 0;
        }
        if (dp[worker][visited] != -1) { // 이미 방문한 조합
            return dp[worker][visited];
        }

        dp[worker][visited] = Integer.MAX_VALUE/2;
        for (int work = 0; work < N; work++) {
            if (((visited >> work) & 0x1) == 0x1) { // 이미 처리한 작업
                continue;
            }

            dp[worker][visited] = Math.min(dp[worker][visited],
                    dfs(worker+1, visited | 0x1 << work ) + D[worker][work]);
        }
        return dp[worker][visited];
    }
}
