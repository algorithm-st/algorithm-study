package june.구현.스위치켜고끄기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[101];
        String[] s = br.readLine().split(" ");

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(s[i-1]);
        }

        int stu = Integer.parseInt(br.readLine());
        for (int i = 0; i < stu; i++) {
            // 판별함수
            String[] s1 = br.readLine().split(" ");
            int man = Integer.parseInt(s1[0]);
            int num = Integer.parseInt(s1[1]);
            button(man, num, N, arr);
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i <= N; i++) {
            stringBuilder.append(arr[i]).append(" ");
            if (i % 20 == 0) {
                stringBuilder.append("\n");
            }
        }
        System.out.println(stringBuilder);

    }


    // 토글
    public static void button(int gender, int num, int N, int[] arr) {
        if (gender == 1) {
        // 남학생
            int index = num;
            while (index <= N) {
                toggle(arr, index);
                index += num;
            }
        } else if (gender == 2) {
            // 여학생
            toggle(arr, num);
            int len = Math.min(num - 1, N - num);
            for (int i = 1; i <= len; i++) {
                if (arr[num - i] != arr[num + i]) {
                    break;
                }
                toggle(arr, num - i);
                toggle(arr, num + i);
            }
        }
    }

    public static void toggle(int[] arr, int num) {
        if (arr[num] == 0) {
            arr[num] = 1;
        } else {
            arr[num] = 0;
        }
    }

}
