
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Bear {

    public static void getAnswer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int V = Integer.parseInt(st.nextToken());
        Set<Integer> [] edges = new Set[N+1];
        for(int i =1;i<=N;i++){
            edges[i] = new TreeSet<>();
        }
        for(int i=0;i<M;i++){
            int [] array  = Arrays.stream(br.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
            edges[array[0]].add(array[1]);
            edges[array[1]].add(array[0]);
        }
        boolean []checkDfs = new boolean[N+1];
        String dfsAnswer = dfs(V, edges, checkDfs);
        boolean []checkBfs = new boolean[N+1];
        String bfsAnswer =  bfs(V,edges, checkBfs);
        System.out.println(dfsAnswer);
        System.out.println(bfsAnswer);

    }

    public static String dfs(int startIndex, Set<Integer>[] edges, boolean[] check){
        if(check[startIndex]) return "";

        check[startIndex] = true;
        String s = Integer.toString(startIndex);

        for(int i : edges[startIndex]){
            if(!check[i]){
                s += " "+ dfs(i,edges,check);
            }
        }
        return s;
    }
    public static String bfs(int startIndex, Set<Integer>[] edges, boolean[] check){
        check[startIndex] = true;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(startIndex);
        String s = "";

        while(!queue.isEmpty()){
            int curIndex = queue.poll();
            s += curIndex+" ";

            for(int i : edges[curIndex]){
                if(!check[i]){
                    check[i] = true;
                    queue.add(i);
                }
            }
        }
        return s;
    }


    public static void main(String []args) throws Exception{
        getAnswer();
    }
}
