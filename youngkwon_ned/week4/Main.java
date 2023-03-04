package youngkwon_ned.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * <a href="https://www.acmicpc.net/problem/16926">배열돌리기</a>
 */
public class Main {

    private static int[][] map;
    private static int boxCount;
    private static int n;
    private static int m;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        int r = Integer.parseInt(st.nextToken());

        // 입력
        for (int i = 0; i < map.length; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 돌려야 하는 박스의 갯수
        boxCount = Math.min(m, n) / 2;

        // 배열 돌리기
        for (int i = 0; i < r; i++) {
            turnArray();
        }
        // 출력
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void turnArray() {
        for (int count = 0; count < boxCount; count++) {
            //5 4 / 4 3 / 3 2
            int nMax = n - count - 1;
            int mMax = m - count - 1;

            int tmp = map[count][count];
            // 위쪽 변 : 왼 <- 오
            for (int i = count; i < mMax; i++) {
                map[count][i] = map[count][i + 1];
            }
            // 오른쪽 변 : 아래 <- 위
            for (int i = count; i < nMax; i++) {
                map[i][mMax] = map[i + 1][mMax];
            }
            // 아래쪽 변 : 왼 -> 오
            for (int i = mMax; i > count; i--) {
                map[nMax][i] = map[nMax][i - 1];
            }
            // 왼쪽 변 : 위 -> 아래
            for (int i = nMax; i > count; i--) {
                map[i][count] = map[i - 1][count];
            }
            map[count + 1][count] = tmp;
        }
    }
}
