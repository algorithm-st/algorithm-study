package june.이분탐색.랜선자르기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
//        4 11
//802
//743
//457
//539
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int K = Integer.parseInt(s[0]);
        int N = Integer.parseInt(s[1]);
        List<Long> list = new ArrayList<>();
        for (int i = 0; i < K; i++) {
            list.add(Long.parseLong(br.readLine()));
        }
        Collections.sort(list);
        long left = 1;
        long right = list.get(K - 1);
        long mid = 0;
        long max = 0;
        while (left <= right) {
            mid = (left + right) / 2;
            if (count(mid, list) < N) {
                right = mid - 1;
            } else {
                max = Math.max(max, mid);
                left = mid + 1;
            }
        }
        System.out.println(max);
    }

    private static long count(long mid, List<Long> list) {
        int total = 0;
        for (Long num : list) {
            total += num / mid;
        }
        return total;
    }

}
