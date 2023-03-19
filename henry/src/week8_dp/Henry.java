package week8_dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

/**
 * 문제 설명
 * - 1번부터 N번까지 학생이 있다.
 * - 학생마다 최대 M개의 블록을 가지고 있다.
 * - 각 학생이 들고 있는 블록의 높이는 서로 다르다.
 * - 1번부터 N번까지의 학생들이 가진 블록을 차례대로 사용하여 하나의 탑을 쌓는다.
 * - 한 학생은 블록을 사용하지 않거나, 1개 사용할 수 있다.
 * - 1번부터 N번까지의 학생들이 가지고 있는 블록들에  대한 정보가 주어졌을 때, 높이가 정확히 H인 탑을 만들 수 있는 경우의 수를 계산하는 프로그램을 작성하시오.
 *
 * 문제 풀이 설명
 * 0 1 2 3 4 5
 * 0 0 1 1 0 1
 * 0 0 1 2 0 3
 * 0 1 2 4 3 6
 *  1. 이전 블록 + 새로운 블럭으로 쌓을수 있는 높이 경우의 수 업데이트
 *  2. 새로운 블럭 높이에 해당하는 카운트 1 더하기
 *
 * 왜 이 문제를 선택했나?
 * - 단순 점화식이 아니라 추가적으로 해줘야 하는 작업이 더 있어서
 *
 * 문제 푸는데 걸린 시간?
 * - 1시간 30분
 *
 * 시간 복잡도
 * - N*M*H
 */
public class Henry {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        // 초기화
        int N = Integer.valueOf(stringTokenizer.nextToken());
        int M = Integer.valueOf(stringTokenizer.nextToken());
        int H = Integer.valueOf(stringTokenizer.nextToken());
        long[][] dpArray = new long[N][H + 1];
        List<Student> studentList = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            List<Integer> blockList = new ArrayList<>();
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            while (stringTokenizer.hasMoreTokens()) {
                blockList.add(Integer.valueOf(stringTokenizer.nextToken()));
            }

            studentList.add(new Student(blockList));
        }

        // 첫번째 학생
        for (Integer block : studentList.get(0).blockList) {
            dpArray[0][block] = 1;
        }


        // 2번째 학생부터
        for (int i = 1; i < studentList.size(); i++) {
            // 이전 단계 복사
            dpArray[i] = Arrays.copyOf(dpArray[i - 1], dpArray[i - 1].length);

            List<Integer> blockList = studentList.get(i).blockList;
            for (Integer block : blockList) {
                for (int j = block + 1; j <= H; j++) {
                    dpArray[i][j] = dpArray[i][j] + dpArray[i - 1][j - block];
                }

                dpArray[i][block]++;
            }

            for (int j = 0; j <= H; j++) {
                dpArray[i][j] = dpArray[i][j] % 10007;
            }
        }

        System.out.println(dpArray[N - 1][H]);
    }

    public static class Student{
        List<Integer> blockList;

        public Student(List<Integer> blockList) {
            this.blockList = blockList;
        }
    }
}
