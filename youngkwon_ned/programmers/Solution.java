package youngkwon_ned.programmers;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/169199">리코쳇 로봇</a>
 */
class Solution {
    private static int[] dx = {0, 0, -1, 1};
    private static int[] dy = {1, -1, 0, 0};
    private static int n, m;
    private static String[] map;


//    public static void main(String[] args) {
//        Solution s = new Solution();
//        String[] s1 = {
//                "...D..R",
//                ".D.G...",
//                "....D.D",
//                "D....D.",
//                "..D...."};
//        int solution = s.solution(s1);
//        System.out.println("solution = " + solution);
//    }

    public int solution(String[] board) {
        map = board;
        n = board.length;
        m = board[0].length();
        Node robot = null;
        Node goal = null;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length(); j++) {
                if (board[i].charAt(j) == 'R') {
                    robot = new Node(i, j, 0);
                } else if (board[i].charAt(j) == 'G') {
                    goal = new Node(i, j, 0);
                }
            }
        }

        return bfs(robot, goal);
    }

    private int bfs(Node robot, Node goal) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(robot);
        boolean[][] visited = new boolean[n][m];
        visited[robot.x][robot.y] = true;

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            if (currentNode.x == goal.x && currentNode.y == goal.y) {
                return currentNode.depth;
            }

            for (int i = 0; i < 4; i++) {
                int nextX = currentNode.x;
                int nextY = currentNode.y;
                while (inRange(nextX, nextY) && map[nextX].charAt(nextY) != 'D') {
                    nextX += dx[i];
                    nextY += dy[i];
                }
                nextX -= dx[i];
                nextY -= dy[i];

                if (visited[nextX][nextY] || (currentNode.x == nextX && currentNode.y == nextY)) {
                    continue;
                }

                visited[nextX][nextY] = true;
                queue.add(new Node(nextX, nextY, currentNode.depth + 1));
            }
        }
        return -1;
    }

    private boolean inRange(int x, int y) {
        return x >= 0 && y >= 0 && x < n && y < m;
    }

    private static class Node {
        int x;
        int y;
        int depth;

        public Node(int x, int y, int depth) {
            this.x = x;
            this.y = y;
            this.depth = depth;
        }
    }
}
