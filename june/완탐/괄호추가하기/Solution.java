package june.완탐.괄호추가하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    static int N;
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        String s = bufferedReader.readLine();

        solve(2, s.charAt(0) - '0', s);

        System.out.println(max);
    }

    // 괄호를 치는 경우와 안치는 경우로 나뉜다
    // 괄호를 안치면 해당 인덱스 숫자와 연산하고 인덱스 증가
    // 괄호를 치면 괄호친 숫자 연산부터 진행하고 이전 sum에 더한 뒤 인덱스 증가
    // 인덱스가 N보다 크면 max 업데이트
    public static void solve(int i, int sum, String s) {
        // 인덱스가 N보다 크면 max 업데이트
        if (i >= N) {
            max = Math.max(max, sum);
            return;
        }
        // 괄호를 안치면 해당 인덱스 숫자와 연산하고 인덱스 증가
        solve(i+2, cal(sum, s.charAt(i-1), s.charAt(i)-'0'), s);

        // 괄호를 치면 괄호친 숫자 연산부터 진행하고 이전 sum에 더한 뒤 인덱스 증가
        if (i + 2< N) {
            int right = cal(s.charAt(i) - '0', s.charAt(i + 1), s.charAt(i + 2) - '0');
            int left = cal(sum, s.charAt(i - 1), right);
            solve(i+4, left, s);
        }
    }

    public static int cal(int a, char operator, int b) {
        if (operator == '*') {
            return a * b;
        } else if (operator == '+') {
            return a + b;
        } else {
            return a - b;
        }
    }
}
