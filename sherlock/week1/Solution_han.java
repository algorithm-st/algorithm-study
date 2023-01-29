package sherlock.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution_han {
    public void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());//테스트케이스

        for (int i = 0; i < T; i++) {
            StringBuilder sb = new StringBuilder();
            String input = br.readLine();
            String[] split = input.split(" ");

            for (String word : split) {
                StringBuilder reverseWord = new StringBuilder(word);
                sb.append(reverseWord.reverse());
                sb.append(' ');
            }
            sb.deleteCharAt(sb.length() - 1);
            System.out.println(sb);
        }
    }
}
