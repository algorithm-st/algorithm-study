package june.BFS.안전영역;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

//문제 푼 데 걸린 시간은?
// 30분

//시간 복잡도는 ? (잘 모르겠다면 예상)
// O(V+E)

//문제 풀이 설명
// 물 높이 max를 구한 뒤 1부터 max까지 완전탐색으로 안전구역을 찾는다
// 안전구역은 bfs로 탐색해서 방문여부 체크한다
// 좀 막혔던 부분은 count할때 이미 max높이보다 낮은 구역은 굳이 찾아 들어갈 필요가 없다
// 그래서 완전탐색에서 bfs 조건문에 visited 추가하면 된다
// 마지막으로 모든 해수면이 같을때 정답은 1인데 0으로 나와 수정했다
public class Main {
    public static int[][] arr;
    public static boolean[][] visited;
    public static int N;
    public static int maxWater;
    public static int answer;
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        maxWater = 0;
        answer = 0;
        for (int i = 0; i < N; i++) {
            String[] s = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(s[j]);
                maxWater = Math.max(arr[i][j], maxWater);
            }
        }

        for (int i = 1; i <= maxWater; i++) {
            int count = 0;
            visited = new boolean[N][N];

            for (int j = 0; j < N; j++) {
                for (int k = 0; k < N; k++) {
                    if (!visited[j][k] && arr[j][k] > i) {
                        bfs(i, j, k);
                        count++;
                    }
                }
            }
            answer = Math.max(answer, count);
        }
        if (answer == 0) {
            System.out.println(1);
            return;
        }
        System.out.println(answer);

    }

    private static void bfs(int height, int j, int k) {
        LinkedList<int[]> queue = new LinkedList<>();
        queue.add(new int[]{j, k});

        while (!queue.isEmpty()) {
            int[] current = queue.pop();
            for (int i = 0; i < 4; i++) {
                int nx = current[0] + dx[i];
                int ny = current[1] + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N || visited[nx][ny]) {
                    continue;
                }

                if (arr[nx][ny] <= height) {
                    continue;
                }

                queue.add(new int[]{nx, ny});
                visited[nx][ny] = true;
            }
        }
    }

}
