package youngkwon_ned.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * <a href="https://www.acmicpc.net/problem/2231">분배합</a>
 */
public class Solution2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int answer = 0;
        for (int i = 1; i <= n; i++) {
            int m = i;
            int result = i;
            while (m >= 10){
                result += (m % 10);
                m/=10;
            }
            result += m;
            if (result == n){
                answer = i;
                break;
            }
        }

        System.out.println(answer);
    }
}
