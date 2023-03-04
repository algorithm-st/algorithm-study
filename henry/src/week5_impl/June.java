package week5_impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 사람들은 자기보다 큰 사람이 왼쪽에 몇명이 있었는지만을 기억한다.
 * N명의 사람들이 있고, 사람들의 키는 1부터 N까지 모두 다르다.
 *
 * 입력
 * - N <= 10
 * - 키가 1인 사람부터 차례대로 자기보다 키가 큰 사람이 왼쪽에 몇 명 있는지 주어진다.
 */
public class June {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        Integer n = Integer.valueOf(bufferedReader.readLine());
        int[] line = new int[n + 1];

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for (int i = 1; i <= n; i++) {
            Integer leftTallerPersonCount = Integer.valueOf(stringTokenizer.nextToken());

            int count = 0;
            for (int j = 1; j <= n; j++) {
                // 반복문 끝내는 조건
                if (line[j] == 0 && count == leftTallerPersonCount) {
                    line[j] = i;
                    break;
                }

                // 카운트 증가(빈 자리는 자기보다 큰 숫자로 채워질 예정)
                if (line[j] == 0 || line[j] > i) {
                    count++;
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            System.out.print(line[i] + " ");
        }
    }
}
