package youngkwon_ned.dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <a href="https://www.acmicpc.net/problem/2644">촌수계산</a>
 */
public class Main {

    private static int end, answer = -1;
    private static boolean[] visited;
    private static List<List<Integer>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        // 방문 여부 배열 생성
        visited = new boolean[n + 1];

        // 인접 리스트 초기화
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }


        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        // 관계의 촌수를 구할 시작점과 끝점 구함.
        int start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        // 간선의 갯수
        int relationCount = Integer.parseInt(br.readLine());

        for (int i = 0; i < relationCount; i++) {
            StringTokenizer tokenizer = new StringTokenizer(br.readLine());
            // 촌수를 나타내는 관계에서 시작-끝을 구하고 관계를 담을 인접리스트에 저장
            int from = Integer.parseInt(tokenizer.nextToken());
            int to = Integer.parseInt(tokenizer.nextToken());
            graph.get(from).add(to);
            graph.get(to).add(from);
        }

        // 시작점, 촌수
        dfs(start, 0);
        System.out.println(answer);
    }

    private static void dfs(int start, int cnt) {
        // 탐색을 하는 위치 방문여부 체크
        visited[start] = true;

        // 인접리스트에서 시작점에 해당하는 리스트 순회
        for (int x : graph.get(start)) {
            // 방문하지 않았다면
            if (!visited[x]) {
                // 탐색환 해당 위치가 목표점(끝)인지 확인
                if (x == end) {
                    // 맞으면 촌수 계산 후 리턴
                    answer = cnt + 1;
                    return;
                }
                // 아니면 촌수 + 1 후 dfs 계속 진행
                dfs(x, cnt + 1);
            }
        }
    }

}
