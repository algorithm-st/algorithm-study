package youngkwon_ned.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href="https://www.acmicpc.net/problem/12865">평범한 배낭</a>
 */
public class Main {
    private static int N, K;
    private static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 물품
        N = Integer.parseInt(st.nextToken());

        // 최대 무게
        K = Integer.parseInt(st.nextToken());
        map = new int[N + 1][2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            map[i][0] = Integer.parseInt(st.nextToken()); // 무게
            map[i][1] = Integer.parseInt(st.nextToken()); // 가치
        }

        int[][] dp = new int[N + 1][K + 1];

        for (int i = 1; i <= N; i++) { // 물품 갯수
            for (int j = 1; j <= K; j++) { // 최대 무게
                if (j - map[i][0] >= 0) { // 무게 - 물품의 무게가 0보단 큰 경우 배낭에 넣을 수 있음
                    // 이전 물건의 현재 무게에 대한 최대치와 vs (현재물건 가치 + 현재물건을 넣고 남은 무게에 대한 이전 무게의 최대치) 중 큰 값
                    dp[i][j] = Math.max(dp[i - 1][j], map[i][1] + dp[i - 1][j - map[i][0]]);
                } else {
                    // 무게 - 물품의 무게가 0보다 크지 않다면 배낭에 넣을 수 없기 떄문에 이전 물건에 대한 최대치를 현재 물건에 대한 가치의 최대로 넣음
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }

        System.out.println(dp[N][K]);
    }
}
