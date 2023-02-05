package june.빽트래킹.스도쿠;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int[][] arr = new int[9][9];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for (int i = 0; i < 9; i++) {
            String[] s = br.readLine().split(" ");
            for (int j = 0; j < 9; j++) {
                arr[i][j] = Integer.parseInt(s[j]);
            }
        }

        sudoku(0, 0);
    }

    private static void sudoku(int x, int y) {
        if (x == 9) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < 9; i++) {
                for (int j = 0; j < 9; j++) {
                    sb.append(arr[i][j]).append(" ");
                }
                sb.append("\n");
            }
            System.out.println(sb);
            System.exit(0);
            return;
        }

        if (y == 9) {
            sudoku(x + 1, 0);
            return;
        }

        // 가능한 숫자 넣기
        if (arr[x][y] == 0) {
            for (int i = 1; i <= 9; i++) {
                if (possible(x, y, i)) {
                    arr[x][y] = i;
                    sudoku(x, y + 1);
                }
            }
            arr[x][y] = 0;
            return;
        }
        sudoku(x, y + 1);
    }

    private static boolean possible(int x, int y, int value) {
        for (int i = 0; i < 9; i++) {
            if (arr[x][i] == value) {
                return false;
            }
            if (arr[i][y] == value) {
                return false;
            }
        }

        int rx = (x) / 3;
        int ry = (y) / 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (arr[rx * 3 + i][ry * 3 + j] == value) {
                    return false;
                }
            }
        }
        return true;
    }

}

//0 3 5 4 6 9 2 7 8
//7 8 2 1 0 5 6 0 9
//0 6 0 2 7 8 1 3 5
//3 2 1 0 4 6 8 9 7
//8 0 4 9 1 3 5 0 6
//5 9 6 8 2 0 4 1 3
//9 1 7 6 5 2 0 8 0
//6 0 3 7 0 1 9 5 2
//2 5 8 3 9 4 7 6 0