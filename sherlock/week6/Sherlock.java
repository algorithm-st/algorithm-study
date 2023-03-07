package sherlock.week6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sherlock {
    static char[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        // 1 5,9 9,17 13
        // 4 * (n-1) + 1
        int x = 4 * (n - 1) + 1; //가로
        int y = 4 * (n - 1) + 1; //세로
        arr = new char[x][y];

        setStar(n, 0, 0);

        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                System.out.print(arr[i][j]);
            }
            System.out.println();
        }
    }

    private static void setStar(int n, int x, int y) {
        if (n == 1) {
            arr[x][y] = '*';
            return;
        }

        int len = 4 * (n - 1) + 1;

        // 위, 아래 가로선
        for (int j = y; j < y + len; j++) {
            arr[x][j] = '*';
            arr[x + len - 1][j] = '*';
        }

        // 좌, 우 세로선
        for (int i = x; i < x + len; i++) {
            arr[i][y] = '*';
            arr[i][y + len - 1] = '*';
        }

        // n을 줄여가면 재귀적으로 수행
        setStar(n - 1, x + 2, y + 2);
    }
}
