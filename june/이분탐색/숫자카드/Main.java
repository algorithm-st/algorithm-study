package june.이분탐색.숫자카드;

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
        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(st.nextToken()));
        }
        Collections.sort(list);
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < M; i++) {
            sb.append(binary(Integer.parseInt(st.nextToken()), list)).append(" ");
        }
        System.out.println(sb);
    }

    private static int binary(int val, ArrayList<Integer> list) {
        int left = 0;
        int right = list.size() - 1;
        int mid;
        while (left <= right) {
            mid = (left + right) / 2;
            if (list.get(mid) < val) {
                left = mid + 1;
            } else if (list.get(mid) > val) {
                right = mid - 1;
            } else {
                return 1;
            }
        }
        return 0;
    }

}
