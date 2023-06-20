import java.util.*;
public class Enzo {
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] minTimeNodeIndexList = new int[n+1];
        Arrays.fill(minTimeNodeIndexList, Integer.MAX_VALUE);
        List<Integer>[] edges = new List[n+1];
        for(int i=0;i<=n;i++){
            edges[i] = new ArrayList<>();
        }
        for(int [] road : roads){
            edges[road[0]].add(road[1]);
            edges[road[1]].add(road[0]);
        }
        // nodeIndex, count
        Queue<int[]> queue = new PriorityQueue<>((o1,o2)->{
            return o1[1] - o2[1];
        });
        minTimeNodeIndexList[destination] = 0;
        queue.add(new int[]{destination,0});
        while(!queue.isEmpty()){
            int [] index = queue.poll();
            for(int i : edges[index[0]]){
                if(minTimeNodeIndexList[i] > index[1]+1){
                    minTimeNodeIndexList[i] = index[1]+1;
                    queue.add(new int[]{i,index[1]+1});
                }
            }
        }
        int[] answer = new int[sources.length];
        for(int i=0; i< sources.length; i++){
            answer[i] = (minTimeNodeIndexList[sources[i]] == Integer.MAX_VALUE) ? -1 : minTimeNodeIndexList[sources[i]];
        }
        return answer;
    }
}