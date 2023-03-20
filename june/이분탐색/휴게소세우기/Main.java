package june.이분탐색.휴게소세우기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int N = Integer.parseInt(s[0]);
        int M = Integer.parseInt(s[1]);
        Long L = Long.parseLong(s[2]);
        ArrayList<Long> list = new ArrayList<>();
        String[] s1 = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            list.add(Long.parseLong(s1[i]));
        }
        list.add(0L);
        list.add(L);
        Collections.sort(list);
        long left = 1;
        long right = L - 1;
        long mid;

        while (left <= right) {
            mid = (left + right) / 2;
            if (count(mid, list) < M) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(left);
    }

    private static long count(long mid, ArrayList<Long> list) {
        long count = 0;
        for (int i = 0; i < list.size() - 1; i++) {
            count += (list.get(i + 1) - list.get(i) - 1) / mid;
        }
        return count - 1;
    }

}
