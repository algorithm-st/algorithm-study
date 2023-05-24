package june.프로그래머스.특정한최단경로;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    //4 6
//1 2 3
//2 3 3
//3 4 1
//1 3 5
//2 4 5
//1 4 4
//2 3
    static int N;
    static int M;
    static ArrayList<Node>[] list;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = toInt(st.nextToken());
        M = toInt(st.nextToken());

        list = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            list[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = toInt(st.nextToken());
            int b = toInt(st.nextToken());
            int c = toInt(st.nextToken());

            list[a].add(new Node(b, c));
            list[b].add(new Node(a, c));
        }
        st = new StringTokenizer(br.readLine());
        int a = toInt(st.nextToken());
        int b = toInt(st.nextToken());

        int first = 0;
        first += dijkstra(1, a);
        first += dijkstra(a, b);
        first += dijkstra(b, N);
        int second = 0;
        second += dijkstra(1, b);
        second += dijkstra(b, a);
        second += dijkstra(a, N);

        int min = Math.min(first, second);
        if (min >= 200000000) {
            System.out.println(-1);
        } else {
            System.out.println(min);
        }
    }

    private static int dijkstra(int start, int end) {
        PriorityQueue<Node> q = new PriorityQueue<>((Comparator.comparingInt(o -> o.value)));
        int[] dis = new int[N + 1];
        boolean[] v = new boolean[N + 1];
        Arrays.fill(dis, 200000000);

        q.add(new Node(start, 0));
        dis[start] = 0; // 놓친 부분

        while (!q.isEmpty()) {
            Node now = q.poll();
            if (!v[now.v]) {
                v[now.v] = true;
                for (Node next : list[now.v]) {
                    if (dis[next.v] > dis[now.v] + next.value) {
                        dis[next.v] = dis[now.v] + next.value;
                        q.add(new Node(next.v, dis[next.v]));
                    }
                }
            }
        }

        return dis[end];
    }

    public static int toInt(String a) {
        return Integer.parseInt(a);
    }

    private static class Node {

        int v;
        int value;

        public Node(int v, int value) {
            this.v = v;
            this.value = value;
        }

        public int getV() {
            return v;
        }

        public int getValue() {
            return value;
        }
    }
}
