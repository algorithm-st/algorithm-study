package june.String.단어뒤집기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

    //2
    //I am happy today
    //We want to win the first prize
    public static void main(String[] args) throws IOException {
        InputStreamReader ir = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(ir);
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            String[] strArr = br.readLine().split(" ");
            for (int j = 0; j < strArr.length; j++) {
                StringBuilder sb = new StringBuilder(strArr[j]);
                strArr[j] = sb.reverse().toString();
            }
            String line = String.join(" ", strArr);
            System.out.println(line);
        }
    }
}
