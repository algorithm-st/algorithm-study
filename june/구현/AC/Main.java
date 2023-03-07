package june.구현.AC;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        // T 만큼 반복 나중에 함수로 추출
        for (int i = 0; i < T; i++) {
            solution(br);
        }
    }

    private static void solution(BufferedReader br) throws IOException {
        String p = br.readLine();
        int n = Integer.parseInt(br.readLine());
        String s1 = br.readLine();
        String[] split = s1.substring(1, s1.length() - 1).split(",");
        LinkedList<String> queue = new LinkedList<>();
        for (String s : split) {
            if (!s.equals("")) {
                queue.add(s);
            }
        }
        boolean flag = true; // true 일때 정방향

        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);
            if (c == 'R') {
                flag = !flag;
            } else if (c == 'D') {
                if (queue.isEmpty()) {
                    System.out.println("error");
                    return; // return
                }
                if (flag) {
                    queue.pollFirst();
                } else {
                    queue.pollLast();
                }
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        ArrayList<String> list = new ArrayList<>();
        if (flag) {
            while (!queue.isEmpty()) {
                list.add(queue.pollFirst());
            }
        } else {
            while (!queue.isEmpty()) {
                list.add(queue.pollLast());
            }
        }
        String collect = list.stream()
                .collect(Collectors.joining(","));
        sb.append(collect);
        sb.append("]");
        System.out.println(sb);
    }

}
