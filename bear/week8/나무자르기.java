package bear.week8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 나무자르기 {

    static int N;
    static int M;
    static int[] trees;

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        trees = new int[N];

        st = new StringTokenizer(in.readLine());

        for(int i = 0; i < N; i++) {
            trees[i] = Integer.parseInt(st.nextToken());
        }

        long L = 0;
        long R = 2000000000;
        long ans = 0;

        while (L <= R) {
            long mid = (L + R) / 2;
            if(deter((int) mid)) {
                ans = mid;
                L = mid + 1;
            }
            else {
                R = mid - 1;
            }
        }

        System.out.println(ans);



    }

    static boolean deter(int H) {
        long sum = 0;
        for(int i = 0; i < N; i++) {
            if(trees[i] > H) {
                sum += trees[i] - H;
            }
        }

        return sum >= M;
    }
}
