package week3_dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bear {
    static int n;
    static int m;
    static int v;

    static boolean[][] graph;
    static boolean[] ch;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String s = bufferedReader.readLine();
        StringTokenizer stringTokenizer = new StringTokenizer(s);

        n = Integer.valueOf(stringTokenizer.nextToken());
        m = Integer.valueOf(stringTokenizer.nextToken());
        v = Integer.valueOf(stringTokenizer.nextToken());

        graph = new boolean[n + 1][n + 1];

        for (int i = 0; i < m; i++) {
            String s1 = bufferedReader.readLine();
            StringTokenizer stringTokenizer1 = new StringTokenizer(s1);

            int point1 = Integer.valueOf(stringTokenizer1.nextToken());
            int point2 = Integer.valueOf(stringTokenizer1.nextToken());

            // 양방향
            graph[point1][point2] = true;
            graph[point2][point1] = true;
        }

        //dfs
        ch = new boolean[n + 1];
        ch[v] = true;
        dfs(v);
        System.out.println();

        //bfs
        ch = new boolean[n + 1];
        ch[v] = true;
        bfs(v);
    }

    static void dfs(int point) {
        System.out.print(point + " ");

        for (int i = 1; i <= n; i++) {
            if (graph[point][i] == true && ch[i] == false) {
                ch[i] = true;
                dfs(i);
            }
        }
    }

    static void bfs(int point){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(point);
        while (!queue.isEmpty()) {
            Integer nowPoint = queue.poll();
            System.out.print(nowPoint + " ");
            for (int i = 1; i <= n; i++) {
                if (graph[nowPoint][i] == true && ch[i] == false) {
                    ch[i] = true;
                    queue.add(i);
                }
            }
        }
    }
}
