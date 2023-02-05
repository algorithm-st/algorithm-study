package week2_complete_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Han {
    static int[] counts = new int[12];
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.valueOf(bufferedReader.readLine() + "");

        makeCount();
        for (int i = 0; i < T; i++) {
            Integer n = Integer.valueOf(bufferedReader.readLine() + "");
            System.out.println(counts[n]);
        }
    }

    static void makeCount() {
        counts[1] = 1;
        counts[2] = 2;
        counts[3] = 4;

        for (int i = 4; i < 12; i++) {
            counts[i] = counts[i-1] + counts[i-2] + counts[i-3];
        }
    }
}
