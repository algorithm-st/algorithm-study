package june.이분탐색.IF문좀대신써줘;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
// 메모리 초과
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int N = Integer.parseInt(s[0]);
        int M = Integer.parseInt(s[1]);
        String[] arr = new String[1000000001];
        int index = 0;
        for (int i = 0; i < N; i++) {
            String[] s1 = br.readLine().split(" ");
            String a = s1[0];
            int b = Integer.parseInt(s1[1]);

            while (index <= b) {
                arr[index] = a;
                index++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            sb.append(arr[Integer.parseInt(br.readLine())]).append("\n");
        }
        System.out.println(sb);
    }

}
//3 8
//WEAK 10000
//NORMAL 100000
//STRONG 1000000
//0
//9999
//10000
//10001
//50000
//100000
//500000
//1000000