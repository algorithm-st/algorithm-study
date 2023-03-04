package june.최단거리.타임머신;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    static int V;
    static int E;
    static ArrayList<Edge> arr;

    static long[] d;
    static int INF = 60000000;

    static class Edge {

        int from;
        int to;
        int dis;

        public Edge(int from, int to, int dis) {
            this.from = from;
            this.to = to;
            this.dis = dis;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        V = Integer.parseInt(s[0]);
        E = Integer.parseInt(s[1]);
        // 1. 그래프 초기화
        arr = new ArrayList();

        for (int i = 0; i < E; i++) {
            String[] s1 = br.readLine().split(" ");
            int u = Integer.parseInt(s1[0]);
            int v = Integer.parseInt(s1[1]);
            int w = Integer.parseInt(s1[2]);
            arr.add(new Edge(u, v, w));
        }

        if (bellmanFord()) {
            // 순환
            System.out.println(-1);
        } else {
            if (V == 1) {
                System.out.println(-1);
                return;
            }
            StringBuilder sb = new StringBuilder();
            for (int i = 2; i <= V; i++) {
                if (d[i] == INF) {
                    sb.append(-1).append("\n");
                    continue;
                }
                sb.append(d[i]).append("\n");
            }
            System.out.println(sb);
        }
    }

    private static boolean bellmanFord() {
        d = new long[V + 1];
        Arrays.fill(d, INF);
        d[1] = 0;

        for (int i = 0; i < V; i++) {
            for (int j = 0; j < E; j++) {
                Edge edge = arr.get(j);

                if (d[edge.from] != INF && d[edge.to] > d[edge.from] + edge.dis) {
                    d[edge.to] = d[edge.from] + edge.dis;
                    if (i == V - 1) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
