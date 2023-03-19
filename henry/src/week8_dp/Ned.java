package week8_dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * N - 물건의 개수
 * W - 무게, V - 가치
 * K - 가방에 넣을 수 있는 최대 무게
 *
 *
 */
public class Ned {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int n = Integer.valueOf(stringTokenizer.nextToken());
        int k = Integer.valueOf(stringTokenizer.nextToken());
        int[] weightArray = new int[n];
        int[] valueArray = new int[n];
        for (int i = 0; i < n; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            weightArray[i] = Integer.valueOf(stringTokenizer.nextToken());
            valueArray[i] = Integer.valueOf(stringTokenizer.nextToken());
        }

        int[] dp = new int[k + 1];

        for (int i = 0; i < n; i++) {
            int weight = weightArray[i];
            int value = valueArray[i];

            for (int j = k; j >= weight; j--) {
                dp[j] = Math.max(dp[j], dp[j - weight] + value);
            }
        }

        System.out.println(dp[k]);
    }
}
