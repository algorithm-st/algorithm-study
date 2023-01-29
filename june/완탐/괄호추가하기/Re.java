package june.완탐.괄호추가하기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Re {

    static int N;
    static int max = Integer.MIN_VALUE;
    static String str;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(bufferedReader.readLine());
        str = bufferedReader.readLine();

        solution(2, str.charAt(0) - '0');
        System.out.println(max);
    }

    private static void solution(int index, int sum) {

        if (index >= N) {
            max = Math.max(max, sum);
            return;
        }

        // 괄호 없을때
        solution(index + 2, cal(sum, str.charAt(index - 1), str.charAt(index) - '0'));

        if (index + 2 < N) {
            // 괄호 있으면
            int right = cal(str.charAt(index) - '0', str.charAt(index + 1), str.charAt(index + 2) - '0');
            solution(index + 4, cal(sum, str.charAt(index - 1), right));
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

//    9
//3+8*7-9*2

}
