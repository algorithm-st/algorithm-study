package youngkwon_ned.w14;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/77485">행렬 테두리 회전하기</a>
 */
public class Solution {
    private static int[][] maps;
    private static List<Integer> result = new ArrayList<>();

    public static void main(String[] args) {
        Solution s = new Solution();
//        int[][] q = {{2, 2, 5, 4}};
        int[][] q = {{2, 2, 5, 4}, {3, 3, 6, 6}, {5, 1, 6, 3}};
        System.out.println(Arrays.toString(s.solution(6, 6, q)));
    }

    public int[] solution(int rows, int columns, int[][] queries) {
        maps = new int[rows][columns];
        int value = 1;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                maps[i][j] = value++;
            }
        }

        for (int[] query : queries) {
            rotation(query);
        }
        return result.stream().mapToInt(i -> i).toArray();
    }

    private static void printMap() {
        System.out.println("-".repeat(10));
        for (int j = 0; j < maps.length; j++) {
            System.out.println(Arrays.toString(maps[j]));
        }
        System.out.println("-".repeat(10));
    }

    private void rotation(int[] queries) {
        int minY = queries[0] - 1;
        int maxY = queries[2] - 1;
        int minX = queries[1] - 1;
        int maxX = queries[3] - 1;

        Node point1 = new Node(minX, minY);
        Node point2 = new Node(maxX, minY);
        Node point3 = new Node(minX, maxY);
        Node point4 = new Node(maxX, maxY);

        int temp = maps[minY][maxX];
        int min = temp;

        // 윗줄 >>>>>>>
        for (int i = point2.x; i > point1.x; i--) {
            min = Math.min(min, maps[point2.y][i]);
            maps[point2.y][i] = maps[point2.y][i - 1];
        }
        // 왼쪽 줄 위로 올림
        for (int i = point1.y; i < point3.y; i++) {
            min = Math.min(min, maps[i][point1.x]);
            maps[i][point1.x] = maps[i + 1][point1.x];
        }
        // 아랫줄 <<<
        for (int i = point3.x; i < point4.x; i++) {
            min = Math.min(min, maps[point3.y][i]);
            maps[point3.y][i] = maps[point3.y][i + 1];
        }
        // 오른쪽 줄 내림
        for (int i = point4.y; i > point2.y; i--) {
            min = Math.min(min, maps[i][point2.x]);
            maps[i][point2.x] = maps[i - 1][point2.x];
        }
        maps[minY + 1][maxX] = temp;
        result.add(min);
    }

    static class Node {
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }

        int x;
        int y;
    }
}
