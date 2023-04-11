package june.투포인터.회전초밥;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) throws IOException {
//        8 30 4 30
//7
//9
//7
//30
//2
//7
//9
//25
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int N = Integer.parseInt(s[0]);
        int d = Integer.parseInt(s[1]);
        int k = Integer.parseInt(s[2]);
        int c = Integer.parseInt(s[3]);
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        int max = 0;
        int count = 0;
        int[] temp = new int[d+1];
        for (int i = 0; i < k; i++) {
            if (temp[arr[i]] == 0) {
                count++;
            }
            temp[arr[i]]++;
        }
        for (int i = 0; i < N; i++) {
            if (max <= count) {
                if (temp[c] == 0) {
                    max = count + 1;
                } else {
                    max = count;
                }
            }
            temp[arr[i]]--;
            if (temp[arr[i]] == 0) {
                count--;
            }
            if (temp[arr[(i+k)%N]] == 0) {
                count++;
            }
            temp[arr[(i+k)%N]]++;
        }
        System.out.println(max);
    }

}
