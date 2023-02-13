package june.BFS.유기농배추;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

// 왜 이 문제를 선택했나?
//

//문제 푼 데 걸린 시간은?
// 20분

//시간 복잡도는 ? (잘 모르겠다면 예상)
// 어렵다,,,

//문제 풀이 설명
// 배추 위치를 알려줘서 모든 좌표를 다 돌릴 필요없고 배추 위치만 큐에 담아서 DFS
// DFS는 인접한 1인 배추를 모조리 방문처리
// 카운트는 큐에서 꺼낼때 이미 방문했으면 생략하고 한적없으면 카운트
public class Main {
    public static int[][] arr;
    public static boolean[][] visited;
    public static int T;
    public static int M;
    public static int N;
    public static int K;
    public static int count = 0;
    public static LinkedList<int[]> queue = new LinkedList<>();

    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            setting(br);
        }

    }

    private static void setting(BufferedReader br) throws IOException {
        String[] s = br.readLine().split(" ");
        M = Integer.parseInt(s[0]);
        N = Integer.parseInt(s[1]);
        K = Integer.parseInt(s[2]);
        count = 0;
        arr = new int[M][N];
        visited = new boolean[M][N];

        for (int i = 0; i < K; i++) {
            String[] s2 = br.readLine().split(" ");
            int x = Integer.parseInt(s2[0]);
            int y = Integer.parseInt(s2[1]);
            arr[x][y] = 1;
            queue.add(new int[]{x, y});
        }

        while (!queue.isEmpty()) {
            int[] current = queue.pop();
            if (!visited[current[0]][current[1]]) {
                count++;
                dfs(current);
            }
        }
        System.out.println(count);
    }

    private static void dfs(int[] baechu) {
        visited[baechu[0]][baechu[1]] = true;

        for (int i = 0; i < 4; i++) {
            int nx = baechu[0] + dx[i];
            int ny = baechu[1] + dy[i];

            if (nx < 0 || ny < 0 || nx >= M || ny >= N || visited[nx][ny]) {
                continue;
            }

            if (arr[nx][ny] == 0) {
                continue;
            }

            dfs(new int[]{nx, ny});
        }
    }

}
