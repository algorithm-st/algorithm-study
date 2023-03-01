import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Bear {


    public static void getAnswer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());
        List<int[]>[] edge = new List[V+1];
        for(int i=0;i<=V;i++){
            edge[i] = new ArrayList<>();
        }
        for(int i=0;i<E;i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            edge[start].add(new int[]{end,weight});
        }

        int [] minDistance = new int[V+1];
        Arrays.fill(minDistance,Integer.MAX_VALUE);

        Queue<int[]> pq = new PriorityQueue<>((o1,o2)->{
            if(o1[1] == o2[1]) return o1[0] - o2[0];
            return o1[1] - o2[1];
        });
        minDistance[K] = 0;
        pq.add(new int[]{K,0});
        int count = 0;
        while(!pq.isEmpty()){
            int[] index = pq.poll();
            if(minDistance[index[0]]<index[1]) continue;
            count++;
            if(count == V) break;
            for(int i=0;i<edge[index[0]].size();i++){
                int [] curIndex = edge[index[0]].get(i);
                if(minDistance[curIndex[0]] > index[1]+curIndex[1]){
                    minDistance[curIndex[0]] = index[1]+curIndex[1];
                    pq.add(new int[]{curIndex[0],index[1]+curIndex[1]});
                }
            }
        }
        StringBuffer sb = new StringBuffer();
        for(int i=1;i<minDistance.length;i++){
            String cur = (minDistance[i] == Integer.MAX_VALUE) ? "INF" : String.valueOf(minDistance[i]);
            sb.append(cur).append("\n");
        }
        System.out.println(sb.toString());
    }

    public static void main(String []args) throws IOException {
        getAnswer();
    }
}
