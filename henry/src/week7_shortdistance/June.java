package week7_shortdistance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class June {
    public static final int ENF = Integer.MAX_VALUE/2;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.valueOf(bufferedReader.readLine());
        int m = Integer.valueOf(bufferedReader.readLine());
        int[][] map = new int[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    map[i][j] = 0;
                }else{
                    map[i][j] = ENF;
                }
            }
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            int from = Integer.valueOf(stringTokenizer.nextToken());
            int to = Integer.valueOf(stringTokenizer.nextToken());
            int weight = Integer.valueOf(stringTokenizer.nextToken());

            map[from][to] = Math.min(map[from][to], weight);
        }


        for (int k = 1; k <= n; k++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    map[i][j] = Math.min(map[i][j], map[i][k] + map[k][j]);
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (map[i][j] == ENF) {
                    System.out.print(0+" ");
                }else{
                    System.out.print(map[i][j]+ " ");
                }
            }
            System.out.println();
        }
    }
}
