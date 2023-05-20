package week16;

import java.util.*;

public class Henry {
    /**
     문제 풀이
     1. 모든 점에 대해 4방향 테스트
     2. 각점에 대해 방향 체크를 하며, 이전에 사이클이 지나간 방향이면 제외
     */
    static class Solution {
        static String[][] map;
        static boolean[][][] ch;
        static int n;
        static int m;

        // UP, RIGHT, DOWN, LEFT
        static int UP = 0;
        static int RIGHT = 1;
        static int DOWN = 2;
        static int LEFT = 3;
        static int[] dr = {-1, 0, 1, 0};
        static int[] dc = {0, 1, 0, -1};

        // 방향 전환
        static int[] right = {RIGHT, DOWN, LEFT, UP};
        static int[] left = {LEFT, UP, RIGHT, DOWN};

        static class Light {
            int r;
            int c;
            int dir;

            Light(int r, int c, int dir) {
                this.r = r;
                this.c = c;
                this.dir = dir;
            }

            void move(int n, String[][] map) {
                if (map[r][c].equals("R")) {
                    dir = right[dir];
                } else if (map[r][c].equals("L")) {
                    dir = left[dir];
                }

                r = r + dr[dir];
                c = c + dc[dir];
                if (r == n) {
                    r = 0;
                } else if (r == -1) {
                    r = n - 1;
                } else if (c == m) {
                    c = 0;
                } else if (c == -1) {
                    c = m - 1;
                }
            }
        }

        public int[] solution(String[] grid) {
            List<Integer> answer = new ArrayList<>();
            n = grid.length;
            m = grid[0].length();

            // 배열 초기화
            map = new String[n][m];
            ch = new boolean[n][m][4];
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    map[i][j] = grid[i].substring(j, j + 1);
                }
            }

            // 모든 점을 돌며 4방향에 대해 사이클을 체크한다.
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    for (int k = 0; k < 4; k++) { // 방향
                        if (ch[i][j][k]) { // 이미 사이클이 존재
                            continue;
                        }

                        // 같은 위치, 방향 나올때까지 빛이 이동
                        int pathCnt = 0;
                        Light light = new Light(i, j, k);
                        do {
                            pathCnt++;
                            ch[light.r][light.c][light.dir] = true;
                            light.move(n, map);
                        } while (!(light.r == i && light.c == j && light.dir == k));

                        answer.add(pathCnt);
                    }
                }
            }

            answer.sort((a, b) -> {
                return a - b;
            });

            return answer.stream().mapToInt(Integer::intValue).toArray();
        }
    }
}
