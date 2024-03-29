package week9_binarysearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 문제 설명
 * - 집 N개가 수직선 위에 x1,... xN
 * - 공유기 C개, 가장 인접한 두 공유기 사이의 거리를 가능한 크게 설치
 * - 가장 인접한 두 공유기 사이의 거리를 최대로 하는 프로그램
 * - 가장 인접한 두 공유기 사이의 최대 거리를 출력
 *
 * 문제 풀이 설명
 * - 가장 인접한 두 공유기 사이의 최대 거리와 필요한 공유기의 개수는 반비례한다.
 * - 공유기 c개에 대해, 거리가 점점 증가하면서 C개 이상 필요하다가 정확히 C개가 필요한 지점을 이분 탐색으로 찾는다.
 *
 * 왜 이 문제를 선택했나?
 * - 대회 기출 문제라 선택했습니다.
 *
 * 풀이 시간
 * - 1시간 30분
 *
 * 시간 복잡도는?
 * - n * log(집의 최대거리 - 집의 최소 거리)
 */
public class Henry {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = bufferedReader.readLine().split(" ");
        int n = Integer.valueOf(input[0]);
        int c = Integer.valueOf(input[1]);

        int[] x = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = Integer.valueOf(bufferedReader.readLine());
        }
        Arrays.sort(x);


        int min = 1;
        int max = x[n - 1] - x[0];
        int mid;
        int answer = 0;

        while (min <= max) {
            mid = (max + min) / 2;
            if (distanceTest(x, mid, c, n)) {
                answer = mid;
                min = mid + 1;
            }else{
                max = mid - 1;
            }
        }

        System.out.println(answer);
    }

    static boolean distanceTest(int[] x, int testDistance, int c, int n) {
        int count = 1;

        int prevInstallX = x[0];
        for (int i = 1; i < n; i++) {
            if ((x[i] - prevInstallX) >= testDistance) {
                prevInstallX = x[i];
                count++;
            }
        }

        if (count < c) {
            return false;
        }else{
            return true;
        }
    }
}
