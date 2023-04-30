package sherlock.week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Sherlock {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int diff = Integer.MAX_VALUE;
        int left = 0;
        int right = 0;
        Arrays.sort(arr);

        // -99 -2 -1 4 98

        int i = 0;
        int j = n - 1;

        int absSum;
        int sum;

        while (i < j) {
            sum = arr[i] + arr[j];
            absSum = Math.abs(sum);
            if (absSum < diff) {
                diff = absSum;
                left = arr[i];
                right = arr[j];
            }
            if (sum > 0) {
                j--;
            } else {
                i++;
            }
        }

        System.out.println(left + " " + right);

    }
}
