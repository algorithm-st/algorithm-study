package week2_complete_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Bear {

    static int[] ch = new int[1000];
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.valueOf(bufferedReader.readLine());

        for (int i = 0; i < n; i++) {

            String s = bufferedReader.readLine();
            StringTokenizer stringTokenizer = new StringTokenizer(s, " ");
            Integer input = Integer.valueOf(stringTokenizer.nextToken());
            Integer strike = Integer.valueOf(stringTokenizer.nextToken());
            Integer ball = Integer.valueOf(stringTokenizer.nextToken());

            for (int j = 1; j < 1000; j++) {
                // 유효 숫자 체크
                if (isInvalidNumber(j)) {
                    continue;
                }

                int strikeCount = getStrike(j, input);
                int ballCount = getBall(j, input);

                if (strikeCount == strike && ballCount == ball) {
                    ch[j]++;
                }
            }
        }

        int answer = 0;
        for (int i = 1; i < 1000; i++) {
            if (ch[i] == n) {
                answer++;
            }
        }
        System.out.println(answer);
    }

    static boolean isInvalidNumber(int number) {
        Set<Integer> set = new HashSet<>();

        int numberTmp = number;

        // 중복 숫자 체크
        for (int i = 0; i < 3; i++) {
            if (set.contains(numberTmp % 10)) {
                return true;
            }
            set.add(numberTmp % 10);
            numberTmp/=10;
        }

        // 0이 있는지 체크
        if (set.contains(0)) {
            return true;
        }
        return false;
    }

    static int getStrike(int test, int input) {
        int result = 0;

        int testTmp = test;
        int inputTmp = input;
        for (int i = 0; i < 3; i++) {
            int testDigit = testTmp % 10;
            int inputDigit = inputTmp % 10;

            if (testDigit == inputDigit) {
                result++;
            }

            testTmp = testTmp / 10;
            inputTmp = inputTmp / 10;
        }

        return result;
    }

    static int getBall(int test, int input){
        int result = 0;

        int testTmp = test;
        int inputTmp = input;

        List<Integer> inputDigitList = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            inputDigitList.add(inputTmp % 10);
            inputTmp /= 10;
        }

        for (int i = 0; i < 3; i++) {
            int testDigit = testTmp % 10;
            if (inputDigitList.contains(testDigit) && inputDigitList.get(i) != testDigit) {
                result++;
            }
            testTmp /= 10;
        }
        return result;
    }
}
