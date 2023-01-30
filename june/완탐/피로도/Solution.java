package june.완탐.피로도;

import java.util.Arrays;

public class Solution {
    static int[] arr;
    static boolean[] visited;
    static int max = Integer.MIN_VALUE;

    public int solution(int k, int[][] dungeons) {
        arr = new int[dungeons.length];
        visited = new boolean[dungeons.length];
        dfs(dungeons.length, 0, k, dungeons);
        return max;
    }

    private void dfs(int n, int depth, int k, int[][] dungeons) {
        if (n == depth) {
            cal(k, dungeons);
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                arr[depth] = i;
                visited[i] = true;
                dfs(n, depth + 1, k, dungeons);
                visited[i] = false;
            }
        }
    }

    private void cal(int k, int[][] dungeons){
        int count = 0;
        for(int i=0; i<dungeons.length;i++){
            if(k >= dungeons[arr[i]][0]){
                k -= dungeons[arr[i]][1];
                count++;
            }
        }
        max = Math.max(max, count);
    }
}