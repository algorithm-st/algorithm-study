package june.이분탐색.수들의합;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long S = sc.nextLong();
        long sum = 0;
        long i = 1;
        while (sum <= S) {
            sum += i++;
        }
        System.out.println(i - 2);
    }
}

