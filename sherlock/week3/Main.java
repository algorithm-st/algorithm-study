package sherlock.week3;

import java.util.LinkedList;
import java.util.Queue;

public class Main {
    public static int dx[] = {0, 1, 0, -1}; // 위 오른쪽 아래 왼쪽 (시계방향)
    public static int dy[] = {-1, 0, 1, 0};
    public static boolean[][] visited ;
    public static void main(String[] args) {

//        int[][] graph = {{1, 0, 1, 1, 1},
//                {1, 0, 1, 0, 1},
//                {1, 0, 1, 1, 1},
//                {1, 1, 1, 0, 1},
//                {0, 0, 0, 0, 1}};
        int[][] graph = {{1,0,1,1,1},
                         {1,0,1,0,1},
                         {1,0,1,1,1},
                         {1,1,1,0,0},
                         {0,0,0,0,1}};

        int bfs = bfs(graph, 0, 0, graph.length, graph[0].length);
        System.out.println(bfs);
    }

    public static int bfs(int[][] graph, int x, int y, int row, int col) {

        Queue<Node> q = new LinkedList<>();
        graph[x][y] = 1; //처음시작
        q.offer(new Node(x, y));

        while (!q.isEmpty()) {
            Node node = q.poll();
            x = node.getX();
            y = node.getY();

            if(x == 4 && y == 4) return graph[4][4];
            for (int i = 0; i < 4; i++) {
                int next_x = x + dx[i];
                int next_y = y + dy[i];

                if (next_x < 0 || next_x >= 5 || next_y < 0 || next_y >= 5) continue;
                if (graph[next_x][next_y] == 0) continue;
                if (graph[next_x][next_y] == 1) {
                    graph[next_x][next_y] = graph[x][y] + 1;
                    visited[next_x][next_y] = true;
                    q.offer(new Node(next_x, next_y));
                }
            }
        }
        return -1;
    }

}
class Node {
    private int x;
    private int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}