package bear.week7;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 타일링 {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());


        int[] arr = new int[1001];
        arr[1] = 1;
        arr[2] = 2;
        arr[3] = 3;

        for(int i = 4; i < 1001; i++) {
            arr[i] = (arr[i-1] + arr[i-2]) % 10007;
        }
        System.out.println(arr[n]);

    }
}
