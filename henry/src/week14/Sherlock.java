package week14;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 문제 설명
 * - 산성 용액 : 1 ~ 1,000,000,000
 * - 알칼리성 용액 : -1 ~  -1,000,000,000
 *
 * - 같은 양의 두 용액을 혼합하여 특성값이 0에 가장 가까운 용액을 만들려고 한다.
 * - 같은 종류라도 특성값이 0에 가장 가까운 용액을 만드는 경우의 수도 존재
 */
public class Sherlock {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int[] answer = new int[2];

        int n = Integer.valueOf(bufferedReader.readLine());
        int[] liquids = new int[n];

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        for(int i=0; i<n; i++){
            liquids[i] = Integer.valueOf(stringTokenizer.nextToken());
        }
        Arrays.sort(liquids);

        int maxSum = Integer.MAX_VALUE;

        int l = 0;
        int r = n-1;

        while(l<r){
            int a = liquids[l];
            int b = liquids[r];

            int sum = a+b;
            if (Math.abs(sum) < maxSum) {
                maxSum = Math.abs(sum);
                answer[0] = a;
                answer[1] = b;
            }

            if(sum==0){
                System.out.println(a + " " + b);
                return;
            }else if(sum > 0){
                r--;
            } else if (sum < 0) {
                l++;
            }
        }

        System.out.println(answer[0] + " "+answer[1]);
    }
}
