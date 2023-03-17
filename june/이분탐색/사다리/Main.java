package june.이분탐색.사다리;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        double a = Double.parseDouble(input[0]);
        double b = Double.parseDouble(input[1]);
        double c = Double.parseDouble(input[2]);

        double left = 0;
        double right = Math.min(a, b); // 대각선 중 작은거 -> 아무리 길어도 대각선보단 작음
        double mid = 0;

        while (right - left >= 0.001) {
            mid = (left + right) / 2;
            double h1 = Math.sqrt(Math.pow(a, 2) - Math.pow(mid, 2));
            double h2 = Math.sqrt(Math.pow(b, 2) - Math.pow(mid, 2));
            double cPrime = (h1 * h2) / (h1 + h2);
            if (c < cPrime) {
                left = mid;
            } else {
                right = mid;
            }
        }

        System.out.println(String.format("%.3f", mid));
    }

}
