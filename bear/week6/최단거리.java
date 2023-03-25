package bear.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 최단거리 {


    static int v;
    static int e;
    static int k;

    static int INF = Integer.MAX_VALUE;

    static List<Node>[] list;
    static int[] dist;


    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());


        v = Integer.parseInt(st.nextToken());
        e = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(in.readLine());

        list = new ArrayList[v + 1];
        dist = new int[v + 1];

        Arrays.fill(dist, INF);

        for(int i = 1; i < v + 1; i++) {
            list[i] = new ArrayList<>();
        }

        for(int i = 0; i < e; i++) {
            st = new StringTokenizer(in.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            list[start].add(new Node(end, weight));
        }

        StringBuilder sb = new StringBuilder();
        dijk(k);

        for(int i = 1; i < v + 1; i++) {
            if(dist[i] == INF) {
                sb.append("INF");
            }
            else {
                sb.append(dist[i]);
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());


    }

    static void dijk(int start) {

        PriorityQueue<Node> queue = new PriorityQueue<>();
        boolean[] check = new boolean[v + 1];
        queue.add(new Node(start, 0));
        dist[start] = 0;

        while(!queue.isEmpty()){
            Node curNode = queue.poll();
            int cur = curNode.end;

            if(check[cur] == true) continue;
            check[cur] = true;

            for(Node node : list[cur]){
                if(dist[node.end] > dist[cur] + node.weight){
                    dist[node.end] = dist[cur] + node.weight;
                    queue.add(new Node(node.end, dist[node.end]));
                }
            }
        }
    }


    static class Node implements Comparable<Node> {

        int end;
        int weight;

        public Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node n) {
            return this.weight - n.weight;
        }
    }
}
