import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Ned {

    public static final int MAX_VAL = Integer.MAX_VALUE/3;
    public static void getAnswer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        List<int[]>[] edges = new List[N+1];
        for(int i=0;i<=N;i++){
            edges[i] = new ArrayList<>();
        }
        for(int i=0;i<M;i++){
            int[] edgeArr = Arrays.stream(br.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
            edges[edgeArr[0]].add(new int[]{edgeArr[1],edgeArr[2]});
            edges[edgeArr[1]].add(new int[]{edgeArr[0],edgeArr[2]});
        }
        st = new StringTokenizer(br.readLine());
        int u = Integer.parseInt(st.nextToken());
        int v = Integer.parseInt(st.nextToken());
        int [] dijkstraStartOne = getDijstraArray(1,edges,N);
        int [] dijkstraStartU = getDijstraArray(u,edges,N);
        int [] dijkstraStartV = getDijstraArray(v,edges,N);
        int uToV = dijkstraStartOne[u] + dijkstraStartU[v] + dijkstraStartV[N];
        int vToU = dijkstraStartOne[v] + dijkstraStartV[u] + dijkstraStartU[N];

        int minNum = Math.min(uToV,vToU);
        if(minNum >= MAX_VAL) System.out.println(-1);
        else System.out.println(minNum);
    }

    private static int[] getDijstraArray(int startIndex, List<int[]>[] edges, int N) {
        int [] minArr = new int[N+1];
        Arrays.fill(minArr,MAX_VAL);
        Queue<int[]> queue = new PriorityQueue<>((o1, o2) -> {
            return o1[1] - o2[1];
        });
        queue.add(new int[]{startIndex,0});
        minArr[startIndex] = 0;
        while(!queue.isEmpty()){
            int [] curIndex = queue.poll();

            for(int[] edge : edges[curIndex[0]]){
                if(edge[1] + curIndex[1] < minArr[edge[0]]){
                    minArr[edge[0]] = edge[1] + curIndex[1];
                    queue.add(new int[]{edge[0],edge[1]+curIndex[1]});
                }
            }
        }
        return minArr;
    }

    public static void main(String[] args) throws IOException {
        getAnswer();
    }
}