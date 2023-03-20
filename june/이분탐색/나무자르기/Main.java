package june.이분탐색.나무자르기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        ArrayList<Integer> list = new ArrayList<>();
        String[] s = br.readLine().split(" ");
        int N = Integer.parseInt(s[0]);
        int M = Integer.parseInt(s[1]);

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(list, (o1, o2) -> o2 - o1);

        int left = 0;
        int right = list.get(list.size() - 1);
        int mid;
        int max = 0;
        while (left <= right) {
            mid = (left + right) / 2;
            if (cal(mid, list) < M) {
                right = mid - 1;
            } else {
                max = Math.max(max, mid);
                left = mid + 1;
            }
        }
        System.out.println(max);

    }

    private static long cal(int mid, ArrayList<Integer> list) {
        long total = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) - mid > 0) {
                total += list.get(i) - mid;
            }
        }
        return total;
    }

}
