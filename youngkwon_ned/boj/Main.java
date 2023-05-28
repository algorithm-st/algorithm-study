package youngkwon_ned.boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * <a href="https://www.acmicpc.net/problem/1504">특정한 최단 경로 성공</a>
 */
public class Main {
    private static List<List<Node>> map;
    private static int[] dist; // a -> b 로 가는 최단거리
    private static final int INF = 200_000_000; // 1000 * 200000
    private static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        StringTokenizer st = new StringTokenizer(s);
        n = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());

        map = new ArrayList<>();
        dist = new int[n + 1];

        for (int i = 0; i <= n; i++) {
            map.add(new ArrayList<>());
        }

        for (int i = 0; i < e; i++) {
            String info = br.readLine();
            st = new StringTokenizer(info);
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map.get(a).add(new Node(b, c));
            map.get(b).add(new Node(a, c));
        }

        String s1 = br.readLine();
        st = new StringTokenizer(s1);
        int p1 = Integer.parseInt(st.nextToken());
        int p2 = Integer.parseInt(st.nextToken());

        int dijkstra1 = 0;
        dijkstra1 += dijkstra(1, p1);
        dijkstra1 += dijkstra(p1, p2);
        dijkstra1 += dijkstra(p2, n);

        int dijkstra2 = 0;
        dijkstra2 += dijkstra(1, p2);
        dijkstra2 += dijkstra(p2, p1);
        dijkstra2 += dijkstra(p1, n);

        int answer = (dijkstra1 >= INF && dijkstra2 >= INF) ? -1 : Math.min(dijkstra1, dijkstra2);

        System.out.println(answer);
    }

    // 다익스트라 알고리즘
    public static int dijkstra(int start, int end) {
        // start -> end 까지 최단거리 저장하는 dist 초기화
        Arrays.fill(dist, INF);

        PriorityQueue<Node> pq = new PriorityQueue<>();
        boolean[] check = new boolean[n + 1];
        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node curNode = pq.poll();
            int currentEnd = curNode.end;

            if (!check[currentEnd]) {
                check[currentEnd] = true;

                for (Node nextNode : map.get(currentEnd)) {
                    // start -> nextNode end 까지 최단거리와 start -> 현재 노드 최단거리 + 다음노드 가중치 비교 후 현재 노드 값 + 다음 노드 가중치가 더 적을 경우 최신화
                    // start -> end 최단거리 계산 할 때 다음 노드를 거치느냐 안거치느냐 확인하는 로직
                    if (!check[nextNode.end] && dist[nextNode.end] > dist[currentEnd] + nextNode.weight) {
                        dist[nextNode.end] = dist[currentEnd] + nextNode.weight;
                        pq.add(new Node(nextNode.end, dist[nextNode.end]));
                    }
                }
            }
        }

        return dist[end];
    }

    private static class Node implements Comparable<Node> {
        int end;
        int weight;

        public Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node node) {
            return this.weight - node.weight;
        }
    }
}
