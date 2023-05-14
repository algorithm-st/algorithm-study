package week15;

/**
 문제 설명
 - N개의 마을로 이루어진 나라 1번 ~ N번까지 번호 부여
 - 양방향 통행 도로, 도로별로 시간이 다르다.
 - 1번 마을에 있는 음식점 -> N개의 마을 중에서 K시간 이하로 배달이 가능한 마을에서만 주문을 받는다.

 문제 풀이
 - 다익스트라 알고리즘
 */
import java.util.*;
public class June {

    public static class Solution{
        public int solution(int N, int[][] road, int K) {
            int answer = 0;

            // 거리 배열 초기화
            int[][] map = new int[N + 1][N + 1];
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    map[i][j] = Integer.MAX_VALUE / 2;
                }
            }
            for (int i = 1; i <= N; i++) {
                map[i][i] = 0;
            }
            for (int[] ro : road) {
                map[ro[0]][ro[1]] = Math.min(map[ro[0]][ro[1]], ro[2]);
                map[ro[1]][ro[0]] = Math.min(map[ro[1]][ro[0]], ro[2]);
            }


            int[] dis = new int[N + 1];
            for (int i = 2; i <= N; i++) {
                dis[i] = Integer.MAX_VALUE / 2;
            }

            PriorityQueue<Node> pq = new PriorityQueue<>((a, b)->{
                return a.dis - b.dis;
            });
            pq.offer(new Node(1, 0));

            while(!pq.isEmpty()) {
                Node nowNode = pq.poll();
                for (int j = 1; j <= N; j++) {
                    int nextDis = dis[nowNode.number] + map[nowNode.number][j];
                    if (nextDis < dis[j]) {
                        dis[j] = nextDis;
                        pq.offer(new Node(j, dis[j]));
                    }
                }
            }

            for (int i = 1; i <= N; i++) {
                if (dis[i] <= K) {
                    answer++;
                }
            }

            return answer;
        }

        private static class Node {
            int number;
            int dis;

            public Node(int number, int dis) {
                this.number = number;
                this.dis = dis;
            }
        }
    }
}
