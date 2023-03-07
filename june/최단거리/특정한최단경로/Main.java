package june.최단거리.특정한최단경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    static class Node {

        int index;
        int dis;

        public Node(int index, int dis) {
            this.index = index;
            this.dis = dis;
        }
    }

    static int INF = 200000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        ArrayList<Node>[] arr = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            arr[a].add(new Node(b, c));
            arr[b].add(new Node(a, c));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());
        int first = 0;
        first += dijkstra(arr, 1, v1, N);
        first += dijkstra(arr, v1, v2, N);
        first += dijkstra(arr, v2, N, N);
        int second = 0;
        second += dijkstra(arr, 1, v2, N);
        second += dijkstra(arr, v2, v1, N);
        second += dijkstra(arr, v1, N, N);

        int min = Math.min(first, second);
        if (min >= INF) {
            System.out.println(-1);
        } else {
            System.out.println(min);
        }
    }

    private static int dijkstra(ArrayList<Node>[] arr, int start, int end,int N) {
        PriorityQueue<Node> pq = new PriorityQueue<>((Comparator.comparingInt(o -> o.dis)));

        boolean[] v = new boolean[N + 1];
        int[] d = new int[N + 1];
        Arrays.fill(d, INF);
        pq.offer(new Node(start, 0));
        d[start] = 0;
        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (!v[now.index]) {
                v[now.index] = true;
                for (Node next : arr[now.index]) {
                    if (d[next.index] > d[now.index] + next.dis) {
                        d[next.index] = d[now.index] + next.dis;
                        pq.offer(new Node(next.index, d[next.index]));
                    }
                }
            }
        }
        return d[end];
    }

}
