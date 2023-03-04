package week4_bfs;

import java.io.*;
import java.util.*;

public class Enzo {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(System.out));

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
            bufferedWriter.write(answer[i] + " ");
        }

        bufferedWriter.flush();
        bufferedWriter.close();
    }

    static boolean[] isCircular;
    static boolean flag;
    private static List<Integer> findCircularNodeList(int n, List<Node> graph) {
        isCircular = new boolean[n + 1];
        flag = false;

        List<Integer> circularNodeNumberList = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            if (flag) {
                break;
            }
            boolean[] isVisit = new boolean[n + 1];
            boolean[] isCircularTmp = new boolean[n + 1];

            isVisit[i] = true;
            isCircularTmp[i] = true;
            isCircularNodeNumberListWithDfs(n, graph.get(i),-1,  i, isCircularTmp, isVisit);
        }

        for (int i = 1; i <= n; i++) {
            if (isCircular[i]) {
                circularNodeNumberList.add(i);
            }
        }

        return circularNodeNumberList;
    }

    static void isCircularNodeNumberListWithDfs(int n, Node currentNode, int prevNodeNumber, int startNodeNumber, boolean[] isCircularTmp, boolean[] isVisit) {
        for (Node neighbotNode : currentNode.neighborNodeList) {
            if (flag) {
                return;
            }

            if (neighbotNode.number == prevNodeNumber) {
                continue;
            }
            if (neighbotNode.number == startNodeNumber) {
                flag = true;
                for (int k = 1; k <= n; k++) {
                    if (isCircularTmp[k]) {
                        isCircular[k] = true;
                    }
                }
                continue;
            }
            if (isVisit[neighbotNode.number]) {
                continue;
            }

            isCircularTmp[neighbotNode.number] = true;
            isVisit[neighbotNode.number] = true;
            isCircularNodeNumberListWithDfs(n, neighbotNode, currentNode.number, startNodeNumber, isCircularTmp, isVisit);
            isCircularTmp[neighbotNode.number] = false;
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