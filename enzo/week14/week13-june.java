import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Ned {
    public int solution(int N, int[][] road, int K) {
        int [][] edges = new int[N+1][N+1];
        for(int i=0;i<=N;i++){
            Arrays.fill(edges[i], Integer.MAX_VALUE);
        }
        for(int [] r : road){
            edges[r[0]][r[1]] = Math.min(edges[r[0]][r[1]], r[2]);
            edges[r[1]][r[0]] = Math.min(edges[r[1]][r[0]], r[2]);
        }

        Queue<int[]> queue = new PriorityQueue<>((o1, o2)->{
            return o1[1] - o2[1];
        });

        int [] checkNode = new int[N+1];
        Arrays.fill(checkNode, Integer.MAX_VALUE);
        checkNode[1] = 0;
        queue.add(new int[]{1,0});
        while(!queue.isEmpty()){
            int[] curIndex = queue.poll();
            if(curIndex[1] > K ) break;
            for(int i=1;i<=N;i++){
                if(edges[curIndex[0]][i] != Integer.MAX_VALUE && checkNode[i] > curIndex[1] + edges[curIndex[0]][i] ){
                    checkNode[i] = curIndex[1] + edges[curIndex[0]][i];
                    queue.add(new int[]{i,curIndex[1] + edges[curIndex[0]][i]});
                }
            }
        }
        int answer = 0;
        for(int i=1;i<=N;i++){
            if(checkNode[i] <= K) answer++;
        }
        return answer;
    }
}