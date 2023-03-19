package youngkwon_ned.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <a href="https://www.acmicpc.net/problem/1504">특정한 최단 경로</a>
 */
public class Main {

    private static int NODE, EDGE;
    private static List<List<Node>> list;
    private static int[] dist; // a -> b 로 가는 최단거리
    private static final int INF = 200_000_000; // 1000 * 200000

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        NODE = Integer.parseInt(st.nextToken());
        EDGE = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        dist = new int[NODE + 1];

        for (int i = 0; i < NODE + 1; i++) {
            list.add(new ArrayList<>());
        }

        for (int i = 0; i < EDGE; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            list.get(start).add(new Node(end, weight));
            list.get(end).add(new Node(start, weight));
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int res1 = 0;
        res1 += dijkstra(1, v2);
        res1 += dijkstra(v2, v1);
        res1 += dijkstra(v1, NODE);

        int res2 = 0;
        res2 += dijkstra(1, v1);
        res2 += dijkstra(v1, v2);
        res2 += dijkstra(v2, NODE);

        int answer = (res1 >= INF && res2 >= INF) ? -1 : Math.min(res1, res2);

        System.out.println(answer);
    }

    private static int dijkstra(int start, int end) {
        Arrays.fill(dist, INF);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] check = new boolean[NODE + 1];
        pq.offer(new Node(start, 0));
        dist[start] = 0;
        while (!pq.isEmpty()) {
            Node currentNode = pq.poll();
            int current = currentNode.end;
            if (!check[current]) {
                check[current] = true;
                for (Node node : list.get(current)) {
                    if (!check[node.end] && dist[node.end] > dist[current] + node.weight) {
                        dist[node.end] = dist[current] + node.weight;
                        pq.add(new Node(node.end, dist[node.end]));
                    }
                }
            }
        }

        return dist[end];
    }

    private static class Node implements Comparable<Node> {
        int end;
        int weight;

        Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return weight - o.weight;
        }

    }
}

