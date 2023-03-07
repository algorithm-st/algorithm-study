package june.구현.별찍기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static boolean[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int size = 1 + (N-1) * 4;
        arr = new boolean[size + 1][size + 1];

        solution(1, size);
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size; j++) {
                if (arr[i][j]) {
                    sb.append("*");
                } else {
                    sb.append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    private static void solution(int start, int end) {
        if (start > end) {
            return;
        }
        for (int i = start; i <= end; i++) {
            arr[i][start] = true;
            arr[i][end] = true;
            arr[start][i] = true;
            arr[end][i] = true;
        }
        solution(start + 2, end - 2);
    }

}
