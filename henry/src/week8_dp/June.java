package week8_dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * N개의 돌이 일렬로 나열되어 있는 강가
 * 마지막 돌에 산삼
 *
 * 3가지 점프
 * - 다음돌
 * - 다다음돌 -
 * - 다다다음돌 - 단한번, k만큼 에너지 소비
 */
public class June {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.valueOf(bufferedReader.readLine());
        int[] dp = new int[N];

        List<Jump> jumpList = new ArrayList<>();
        for (int i = 0; i < N - 1; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int smallJump = Integer.valueOf(stringTokenizer.nextToken());
            int bigJump = Integer.valueOf(stringTokenizer.nextToken());
            jumpList.add(new Jump(smallJump, bigJump));
        }
        int veryBigJump = Integer.valueOf(bufferedReader.readLine());

        int answer = Integer.MAX_VALUE;
        // 큰 점프 안하는 경우
        Arrays.fill(dp, Integer.MAX_VALUE/2);
        dp[0] = 0;
        for (int j = 0; j < N-1; j++) {
            dp[j+1] = Math.min(dp[j+1], dp[j] + jumpList.get(j).smallJump);
            if (j + 2 < N) {
                dp[j + 2] = Math.min(dp[j+2], dp[j] + jumpList.get(j).bigJump);
            }
        }
        answer = Math.min(answer, dp[N-1]);

        // 큰 점프 하는 경우
        for (int i = 0; i <=N-4; i++) {
            Arrays.fill(dp, Integer.MAX_VALUE/2);
            dp[0] = 0;
            for (int j = 0; j < N-1; j++) {
                if (i == j) { //다다음돌
                    dp[j+3] = Math.min(dp[j+3], dp[j]+veryBigJump);
                }

                dp[j+1] = Math.min(dp[j+1], dp[j] + jumpList.get(j).smallJump);
                if (j + 2 < N) {
                    dp[j + 2] = Math.min(dp[j+2], dp[j] + jumpList.get(j).bigJump);
                }
            }
            answer = Math.min(answer, dp[N-1]);
        }
        System.out.println(answer);
    }

    public static class Jump{
        int smallJump;
        int bigJump;

        public Jump(int smallJump, int bigJump) {
            this.smallJump = smallJump;
            this.bigJump = bigJump;
        }
    }
}
