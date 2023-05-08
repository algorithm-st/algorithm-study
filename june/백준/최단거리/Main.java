package june.백준.최단거리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    //5 6
//1
//5 1 1
//1 2 2
//1 3 3
//2 3 4
//2 4 5
//3 4 6
    static List<Node>[] graph;
    static int[] dis;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int start = Integer.parseInt(br.readLine());

        graph = new ArrayList[V + 1];
        dis = new int[V + 1];
        visited = new boolean[V + 1];

        for (int i = 1; i <= V; i++) {
            graph[i] = new ArrayList<>();
            dis[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            graph[a].add(new Node(b, c));
        }

        dijkstra(start);

        for (int i = 1; i <= V; i++) {
            int answer = dis[i];
            if (answer == Integer.MAX_VALUE) {
                System.out.println("INF");
                continue;
            }
            System.out.println(answer);
        }
    }

    private static void dijkstra(int start) {
        PriorityQueue<Node> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        queue.add(new Node(start, 0));
        dis[start] = 0;

        while (!queue.isEmpty()) {
            Node now = queue.poll();
            visited[now.index] = true;
            for (Node next : graph[now.index]) {
                if (visited[next.index]) continue;
                if (dis[next.index] > dis[now.index] + next.val) {
                    dis[next.index] = dis[now.index] + next.val;
                    queue.offer(new Node(next.index, dis[next.index]));
                }
            }
        }
    }
}

class Node {

    int index;
    int val;

    public Node(int index, int val) {
        this.index = index;
        this.val = val;
    }
}
