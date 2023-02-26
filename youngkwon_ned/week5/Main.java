package youngkwon_ned.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * <a href="https://www.acmicpc.net/problem/5430">AC</a>
 */
// 시간 초과
public class Main {
    private static StringBuilder sb = new StringBuilder();
    private static ArrayDeque<Integer> deque;
    private static List<Integer> result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringTokenizer st;

        for (int i = 0; i < t; i++) {
            deque = new ArrayDeque<>();
            String operation = br.readLine();
            int n = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine(), "[],");
            for (int j = 0; j < n; j++) {
                deque.add(Integer.valueOf(st.nextToken()));
            }

            boolean isError = AC(operation);
            if (!isError) {
                appendResult();
//                sb.append(result).append("\n");
            }
        }

        System.out.println(sb);
    }

    private static void appendResult() {
        sb.append("[");
        for (int i = 0; i < result.size(); i++) {
            Integer integer = result.get(i);
            if (i < result.size()-1){
                sb.append(integer).append(",");
                continue;
            }
            sb.append(integer);
        }
        sb.append("]\n");
    }

    private static boolean AC(String operation) {
        boolean isReverse = false;

        for (int j = 0; j < operation.length(); j++) {
            char c = operation.charAt(j);
            if (c == 'R') {
                isReverse = !isReverse;
                continue;
            }

            if (c == 'D') {
                if (deque.isEmpty()) {
                    sb.append("error\n");
                    return true;
                }

                // 반대 방향
                if (isReverse) {
                    deque.pollLast();
                    continue;
                }

                // 정방향
                deque.pollFirst();
            }
        }

        if (isReverse) {
            result = new ArrayList<>(deque);
            Collections.reverse(result);
            return false;
        }

        result = new ArrayList<>(deque);
        return false;
    }
}
