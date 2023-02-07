package bear.week3;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BFS와DFS {

    static int N, M, V;
    static int[][] adj;
    static boolean[] visit;

    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        N = Integer.parseInt(st.nextToken()); // 정점의 개수
        M = Integer.parseInt(st.nextToken()); // 간선의 개수
        V = Integer.parseInt(st.nextToken()); // 정점의 번호 V



        adj = new int[N][N];
        visit = new boolean[N];

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(in.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            adj[x-1][y-1] = 1;
            adj[y-1][x-1] = 1;

        }


        dfs(V-1);
        sb.append("\n");
        visit = new boolean[N];
        bfs(V-1);
        System.out.println(sb.toString());



    }


    static void dfs(int x) {

        visit[x] = true;

        sb.append(x+1).append(" ");

        for(int y = 0; y< N; y++) {
            if(adj[x][y] == 0) continue;
            if(visit[y] == true) continue;;
            dfs(y);

        }
    }

    static void bfs(int x) {
        Queue<Integer> queue = new LinkedList<>();

        queue.add(x);
        visit[x] = true;

        while (!queue.isEmpty()) {
            x = queue.poll();
            sb.append(x+1).append(" ");
            for(int y = 0; y < N; y++) {
                if(adj[x][y] == 0) continue;
                if(visit[y] == true) continue;
                queue.add(y);

                visit[y] = true;
            }
        }

    }


}
