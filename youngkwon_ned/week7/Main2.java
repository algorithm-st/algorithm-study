package youngkwon_ned.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href="https://www.acmicpc.net/problem/21317">징검다리 건너기</a>
 */
public class Main2 {
    private static int N, K;
    private static int[][] jump;
    private static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        jump = new int[N][2];

        for (int i = 1; i <= N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            jump[i][0] = Integer.parseInt(st.nextToken()); // i의 작은 점프
            jump[i][1] = Integer.parseInt(st.nextToken()); // i의 큰 점프
        }

        K = Integer.parseInt(br.readLine()); // 매우 큰 점프
        dfs(0, 1, false); // 1번부터 dfs 시작

        System.out.println(answer);
    }

    private static void dfs(int sum, int to, boolean flag) {
        if (N == to) { // 마지막 돌에 도달하는 경우
            answer = Math.min(sum, answer);
            return;
        }

        if (N < to) { // 넘어가는 경우
            return;
        }

        dfs(sum + jump[to][0], to + 1, flag); // 작은 점프 했을 때
        dfs(sum + jump[to][1], to + 2, flag); // 큰 점프 했을 때

        if (!flag) {
            dfs(sum + K, to + 3, true); // 매우 큰 점프 했을 때 (한번만 가능하기 때문에 flag를 true로 설정해준다.)
        }

    }
}
