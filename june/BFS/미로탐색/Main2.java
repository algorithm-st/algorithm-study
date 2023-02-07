package june.BFS.미로탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main2 {

    //4 6
//101111
//101010
//101011
//111011
    public static int[][] miro;
    public static boolean[][] visited;
    public static int N;
    public static int M;
    public static int min = Integer.MAX_VALUE;
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        M = Integer.parseInt(s[1]);
        miro = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            String s1 = br.readLine();
            for (int j = 0; j < M; j++) {
                int num = s1.charAt(j) - '0';
                miro[i][j] = num;
            }
        }
        bfs(0, 0);
        System.out.println(min);
    }

    private static void bfs(int x, int y) {
        LinkedList<int[]> queue = new LinkedList<>();
        queue.add(new int[]{x,y,1});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.pop();
            for (int i = 0; i < 4; i++) {
                int nx = current[0] + dx[i];
                int ny = current[1] + dy[i];

                if (nx == N - 1 && ny == M - 1) {
                    min = Math.min(min, current[2] + 1);
                }

                if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    continue;
                }

                if (miro[nx][ny] == 0 || visited[nx][ny]) {
                    continue;
                }

//                if (miro[current[0]][current[1]] + 1 > miro[nx][ny]) {
//                    continue;
//                }

//                miro[nx][ny] = Math.min(miro[current[0]][current[1]] + 1, miro[nx][ny]);
                queue.add(new int[]{nx, ny, current[2] + 1});
                visited[nx][ny] = true;
            }
        }
    }

}
