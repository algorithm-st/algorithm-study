package week5_impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Han {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));


        for (int i = 0; i < 3; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            int zeroCount = 0;
            for (int j = 0; j < 4; j++) {
                Integer number = Integer.valueOf(stringTokenizer.nextToken());
                if (number == 0) {
                    zeroCount++;
                }
            }

            switch (zeroCount){
                case 0:
                    System.out.println("E");
                    break;
                case 1:
                    System.out.println("A");
                    break;
                case 2:
                    System.out.println("B");
                    break;
                case 3:
                    System.out.println("C");
                    break;
                case 4:
                    System.out.println("D");
                    break;
                default:
                    break;
            }
        }
    }
}
