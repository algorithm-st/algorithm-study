package week15;
import java.util.*;

/**
 문제 설명
 - A, B 두사람이 택시를 타고 귀가하려한다.
 - 지점 : 1 ~ n 까지 있다. / 간선 : 양방향
 - 만약, 아예 합승을 하지 않고, 각자 이동하는 경우가 더 낮으면 합승을 하지 않아도 된다.
 - 3 <= n <= 200

 문제 풀이
 - 프로이드 워샬 : n^3 (8,000,000)
 - 모든 점에 대해 그 점이 환승점일 때, 계산해서 업데이트
 **/
public class Ned {
    static class Solution {
        final int INF = Integer.MAX_VALUE/3;
        public int solution(int n, int s, int a, int b, int[][] fares) {
            int answer = Integer.MAX_VALUE;

            int[][] dis = new int[n+1][n+1];
            for(int i=1; i<=n; i++){
                for(int j=1; j<=n; j++){
                    if(i==j){
                        dis[i][j] = 0;
                    }else{
                        dis[i][j] = INF;
                    }
                }
            }

            for(int[] fare : fares){
                int from= fare[0]; int to = fare[1]; int subFare = fare[2];
                dis[from][to] = Math.min(dis[from][to], subFare); dis[to][from] = Math.min(dis[to][from], subFare);
            }

            // 플로이드 워샬
            for(int tmp = 1; tmp<=n; tmp++){
                for(int i=1; i<=n; i++){
                    for(int j=1; j<=n; j++){
                        dis[i][j] = Math.min(dis[i][j], dis[i][tmp] +dis[tmp][j]);
                    }
                }
            }

            // 모든 점에 대해 환승지점 체크
            for(int i=1; i<=n; i++){
                int withFare = dis[s][i];
                int aFare = dis[i][a];
                int bFare = dis[i][b];

                answer = Math.min(answer, withFare+aFare+bFare);
            }

            return answer;
        }
    }
}
