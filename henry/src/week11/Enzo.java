package week11;

import java.util.*;

/**
 *  문제 설명
 *  - n * m 격자 미로
 *  - 미로의 (x, y)에서 출발해 (r, c)로 이동해서 탈출
 *  - 조건 3가지
 *  1. 격자의 바깥으로는 나갈 수 없습니다.
 *  2. (x, y)에서 (r, c)까지 이동하는 거리가 총 k여야 합니다. 이때, (x, y)와 (r, c)격자를 포함해, 같은 격자를 두 번 이상 방문해도 됩니다.
 *  3. 미로에서 탈출한 경로를 문자열로 나타냈을 때, 문자열이 사전 순으로 가장 빠른 경로로 탈출해야 합니다.
 */
public class Enzo {

    public static void main(String[] args) {
        Solution solution = new Solution();

        System.out.println(solution.solution(3, 4, 2, 3, 3, 1, 5));
    }

    static class Solution {
        private static final String[] dirStringArray = {"d", "l", "r", "u"};
        private static final int[] dx = {1, 0, 0, -1};
        private static final int[] dy = {0, -1, 1, 0};

        public String solution(int n, int m, int x, int y, int r, int c, int k) {
            String answer = null;

            PriorityQueue<Point> queue = new PriorityQueue<>((p1, p2)->{
                return p1.path.compareTo(p2.path);
            });
            queue.offer(new Point(x, y, 0, ""));

            while(!queue.isEmpty()){
                Point point = queue.poll();

                if(point.x == r && point.y == c && point.cnt == k){
                    answer = point.path;
                    break;
                }

                if(point.cnt == k){
                    continue;
                }

                for(int i=0; i<4; i++){
                    int nextX = point.x + dx[i];
                    int nextY = point.y + dy[i];

                    if(nextX <= 0 || nextX > n || nextY <= 0 || nextY > m){
                        continue;
                    }

                    int remain = Math.abs(nextX - r) + Math.abs(nextY - c);
                    int remainMoveCnt = k - (point.cnt+1);
                    if(remain > remainMoveCnt){
                        continue;
                    }

                    if(Math.abs(remain - remainMoveCnt) % 2 ==1){
                        continue;
                    }

                    Point nextPoint = new Point(nextX, nextY, point.cnt + 1, point.path + dirStringArray[i]);
                    queue.offer(nextPoint);
                }
            }

            if(answer == null){
                answer = "impossible";
            }

            return answer;
        }

        static class Point{
            int x;
            int y;
            int cnt;
            String path;

            public Point(int x, int y, int cnt, String path) {
                this.x = x;
                this.y = y;
                this.cnt = cnt;
                this.path = path;
            }
        }
    }
}
