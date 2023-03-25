package june.이분탐색.정수제곱근;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        long left = 0;
        long right = n;
        long answer = Long.MAX_VALUE;

        while (left <= right) {
            long mid = (left + right) / 2;
            long value = (long) Math.pow(mid, 2);
            if (value >= 0 && value >= n) {
                answer = Math.min(answer, mid);
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(answer);
    }
}

