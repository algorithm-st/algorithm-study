package june.빽트래킹.N과M4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[] arr;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int N = Integer.parseInt(s[0]);
        int M = Integer.parseInt(s[1]);
        arr = new int[M];
        visited = new boolean[N];
        dfs(N, M, 0, 0);
        System.out.println(sb);
    }

    private static void dfs(int n, int m, int index, int depth) {
        if (depth == m) {
            for (int val : arr) {
                sb.append(val + " ");
            }
            sb.append("\n");
            return;
        }
        for (int i = index; i < n; i++) {
            arr[depth] = i + 1;
            dfs(n, m, i, depth + 1);
        }
    }
}
