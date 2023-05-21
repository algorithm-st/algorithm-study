package week16;

import java.util.*;

/**
 왜 이 문제를 선택했나?
 - 레벨 2 문제 중 풀어보지 못한 유형을 고르기 위해 선택

 문제 푼데 걸린 시간은?
 - 1시간 30분

 시간 복잡도는?
 - n * m * 4 (n,m : 격자의 크기, 4 : 4가지 방향 의미)

 문제 설명
 - 각 칸마다 S, L, R가 써져있는 격자가 있다.격자에서 빛을 쏜다.
 - 각 격자는 특이한 성질이 있다.  S - 직진, L - 좌회전, R - 우회전
 - 빛이 격자의 끝을 넘어갈 경우, 반대쪽 끝으로 다시 돌아온다.
 - 빛의 경로 사이클의 모든 길이들을 배열에 담아 오름차순으로 정렬해 return 해주세요.

 문제 풀이 설명
 - 빛이 격자 바깥으로 나가는 경우는 없기 때문에 한번 들어간 빛은 무조건 사이클이 존재한다.
 - 모든 점에 대해 4가지 방향 테스트하며 사이클을 찾아나간다. (상하좌우)
    - 이때 사이클을 형성하는 좌표+방향은 체크한다.
- 이미 빛이 지나간 좌표, 방향이면 이미 사이클을 형성하는 좌표+방향이므로 테스트에서 제외한다.

 */
public class Henry {
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

            void move(int n, String[][] map) { // 빛의 이동 표현
                if (map[r][c].equals("R")) {// 방향 전환
                    dir = right[dir];
                } else if (map[r][c].equals("L")) {
                    dir = left[dir];
                }

                r = r + dr[dir];
                c = c + dc[dir];
                if (r == n) { // 격자 바깥을 나가는 경우
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

            // 격자 초기화
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
                        if (ch[i][j][k]) { // 해당 좌표 + 방향에 이미 사이클이 존재하면 넘어간다.
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
