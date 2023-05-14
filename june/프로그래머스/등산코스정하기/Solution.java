package june.프로그래머스.등산코스정하기;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Solution {

    public static void main(String[] args) {
        int[] solution = solution(6,
                new int[][]{{1, 2, 3}, {2, 3, 5}, {2, 4, 2}, {2, 5, 4}, {3, 4, 4}, {4, 5, 3}, {4, 6, 1}, {5, 6, 1}},
                new int[]{1, 3}, new int[]{5});
//        System.out.println(solution);
    }

    public static int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = {};
        ArrayList<Node>[] nodes = new ArrayList[n + 1];
        int[] intensity = new int[n + 1];

        for (int i = 1; i < n + 1; i++) {
            nodes[i] = new ArrayList<>();
            intensity[i] = Integer.MAX_VALUE;
        }

        for (int i = 0; i < paths.length; i++) {
            nodes[paths[i][0]].add(new Node(paths[i][1], paths[i][2]));
            nodes[paths[i][1]].add(new Node(paths[i][0], paths[i][2]));

        }
        for (int gate : gates) {
            dijkstra(gate, intensity, nodes);
            for (int summit : summits) {
                System.out.println("intensity = " + intensity[summit]);
            }
            System.out.println("-----------------------");
        }
        return answer;
    }

    public static void dijkstra(int start, int[] intensity, ArrayList<Node>[] nodes) {
        PriorityQueue<Node> q = new PriorityQueue<>(Comparator.comparingInt(o -> o.e));
        boolean[] visited = new boolean[nodes.length];
        q.add(new Node(start, 0));
        intensity[start] = 0;
        visited[start] = true;

        while (!q.isEmpty()) {
            Node now = q.poll();

            for (Node next : nodes[now.v]) {
                if (visited[next.v]) continue;

                if (intensity[next.v] > Math.max(intensity[now.v], next.e)) {
                    intensity[next.v] = Math.max(intensity[now.v], next.e);
                    visited[next.v] = true;
                    q.add(new Node(next.v, intensity[next.v]));
                }
            }
        }
    }
}

class Node {

    int v;
    int e;

    public Node(int v, int e) {
        this.v = v;
        this.e = e;
    }
}
