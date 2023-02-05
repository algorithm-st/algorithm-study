package week2_complete_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Henry {
    private static int RIGHT = 0;
    private static int DOWN = 1;
    private static int[] dr = {0, 1};
    private static int[] dc = {1, 0};

    private static int n = 0;
    private static int m = 0;
    private static int[][] inputArray;
    private static int[][] chArray;
    private static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s = bufferedReader.readLine();
        StringTokenizer stringTokenizer = new StringTokenizer(s);

        n = Integer.valueOf(stringTokenizer.nextToken());
        m = Integer.valueOf(stringTokenizer.nextToken());
        chArray = new int[n][m];
        answer = Integer.MIN_VALUE;
        inputArray = new int[n][m];
        for (int i = 0; i < n; i++) {
            String rowString = bufferedReader.readLine();
            for (int j = 0; j < m; j++) {
                inputArray[i][j] = Integer.valueOf(rowString.charAt(j)+"");
            }
        }

        chArray[0][0] = 1;
        dfs(0, 0, 1, RIGHT);
        dfs(0, 0, 1, DOWN);

        System.out.println(answer);
    }

    // DFS로 모든 경우를 탐색
    static void dfs(int r, int c, int chCount, int direction) {

        if(visitAll()){
            answer = Math.max(answer, getSum());
            return;
        }

        int nextR = r + dr[direction];
        int nextC = c + dc[direction];
        if (nextR < n && nextC < m && chArray[nextR][nextC] == 0) {
            chArray[nextR][nextC] = chCount;
            dfs(nextR, nextC, chCount, direction);

            Point nextPoint = getNextPoint();
            if(nextPoint != null){
                int i = nextPoint.r;
                int j = nextPoint.c;
                chArray[i][j] = chCount+1;
                dfs(i, j, chCount+1, RIGHT);
                dfs(i, j, chCount+1, DOWN);
                chArray[i][j] = 0;
            }
            chArray[nextR][nextC] = 0;
        }
    }

    // 방문 하지 않은 포인트 1개 반환
    static Point getNextPoint(){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (chArray[i][j] == 0) {
                    return new Point(i, j);
                }
            }
        }
        return null;
    }

    // BFS로 합계 계산
    static int getSum(){
        int result = 0;
        int[][] ch = new int[n][m];

        Queue<Point> queue = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {

                if (ch[i][j] == 0) {
                    int subSum = 0;

                    queue.add(new Point(i, j));

                    while (!queue.isEmpty()) {
                        Point point = queue.poll();
                        subSum = subSum * 10 + inputArray[point.r][point.c];

                        for (int dir = 0; dir < 2; dir++) {
                            int nextR = point.r + dr[dir];
                            int nextC = point.c + dc[dir];

                            if (nextR < n && nextC < m && ch[nextR][nextC] == 0 && chArray[nextR][nextC] == chArray[point.r][point.c]) {
                                ch[nextR][nextC] = 1;
                                queue.add(new Point(nextR, nextC));
                            }
                        }
                    }
                    result += subSum;
                }
            }
        }
        return result;
    }

    static boolean visitAll(){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (chArray[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
    }

    private static class Point{
        public int r;
        public int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
