package sherlock.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sherlock {
    // 이동하는 상하좌우 배열
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static boolean[][] visited;
    static int[][] graph;
    static int M, N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            // 배추밭 가로 길이
            M = Integer.parseInt(st.nextToken());
            // 배추밭 세로 길이
            N = Integer.parseInt(st.nextToken());
            // 심어진 배추 갯수
            int K = Integer.parseInt(st.nextToken());
            // 배열 사이즈 초기화
            graph = new int[M][N];
            visited = new boolean[M][N];

            // 심어져있는 배추 위치 입력받기
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                graph[x][y] = 1;
            }

            // 필요한 배추지렁이 갯수
            int count = 0;
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    if (graph[i][j] == 1 && !visited[i][j]) {
                        count++;
                        dfs(i, j);
                    }
                }
            }
            sb.append(count).append("\n");
        }
        System.out.println(sb);
    }

    private static void dfs(int i, int j) {
        visited[i][j] = true;

        for (int d = 0; d < 4; d++) {
            int nx = i + dx[d];
            int ny = j + dy[d];
            if (inRange(nx, ny) && graph[nx][ny] == 1 && !visited[nx][ny]) {
                visited[nx][ny] = true;
                dfs(nx, ny);
            }
        }
    }

    // 다음칸으로 움직일 nx, ny가 범위안에 있는지 확인
    private static boolean inRange(int nx, int ny) {
        return (0 <= nx && nx < M && 0 <= ny && ny < N);
    }
}
