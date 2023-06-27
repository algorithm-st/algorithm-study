package week21;

import java.util.*;
public class Enzo {
    static class Solution {

        static List<Set<Integer>> map;
        static final int INF = Integer.MAX_VALUE/2;
        public int[] solution(int n, int[][] roads, int[] sources, int destination) {
            map = new ArrayList<>();
            for(int i=0; i<=n; i++){
                map.add(new HashSet<>());
            }
            for(int[] road : roads){ // 거리 맵 세팅
                int a = road[0]; int b = road[1];
                map.get(a).add(b);
                map.get(b).add(a);
            }

            // 다익스트라
            int[] dis = new int[n+1];
            Arrays.fill(dis, INF);
            boolean[] ch = new boolean[n+1];
            dis[destination] = 0;

            PriorityQueue<Node> pq = new PriorityQueue<>((a, b)->{
                return a.dis - b.dis;
            });

            pq.offer(new Node(destination, 0));
            while(!pq.isEmpty()){
                Node node = pq.poll();

                if(ch[node.num]){
                    continue;
                }
                ch[node.num] = true;

                for(int i : map.get(node.num)){
                    if(!map.get(node.num).contains(i)){
                        continue;
                    }

                    if(node.dis + 1 < dis[i]){
                        dis[i] = node.dis + 1;
                        pq.offer(new Node(i, dis[i]));
                    }
                }
            }


            int[] answer = new int[sources.length];
            for(int i=0; i<sources.length; i++){
                answer[i] = dis[sources[i]] == INF ? -1 : dis[sources[i]];
            }
            return answer;
        }
        static class Node{
            int num;
            int dis;

            Node(int num, int dis){
                this.num = num;
                this.dis = dis;
            }
        }
    }
}
