package june.빽트래킹.N과M6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    static int[] arr;
    static int[] nums;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int N = Integer.parseInt(s[0]);
        int M = Integer.parseInt(s[1]);
        arr = new int[M];
        nums = new int[N];
        visited = new boolean[N];
        String[] s1 = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(s1[i]);
        }
        Arrays.sort(nums);
        dfs(N, M, 0, 0);
        System.out.println(sb);
    }

    private static void dfs(int n, int m, int index, int depth) {
        if (depth == m) {
            for (int val : arr) {
                sb.append(nums[val] + " ");
            }
            sb.append("\n");
            return;
        }
        for (int i = index; i < n; i++) {
            arr[depth] = i;
            dfs(n, m, i+1, depth + 1);
        }
    }
}
