package bear.week4;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 안전영역 {

    static int[][] map;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int cnt;
    static int N;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(in.readLine());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {

            StringTokenizer st = new StringTokenizer(in.readLine());

            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }


        int h = Integer.MIN_VALUE;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(map[i][j] > h) {
                    h = map[i][j];
                }
            }
        }

        int k = 0;
        int max = Integer.MIN_VALUE;


        while (k < h) {

            visited = new boolean[N][N];

            for(int i = 0; i < N; i++) {
                for(int j =0 ; j < N; j++) {
                    if(map[i][j] <= k) {
                        visited[i][j] = true;
                    }
                }
            }
            cnt = 0;
            for(int i = 0; i < N; i++) {
                for(int j = 0; j < N; j++) {
                    if(!visited[i][j]) {
                        bfs(i, j);
                        cnt ++;
                    }
                }
            }

            if(cnt > max) {
                max = cnt;
            }
            k++;

        }

        System.out.println(max);




    }

    static void bfs(int i , int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});

        visited[i][j] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int r = cur[0];
            int c = cur[1];

            for(int adj = 0; adj < 4; adj++) {
                int nr = r + dr[adj];
                int nc = c + dc[adj];

                if(nr < 0 || nc < 0 || nr >= N || nc >= N) continue;
                if(visited[nr][nc]) continue;
                queue.offer(new int[]{nr, nc});
                visited[nr][nc] = true;
            }
        }

    }
}
