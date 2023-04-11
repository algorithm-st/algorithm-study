package june.백준.캐슬디펜스;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    //5 5 1
//0 0 0 0 0
//0 0 0 0 0
//0 0 0 0 0
//0 0 0 0 0
//1 1 1 1 1
    static int[] position;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N][M];
        position = new int[3];
        visited = new boolean[3];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        // 궁수 위치 설정
//        dfs(N, 0);
    }

}
