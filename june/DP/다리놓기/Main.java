package june.DP.다리놓기;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        3
//2 2
//1 5
//13 29
        int[][] arr = new int[30][30];
        for (int i = 0; i < 30; i++) {
            arr[1][i] = i;
        }
        arr[2][2] = 1;
        int N = sc.nextInt();
        for (int i = 0; i < N; i++) {
            int a = Integer.parseInt(sc.next());
            int b = Integer.parseInt(sc.next());
            for (int j = 2; j <= a; j++) {
                for (int k = 2; k <= b; k++) {
                    arr[j][k] = arr[j - 1][k - 1] + arr[j][k - 1];
                }
            }
            System.out.println(arr[a][b]);
        }

    }

}
