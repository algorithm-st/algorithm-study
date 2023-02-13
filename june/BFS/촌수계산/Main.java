package june.BFS.촌수계산;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//문제 푼 데 걸린 시간은?
// 30분

//시간 복잡도는 ? (잘 모르겠다면 예상)
// O(V+E)

//문제 풀이 설명
// 부모자식 관계에 대한 정보가 넘어오면 서로 번호를 물고 있는다. -> people이라는 Map을 통해 자신의 번호와 그 번호와 연관된 부모, 자식 리스트
// 시작과 끝 번호가 주어지고 시작부터 방문한적 없는 인접한 촌수를 찾아가며 count
// 끝 번호를 만나면 count, 만나지 못하면 -1 리턴
public class Main {

    public static boolean[] visited;
    public static Map<Integer, List<Integer>> people = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String[] s = br.readLine().split(" ");
        int a = Integer.parseInt(s[0]);
        int b = Integer.parseInt(s[1]);
        int m = Integer.parseInt(br.readLine());
        visited = new boolean[n + 1];

        for (int i = 0; i < n; i++) {
            people.put(i + 1, new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            String[] s1 = br.readLine().split(" ");
            int a1 = Integer.parseInt(s1[0]);
            int b1 = Integer.parseInt(s1[1]);
            people.get(a1).add(b1);
            people.get(b1).add(a1);
        }

        System.out.println(bfs(a, b));
    }

    private static int bfs(int a, int b) {
        LinkedList<Integer> queue = new LinkedList<>();
        queue.add(a);
        visited[a] = true;
        int count = 0;
        while (!queue.isEmpty()) {
            int len = queue.size();
            count += 1;
            for (int i = 0; i < len; i++) {
                Integer current = queue.pop();
                List<Integer> relative = people.get(current);

                for (Integer rel : relative) {
                    if (rel == b) {
                        return count;
                    }

                    if (visited[rel]) {
                        continue;
                    }

                    queue.add(rel);
                    visited[rel] = true;
                }
            }
        }
        return -1;
    }
}
