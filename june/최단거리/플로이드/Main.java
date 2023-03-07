package june.최단거리.플로이드;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final int INF = 987654321;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int V = Integer.parseInt(br.readLine());
        int E = Integer.parseInt(br.readLine());

        int[][] arr = new int[V + 1][V + 1];
        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                if (i == j) {
                    continue;
                }
                arr[i][j] = INF;
            }
        }
        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            if (arr[a][b] == INF) {
                arr[a][b] = c;
            } else {
                arr[a][b] = Math.min(arr[a][b], c);
            }
        }
//        for (int q = 0; q < V; q++) {
//            for (int i = 1; i <= V; i++) {
//                for (int j = 1; j <= V; j++) {
//                    for (int k = 1; k <= V; k++) {
//                        if (i == j) {
//                            continue;
//                        }
//                        if (arr[i][j] > arr[i][k] + arr[k][j]) {
//                            arr[i][j] = arr[i][k] + arr[k][j];
//                        }
//                    }
//                }
//            }
//        }
        for (int k = 1; k <= V; k++) {
            for (int i = 1; i <= V; i++) {
                for (int j = 1; j <= V; j++) {
                    if (i == j) {
                        continue;
                    }
                    if (arr[i][j] > arr[i][k] + arr[k][j]) {
                        arr[i][j] = arr[i][k] + arr[k][j];
                    }
                }
            }
        }


        for (int i = 1; i <= V; i++) {
            for (int j = 1; j <= V; j++) {
                if (arr[i][j] == INF) {
                    System.out.print(0 + " ");
                } else {
                    System.out.print(arr[i][j] + " ");
                }
            }
            System.out.println();
        }

    }

}
