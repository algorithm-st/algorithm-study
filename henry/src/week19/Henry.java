package week19;

/**
 왜 이문제를 선택했나
 - 3단계 카카오 기출 중 하나 선택

 문제 푸는 데 걸린 시간은?
 - 1시간

 시간 복잡도는?
 - dp 배열의 크기에 비례 n^2

 문제 설명
 - 자동차 경주로 건설에 필요한 견적 의뢰 받았다. 경주로 부지는 N x N
 - 각 칸 0 : 칸이 비어있다. 1 : 벽 / 출발점 (0, 0) 도착점(N-1, N-1)
 - 경주로는 상,하,좌,우로 인접한 두 빈 칸을 연결하여 건설할 수 있다. 벽이 있는 칸에는 경주로를 건설 할 수 없다.
 - 직선도로 : 100원 / 코너 : 500원이 "추가"로 든다
 - 견적서 작성을 위해 경주로를 건설하는 데 필요한 최소 비용을 계산해야 한다.

 문제 풀이 설명
 - 최소 비용을 찾는데 DP 배열을 사용
 - 단순 2차원 배열로는 자동차 방향에 따라 코너가 발생하는 경우를 고려할 수 없으므로 3차원 DP 배열을 선언해서 자동차 방향도 고려한다.
 */

import java.util.*;
public class Henry {
    class Solution {
        static int n;
        static int[][][] dp;
        // 위, 오른쪽, 아래, 왼쪽
        static int[] dr = {-1, 0, 1, 0};
        static int[] dc = {0, 1, 0, -1};

        public int solution(int[][] board) {

            // 초기화
            n = board.length;
            dp = new int[n][n][4];
            for(int i=0; i<n; i++){
                for(int j=0; j<n; j++){
                    for(int k=0; k<4; k++){
                        dp[i][j][k] = Integer.MAX_VALUE/2;
                    }
                }
            }
            for(int k=0; k<4; k++){
                dp[0][0][k] = 0;
            }

            Queue<Point> queue = new LinkedList<>();
            for(int dir=0; dir<4; dir++){
                queue.offer(new Point(0, 0, dir));
            }

            while(!queue.isEmpty()){
                Point point = queue.poll();

                for(int i=0; i<4; i++){
                    int nextR = point.r + dr[i];
                    int nextC = point.c + dc[i];

                    if(nextR < 0 || nextR >= n || nextC <0 || nextC >=n){ // 맵 초과
                        continue;
                    }

                    if(board[nextR][nextC]==1){ // 벽이면 패스
                        continue;
                    }

                    // 비용 절감이 안되면 패스
                    int cost = point.dir==i ? 100 : 600; // 방향이 다르면 코너 건설 비용 500원이 추가로 든다.
                    int newCost = dp[point.r][point.c][point.dir] + cost;
                    if(dp[nextR][nextC][i] < newCost){
                        continue;
                    }

                    dp[nextR][nextC][i] = newCost;
                    queue.offer(new Point(nextR, nextC, i));
                }
            }

            int answer = Integer.MAX_VALUE;
            for(int i=0; i<4; i++){
                answer = Math.min(answer, dp[n-1][n-1][i]);
            }
            return answer;
        }

        static class Point{
            int r; int c; int dir;
            Point(int r, int c, int dir){
                this.r = r; this.c = c; this.dir = dir;
            }
        }
    }
}
