package june.투포인터.부분합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
//        10 15
//5 1 3 5 10 7 4 9 2 8
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int N = Integer.parseInt(s[0]);
        int S = Integer.parseInt(s[1]);
        int[] arr = new int[N];
        String[] s1 = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(s1[i]);
        }
        int min = Integer.MAX_VALUE;
        int start = 0;

        int end = 0;
        int sum = 0;
        while (true) {

            if (sum >= S) {
                min = Math.min(min, end - start);
                sum -= arr[start++];
            } else if (end == N) {
                break;
            } else {
                sum += arr[end++];
            }
        }

        if (min == Integer.MAX_VALUE) {
            System.out.println(0);
        } else {
            System.out.println(min);
        }
    }

}
