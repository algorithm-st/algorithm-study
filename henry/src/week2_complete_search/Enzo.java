package week2_complete_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Enzo {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.valueOf(bufferedReader.readLine() + "");

        for (int i = 1; i <= 1000000; i++) {
            if (n == (i + getDigitSum(i))) {
                System.out.println(i);
                return;
            }
        }

        System.out.println(0);
    }

    static int getDigitSum(int number) {
        int result = 0;
        int numberTmp = number;

        while (numberTmp != 0) {
            result += numberTmp % 10;
            numberTmp /= 10;
        }

        return result;
    }
}
