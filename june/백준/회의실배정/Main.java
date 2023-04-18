package june.백준.회의실배정;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
//11
//1 4
//3 5
//0 6
//5 7
//3 8
//5 9
//6 10
//8 11
//8 12
//2 13
//12 14
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int i = sc.nextInt();
        int[][] arr = new int[i][2];
        for (int j = 0; j < i; j++) {
            arr[j][0] = Integer.parseInt(sc.next());
            arr[j][1] = Integer.parseInt(sc.next());
        }
        Arrays.sort(arr, (o1, o2) -> {
            if (o1[1] == o2[1]) {
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });
        int end = arr[0][1];
        int count = 1;
        for (int j = 1; j < arr.length; j++) {
            int[] time = arr[j];
            if (time[0] < end) {
                continue;
            }
            end = time[1];
            count++;
        }
        System.out.println(count);
    }

}
