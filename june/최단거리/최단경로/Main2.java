package june.최단거리.최단경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

public class Main2 {
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
    static ArrayList<Node>[] a;

    static boolean[] v;
    static int[] d;

    static class Node implements Comparable<Node>{

        int index;
        int val;

        public Node(int index, int val) {
            this.index = index;
            this.val = val;
        }

        @Override
        public int compareTo(Node o) {
            return Integer.compare(this.val, o.val);
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        V = Integer.parseInt(s[0]);
        E = Integer.parseInt(s[1]);
        K = Integer.parseInt(br.readLine());
        // 1. 그래프 초기화
        a = new ArrayList[V + 1];
        for (int i = 0; i <= V; i++) {
            a[i] = new ArrayList<>();
        }
        for (int i = 0; i < E; i++) {
            String[] s1 = br.readLine().split(" ");
            int u = Integer.parseInt(s1[0]);
            int v = Integer.parseInt(s1[1]);
            int w = Integer.parseInt(s1[2]);
            a[u].add(new Node(v, w));
        }
        // 2. 방문 노드, 최단거리 배열
        v = new boolean[V + 1];
        d = new int[V + 1];
        Arrays.fill(d, Integer.MAX_VALUE);
        d[K] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(K, 0));
        dijkstra(K);
        for (int i = 1; i <= V; i++) {
            if (i == K) {
                System.out.println(0);
            } else if (d[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
            } else {
                System.out.println(d[i]);
            }
        }
    }

    private static void dijkstra(int start) {
        d[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        while (!pq.isEmpty()) {
            int nowVertex = pq.poll().index;
            if (v[nowVertex]) {
                continue;
            }
            v[nowVertex] = true;
            for (Node next : a[nowVertex]) {
                if (d[next.index] > d[nowVertex] + next.val) {
                    d[next.index] = d[nowVertex] + next.val;
                    pq.offer(new Node(next.index, d[next.index]));
                }
            }
        }
        
    }
}
