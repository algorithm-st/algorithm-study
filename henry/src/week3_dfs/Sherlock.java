package week3_dfs;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 */
public class Sherlock {

    public static void main(String[] args) {

    }

    public int solution(int[][] maps) {
        int answer = 0;


        answer = bfs(maps);


        return answer;
    }

    int bfs(int[][] maps){

        int result = Integer.MAX_VALUE;

        int n = maps.length;
        int m = maps[0].length;

        boolean[][] ch = new boolean[n][m];
        Queue<Point> queue = new LinkedList<>();

        ch[0][0] = true;
        queue.add(new Point(0, 0, 1));

        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        while (!queue.isEmpty()) {
            if (result != Integer.MAX_VALUE) {
                break;
            }

            Point point = queue.poll();

            for (int i = 0; i < 4; i++) {

                int nextR = point.r + dr[i];
                int nextC = point.c + dc[i];

                if (!(nextR >= 0 && nextR < n && nextC >= 0 && nextC < m)) {
                    continue;
                }

                if (ch[nextR][nextC] || maps[nextR][nextC]==0) {
                    continue;
                }

                ch[nextR][nextC] = true;
                if (nextR == n - 1 && nextC == m - 1) {
                    result = point.count + 1;
                }
                queue.add(new Point(nextR, nextC, point.count + 1));
            }

        }

        if (result == Integer.MAX_VALUE) {
            result = -1;
        }
        return result;
    }

    static class Point{
        int r;
        int c;
        int count;

        public Point(int r, int c, int count) {
            this.r = r;
            this.c = c;
            this.count = count;
        }
    }
}
