package week9_binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 문제 설명
 * - 휴게소 N개가 이미 있다
 * - 휴게소를 M개 더 지어서 휴계소가 없는 구간의 길이의 최댓값을 최소로 하려고 한다.
 */
public class June {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        int N = Integer.valueOf(stringTokenizer.nextToken());
        int M = Integer.valueOf(stringTokenizer.nextToken());
        int L = Integer.valueOf(stringTokenizer.nextToken()); // 고속 도로의 길이

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        List<Integer> restList = new ArrayList<>();
        restList.add(0);
        for (int i = 0; i < N; i++) {
            restList.add(Integer.valueOf(stringTokenizer.nextToken()));
        }
        restList.add(L);
        Collections.sort(restList);


        int max = L;
        int min = 1;
        int answer = Integer.MAX_VALUE;

        while (min <= max) {
            int mid = (max + min) / 2;

            if (getCount(N, M, L, mid, restList) > M) {
                min = mid + 1;
            }else{
                answer = Math.min(answer, mid);
                max = mid - 1;
            }
        }

        System.out.println(answer);
    }

    private static int getCount(int N, int M, int L, int testDistance, List<Integer> restList){
        int restCnt = 0;

        for (int i = 0; i <= N; i++) {
            restCnt += (restList.get(i + 1) - restList.get(i) - 1) / testDistance;
        }

        return restCnt;
    }
}
