package june.프로그래머스.부대복귀;

import java.util.*;

public class Fail_Solution {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        List<Integer>[] list = new ArrayList[n+1];
        for(int i = 0; i<n; i++){
            list[i+1] = new ArrayList();
        }
        
        for(int[] road: roads){
            list[road[0]].add(road[1]);
            list[road[1]].add(road[0]);
        }
        int index = 0;
        for(int source: sources){
            answer[index++] = go(source, destination, list);
        }
        

        
        return answer;
    }
    
    public int go(int source, int desti, List<Integer>[] list){
        boolean[] visited = new boolean[list.length + 1];
        visited[source] = true;
        
        LinkedList<Integer> q = new LinkedList<>();
        q.add(source);
        int len = 0;
        int count = 0;
        while(!q.isEmpty()){
            len = q.size();
            for(int i =0; i<len; i++){
                int now = q.poll();
                if (now == desti) {
                    return count;
                }
                
                for(int next : list[now]){
                    if (visited[next]) {
                        continue;
                    }
                    q.add(next);    
                }
                
            }
            count++;
        }
        return -1;
    }
}