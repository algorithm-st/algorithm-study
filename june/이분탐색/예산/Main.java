package june.이분탐색.예산;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) throws IOException {
//        4
//120 110 140 150
//485
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        ArrayList<Long> list = new ArrayList<>();

        String[] s1 = br.readLine().split(" ");
        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            list.add(Long.parseLong(s1[i]));
        }
        Collections.sort(list);
        long left = 1;
        long right = list.get(N - 1);
        long mid;
        long max = 0;
        while (left <= right) {
            mid = (left + right) / 2;
            if (allPass(mid, list) <= M) {
                left = mid + 1;
                max = Math.max(max, mid);
            } else {
                right = mid - 1;
            }
        }
        System.out.println(max);
    }

    private static long allPass(long mid, ArrayList<Long> list) {
        long total = 0;
        for (Long money : list) {
            if (money <= mid) {
                total += money;
            } else {
                total += mid;
            }
        }
        return total;
    }

}
