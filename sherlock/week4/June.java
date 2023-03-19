package sherlock.week4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class June {
    static int n, m;
    static int[][] graph;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new int[n][m];

        // graph 세팅
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                // 아스키 코드로 빼서 int형으로 변환되게
                graph[i][j] = s.charAt(j) - '0';
            }
        }

        bfs();
        System.out.println(graph[n - 1][m - 1]);

    }

    public static void bfs() {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(0, 0));

        while (!q.isEmpty()) {
            Node node = q.poll();
            // 동서남북 4가지 방향
            for (int dir = 0; dir < 4; dir++) {
                int next_x = node.x + dx[dir];
                int next_y = node.y + dy[dir];
                // 범위를 벗어나지 않고, 다음 이동하는 칸이 1인 경우에만 이동
                if (inRange(next_x, next_y) && graph[next_x][next_y] == 1) {
                    graph[next_x][next_y] = graph[node.x][node.y] + 1;
                    q.offer(new Node(next_x, next_y));
                }
            }
        }
    }

    public static boolean inRange(int x, int y) {
        return (0 <= x && x < n && 0 <= y && y < m);
    }
}

class Node {
    int x, y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
