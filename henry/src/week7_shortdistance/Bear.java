package week7_shortdistance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Bear {

    public static int INF = Integer.MAX_VALUE/3;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        Integer V = Integer.valueOf(stringTokenizer.nextToken());
        Integer E = Integer.valueOf(stringTokenizer.nextToken());
        Integer K = Integer.valueOf(bufferedReader.readLine());

        boolean[] ch = new boolean[V + 1];
        int[] distance = new int[V + 1];
        Arrays.fill(distance, INF);
        List<Edge>[] distanceList = new ArrayList[V + 1];
        for (int i = 1; i <= V; i++) {
            distanceList[i] = new ArrayList<>();
        }

        for (int i = 0; i < E; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            int from = Integer.valueOf(stringTokenizer.nextToken());
            int to = Integer.valueOf(stringTokenizer.nextToken());
            int weight = Integer.valueOf(stringTokenizer.nextToken());

            distanceList[from].add(new Edge(to, weight));
        }


        // 탐색 시작
        Integer currentPoint = K;
        distance[currentPoint] = 0;
        while (true) {

            ch[currentPoint] = true;
            updateDistance(currentPoint, V, distance, distanceList, ch);

            currentPoint = findNextPoint(V, ch, distance);
            if (currentPoint == null) {
                break;
            }
        }

        for (int i = 1; i <= V; i++) {
            if (distance[i] == INF) {
                System.out.println("INF");
            }else{
                System.out.println(distance[i]);
            }
        }
    }

    static void updateDistance(int currentPoint, int v, int[] distance, List<Edge>[] distanceList, boolean[] ch) {
        for (int i = 0; i < distanceList[currentPoint].size(); i++) {
            int to = distanceList[currentPoint].get(i).to;
            int weight = distanceList[currentPoint].get(i).weight;
            distance[to] = Math.min(distance[to], distance[currentPoint] + weight);
        }
    }

    static Integer findNextPoint(int v, boolean[] ch, int[] distance) {
        Integer result = null;

        for (int i = 1; i <= v; i++) {
            if (ch[i]) {
                continue;
            }

            if (result == null) {
                result = i;
                continue;
            }

            if (distance[result] > distance[i]) {
                result = i;
            }
        }

        return result;
    }

    static class Edge{
        int to;
        int weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
}
