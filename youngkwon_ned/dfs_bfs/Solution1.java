package youngkwon_ned.dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * <a href="https://www.acmicpc.net/problem/2667">단지번호붙이기</a>
 */
public class Solution1 {

    private static int houseCount, n;
    private static boolean[][] VISIT;
    private static List<Integer> list;
    private static int[][] house;

    private	static int[] dx = {-1, 0, 1, 0};
    private	static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        house = new int[n][n];
        VISIT = new boolean[n][n];
        list = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                int i1 = Integer.parseInt(line.substring(j, j + 1));
                house[i][j] = i1;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (house[i][j] == 1 && !VISIT[i][j]) {
                    houseCount = 0;
                    bfs(i, j);
                    list.add(houseCount);
                }
            }
        }
        Collections.sort(list);
        System.out.println(list.size());
        list.forEach(System.out::println);
    }

    private static class Node {

        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static void bfs(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y));
        VISIT[x][y] = true;

        while (!q.isEmpty()){
            Node now = q.poll();
            houseCount++;

            for (int i = 0; i < 4; i++) {
                int nextX = now.x + dx[i];
                int nextY = now.y + dy[i];

                if (nextX < 0 || nextY < 0 || nextX >= n || nextY >= n){
                    continue;
                }

                if (house[nextX][nextY] == 0 || VISIT[nextX][nextY]){
                    continue;
                }
                q.offer(new Node(nextX, nextY));
                VISIT[nextX][nextY] = true;
            }
        }
    }
}
