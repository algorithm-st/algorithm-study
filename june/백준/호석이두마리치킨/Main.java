package june.백준.호석이두마리치킨;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class Main {

    //5 4
//1 3
//4 2
//2 5
//3 2
    static int[] df;
    static int[][] dis;
    static int[][] arr;
    static int[][] answer;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int N = Integer.parseInt(s[0]);
        int M = Integer.parseInt(s[1]);
        arr = new int[N + 1][N + 1];
        dis = new int[N + 1][N + 1];
        df = new int[2];
        answer = new int[N + 1][N + 1];
        for (int i = 0; i < M; i++) {
            String[] s1 = br.readLine().split(" ");
            int a = Integer.parseInt(s1[0]);
            int b = Integer.parseInt(s1[1]);
            arr[a][b] = 1;
            arr[b][a] = 1;
        }

        dfs(1, 0, N);
        ArrayList<AB> abs = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (answer[i][j] == min) {
                    int a = Math.min(i, j);
                    int b = Math.max(i, j);
                    abs.add(new AB(a, b));
                }
            }
        }
        Collections.sort(abs, (o1, o2) -> {
            if (o1.a == o2.a) {
                return o1.b - o2.b;
            }
            return o1.a - o2.a;
        });
        if (abs.isEmpty()) {
            System.out.println(1 + " " + 2 + " " + 0);
            return;
        }
        AB ab = abs.get(0);
        System.out.println(ab.a + " " + ab.b + " " + min * 2);
    }

    private static void dfs(int start, int index, int N) {
        if (index == 2) {
//            System.out.println(df[0] + " " + df[1] + "일때 ");
            count2(N);
        } else {
            for (int i = start; i <= N; i++) {
                df[index] = i;
                dfs(i + 1, index + 1, N);
            }
        }
    }

    private static void count2(int N) {
        int a = df[0];
        int b = df[1];
        int total = 0;
        for (int i = 1; i <= N; i++) {
            int count = 0;
            LinkedList<Integer> q = new LinkedList<>();
            boolean[] visited = new boolean[N + 1];
            q.add(i);
            visited[i] = true;
            boolean flag = false;
            while (!q.isEmpty()) {
                int len = q.size();
                for (int j = 0; j < len; j++) {
                    Integer now = q.pop();
                    if (now == a || now == b) {
                        total += count;
                        flag = true;
                        break;
                    }
                    for (int next : arr[now]) {
                        if (!visited[next]) {
                            visited[next] = true;
                            q.add(next);
                        }
                    }
                }
                if (flag) {
                    break;
                }
                count++;
            }
        }
//        System.out.println("토탈이다 " + total);
        if (total == 0) {
            return;
        }
        min = Math.min(min, total);
        answer[a][b] = total;
    }

    static class AB {

        public int a;
        public int b;

        public AB(int a, int b) {
            this.a = a;
            this.b = b;
        }
    }

}
