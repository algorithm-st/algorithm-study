package june.이분탐색.공유기설치;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        ArrayList<Integer> list = new ArrayList<>();
        String[] s = br.readLine().split(" ");
        int N = Integer.parseInt(s[0]);
        int M = Integer.parseInt(s[1]);
        
        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(br.readLine()));
        }
        Collections.sort(list);

        int left = 1;
        int right = list.get(N - 1) - list.get(0) + 1;
        int mid = 0;
        while (left < right) {
            mid = (left + right) / 2; // mid 는 거리
            if (countWifi(mid, list) >= M) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        System.out.println(left - 1);
    }

    // 거리에 따라 가능한 와이파이 수 리턴
    private static int countWifi(int dis, ArrayList<Integer> list) {
        int start = list.get(0);
        int count = 1;
        for (int i = 1; i < list.size(); i++) {
            if (list.get(i) - start >= dis) {
                count++;
                start = list.get(i);
            }
        }
        return count;
    }
}
