package june.이분탐색.좋다;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class Main {

    public static void main(String[] args) throws IOException {
//10
//1 2 3 4 5 6 7 8 9 10
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String[] s = br.readLine().split(" ");
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            list.add(Integer.parseInt(s[i]));
        }
        Collections.sort(list);
        int answer = 0;
        for (int i = 0; i < N; i++) {
            int left = 0;
            int right = N - 1;
            while (true) {
                if (left == i) {
                    left++;
                }
                if (right == i) {
                    right--;
                }
                if (left >= right) {
                    break;
                }

                if (list.get(i) < list.get(left) + list.get(right)) {
                    right--;
                } else if (list.get(i) > list.get(left) + list.get(right)) {
                    left++;
                } else {
                    answer++;
                    break;
                }
            }
        }
        System.out.println(answer);

    }

}
