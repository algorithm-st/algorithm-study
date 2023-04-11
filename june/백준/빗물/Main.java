package june.백준.빗물;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int H = Integer.parseInt(s[0]);
        int W = Integer.parseInt(s[1]);
        int[] arr = new int[W];
        String[] s1 = br.readLine().split(" ");
        for (int i = 0; i < W; i++) {
            arr[i] = Integer.parseInt(s1[i]);
        }
        int count = 0;
        int left = 0;
        int right = 0;
        for (int i = 1; i < W - 1; i++) {
            left = right = 0;
            // 왼쪽에서 가장 높은 건물의 높이
            for (int j = 0; j < i; j++) {
                left = Math.max(arr[j], left);
            }
            // 오른쪽에서 가장 높은 건물의 높이
            for (int j = i + 1; j < W; j++) {
                right = Math.max(arr[j], right);
            }
            // 더 낮은 건물을 기준으로 현재 인덱스에 모이는 빗물
            if (arr[i] < left && arr[i] < right) {
                count += Math.min(left, right) - arr[i];
            }
        }
        System.out.println(count);
    }

}
