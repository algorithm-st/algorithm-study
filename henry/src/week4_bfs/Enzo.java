package week4_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Enzo {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.valueOf(bufferedReader.readLine());
        List<Node> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new Node(i));
        }
        int[] answer = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            String[] line = bufferedReader.readLine().split(" ");
            Node node1 = graph.get(Integer.valueOf(line[0]));
            Node node2 = graph.get(Integer.valueOf(line[1]));
            node1.addNeighborNode(node2);
            node2.addNeighborNode(node1);
        }

        List<Integer> circularNodeNumberList = findCircularNodeList(n, graph);
        List<Node> startNodeList = new ArrayList<>();
        circularNodeNumberList.stream().forEach(circularNodeNumber ->{
            startNodeList.add(graph.get(circularNodeNumber));
        });

        bfs(n, answer, startNodeList);

        for (int i = 1; i <= n; i++) {
            System.out.print(answer[i] + " ");
        }
    }

    static boolean[] isCircular;
    static boolean flag;
    private static List<Integer> findCircularNodeList(int n, List<Node> graph) {
        isCircular = new boolean[n + 1];
        flag = false;

        List<Integer> circularNodeNumberList = new ArrayList<>();
        boolean[] isCircularTmp = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            if (flag) {
                break;
            }

            boolean[][] tempMap = new boolean[n + 1][n + 1];
            for (Node node : graph) {
                for (Node neighborNode : node.neighborNodeList) {
                    tempMap[node.number][neighborNode.number] = true;
                    tempMap[neighborNode.number][node.number] = true;
                }
            }
            isCircularTmp[i] = true;
            isCircularNodeNumberListWithDfs(tempMap, n, i, i, isCircularTmp);
        }

        for (int i = 1; i <= n; i++) {
            if (isCircular[i]) {
                circularNodeNumberList.add(i);
            }
        }

        return circularNodeNumberList;
    }

    static void isCircularNodeNumberListWithDfs(boolean[][] tempMap, int n, int currentNodeNumber, int startNodeNumber, boolean[] isCircularTmp) {
        if (flag) {
            return;
        }
        for (int j = 1; j <= n; j++) {
            if (tempMap[currentNodeNumber][j]) {

                if (j == startNodeNumber) {
                    flag = true;
                    for (int k = 1; k <= n; k++) {
                        if (isCircularTmp[k]) {
                            isCircular[k] = true;
                        }
                    }
                    return;
                }

                tempMap[currentNodeNumber][j] = false;
                tempMap[j][currentNodeNumber] = false;
                isCircularTmp[j] = true;
                isCircularNodeNumberListWithDfs(tempMap, n, j, startNodeNumber, isCircularTmp);
                isCircularTmp[j] = false;
                tempMap[currentNodeNumber][j] = true;
                tempMap[j][currentNodeNumber] = true;
            }
        }
    }

    static void bfs(int n, int[] answer, List<Node> startNodeList) {
        boolean[] ch = new boolean[n + 1];
        Queue<Node> queue = new LinkedList<>();
        for (Node startNode : startNodeList) {
            ch[startNode.number] = true;
            queue.offer(startNode);
        }

        while (!queue.isEmpty()) {
            Node currentNode = queue.poll();
            for (Node neighborNode : currentNode.neighborNodeList) {
                if (ch[neighborNode.number]) {
                    continue;
                }
                ch[neighborNode.number] = true;
                answer[neighborNode.number] = answer[currentNode.number] + 1;
                queue.add(neighborNode);
            }
        }
    }

    static class Node{
        int number;
        List<Node> neighborNodeList;

        public Node(int number) {
            this.number = number;
            this.neighborNodeList = new ArrayList<>();
        }

        public void addNeighborNode(Node node) {
            this.neighborNodeList.add(node);
        }
    }
}