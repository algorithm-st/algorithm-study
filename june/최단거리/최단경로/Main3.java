package june.최단거리.최단경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main3 {
    static int V;
    static int E;
    static int K;
    static ArrayList<Node>[] a;
    static int[] d;

    static class Node {

        int index;
        int val;

        public Node(int index, int val) {
            this.index = index;
            this.val = val;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        V = Integer.parseInt(s[0]);
        E = Integer.parseInt(s[1]);
        K = Integer.parseInt(br.readLine());
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
        d = new int[V + 1];
        Arrays.fill(d, Integer.MAX_VALUE);

        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        pq.offer(new Node(K, 0));
        d[K] = 0;
        int count = 0;
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (d[now.index] < now.val) {
                continue;
            }
            count++;
            if (count >= V) {
                break;
            }
            for (Node next : a[now.index]) {
                if (d[next.index] > d[now.index] + next.val) {
                    d[next.index] = d[now.index] + next.val;
                    pq.offer(new Node(next.index, d[next.index]));
                }
            }
        }
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
}
