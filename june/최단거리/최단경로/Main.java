package june.최단거리.최단경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
//5 6
//1
//5 1 1
//1 2 2
//1 3 3
//2 3 4
//2 4 5
//3 4 6
    static int V;
    static int E;
    static int K;
    static int[][] a;
    static boolean[] v;
    static int[] d;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        V = Integer.parseInt(s[0]);
        E = Integer.parseInt(s[1]);
        K = Integer.parseInt(br.readLine());
        // 1. 그래프 초기화
        a = new int[V + 1][V + 1];
        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                a[i][j] = 100;
            }
        }
        for (int i = 0; i < E; i++) {
            String[] s1 = br.readLine().split(" ");
            int u = Integer.parseInt(s1[0]);
            int v = Integer.parseInt(s1[1]);
            int w = Integer.parseInt(s1[2]);
            a[u][v] = w;
        }
        // 2. 방문 노드, 최단거리 배열
        v = new boolean[V + 1];
        d = new int[V + 1];

        dijkstra(K);
        for (int i = 1; i <= V; i++) {
            if (i == K) {
                System.out.println(0);
            } else if (d[i] > 10) {
                System.out.println("INF");
            } else {
                System.out.println(d[i]);
            }
        }
    }

    private static void dijkstra(int start) {
        for (int i = 1; i <= V; i++) {
            d[i] = a[start][i];
        }
        v[start] = true;
        for (int i = 1; i <= V - 2; i++) {
            int current = getMinIndex();
            v[current] = true;
            for (int j = 1; j <= V; j++) {
                if (!v[j] && d[j] > d[current] + a[current][j]) {
                    d[j] = d[current] + a[current][j];
                }
            }
        }
    }

    private static int getMinIndex() {
        int min = 100;
        int index = 0;
        for (int i = 1; i <= V; i++) {
            if (d[i] < min && !v[i]) {
                min = d[i];
                index = i;
            }
        }
        return index;
    }

}
