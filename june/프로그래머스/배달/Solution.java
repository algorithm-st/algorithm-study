package june.프로그래머스.배달;

import java.util.*;

class Solution {
    public int solution(int N, int[][] road, int K) {
        int answer = 0;
        List<Node>[] graph = new ArrayList[N+1];
        int[] dis = new int[N+1];
        Arrays.fill(dis, Integer.MAX_VALUE);
        boolean[] visited = new boolean[N+1];
        
        for(int i = 1; i<=N; i++){
            graph[i] = new ArrayList<>();
        }

        for(int i = 0; i<road.length; i++){
            graph[road[i][0]].add(new Node(road[i][1], road[i][2]));
            graph[road[i][1]].add(new Node(road[i][0], road[i][2]));
        }
        
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(o1 -> o1.val));
        queue.add(new Node(1, 0));
        visited[1] = true;
        dis[1] = 0;
        int count = 0;
        while(!queue.isEmpty()){
            Node now = queue.poll();
            // visited[now.index] = true;
            if (now.val > dis[now.index]) {
                continue;
            }
            count++;
            if(count>=N){
                break;
            }
            System.out.println("zz" + now.index);

            for(Node next : graph[now.index]){
                // if(visited[next.index]) continue;
                
                if(dis[next.index] > now.val + next.val){
                    dis[next.index] = now.val + next.val;
                    queue.add(new Node(next.index, dis[next.index]));
                }
            }
        }
        
        for(int i =1; i<=N; i++){
            System.out.println(dis[i]);
            if(dis[i] <= K){
                answer++;
            }
        }
        return answer;
    }
}

class Node{
    int index;
    int val;
    
    public Node(int index, int val){
        this.index = index;
        this.val = val;
    }
}