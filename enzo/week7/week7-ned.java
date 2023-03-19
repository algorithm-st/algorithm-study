import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Ned {

    public static void getAnswer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        List<int[]> []edges = new List[N+1];
        for(int i=0;i<=N;i++){
            edges[i] = new ArrayList<>();
        }
        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());
            edges[start].add(new int[]{end,val});
            edges[end].add(new int[]{start,val});
        }
        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());
        int[] oneTo = getDijkstra(N,edges,1);
        int[] v1To = getDijkstra(N,edges,v1);
        int[] nTo = getDijkstra(N,edges,N);
        long answer = Integer.MAX_VALUE*10;
        int v1Tov2 = v1To[v2];
        answer = Math.min((long)oneTo[v1]+v1Tov2+nTo[v2],(long)oneTo[v2]+v1Tov2+nTo[v1]);
        if(answer>=Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(answer);
    }

    private static int[] getDijkstra(int N, List<int[]>[] edges, int i) {
        int[] answer = new int[N+1];
        Arrays.fill(answer,Integer.MAX_VALUE);
        Queue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o[1]));
        answer[i]=0;
        queue.add(new int[]{i,0});
        while(!queue.isEmpty()){
            int[] cur = queue.poll();
            if(answer[cur[0]] < cur[1]) continue;
            for(int[] k : edges[cur[0]]){
                if(answer[k[0]] > cur[1]+k[1]){
                    answer[k[0]] = cur[1] + k[1];
                    queue.add(new int[]{k[0],cur[1]+k[1]});
                }
            }
        }
        return answer;
    }

    public static void main(String [] args) throws IOException {
        getAnswer();
    }
}
