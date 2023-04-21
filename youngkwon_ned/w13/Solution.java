package youngkwon_ned.w13;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/1844">게임 맵 최단거리</a>
 */
public class Solution {
    private static int N, M;
    private static int[][] VISIT;
    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] maps = {{1, 0, 1, 1, 1}, {1, 0, 1, 0, 1}, {1, 0, 1, 1, 1}, {1, 1, 1, 0, 1}, {0, 0, 0, 0, 1}};
        int solution = s.solution(maps);
        System.out.println("solution = " + solution);
    }

    public int solution(int[][] maps) {
        int answer = 0;
        N = maps.length;
        M = maps[0].length;
        VISIT = new int[N][M];
        VISIT[0][0] = 1;
        bfs(new Node(0, 0), maps);
        answer = VISIT[N-1][M-1];
        if (answer == 0) {
            answer = -1;
        }
        return answer;
    }

    private static void bfs(Node start, int[][] maps) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nextX = node.x + dx[i];
                int nextY = node.y + dy[i];
                if (nextX < 0 || nextY < 0 || nextX > N-1 || nextY > M-1) {
                    continue;
                }
                if (maps[nextX][nextY] == 1 && VISIT[nextX][nextY] == 0){
                    Node newNode = new Node(nextX, nextY);
                    queue.add(newNode);
                    VISIT[nextX][nextY] = VISIT[node.x][node.y] + 1;
                }
            }
        }
    }

    static class Node {
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int x;
        int y;
    }
}
