package june.이분탐색.휴게소세우기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int N = Integer.parseInt(s[0]);
        int M = Integer.parseInt(s[1]);
        int L = Integer.parseInt(s[2]);
        ArrayList<Integer> list = new ArrayList<>();
        String[] s1 = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(s1[i]));
        }
        list.add(0);
        list.add(L);
        Collections.sort(list);

        int left = 1;
        int right = L - 1;
        int mid=0;
        while (left <= right) {
            mid = (left + right) / 2;
            if (count(mid, list) < M) {
                // 주어진 M보다 작으면 간격을 더 좁혀야함
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(left);
    }

    private static int count(int mid, ArrayList<Integer> list) {
        int count = 0;
        for (int i = 0; i < list.size() - 1; i++) {
            Integer start = list.get(i);
            Integer next = list.get(i + 1);
            count += (next - start - 1) / mid;
        }
        return count - 1;
    }
}
