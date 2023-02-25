package week6_impl2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 왜 이 문제를 선택했나?
 * - 구현 문제에 주로 배열이 많았는데, 배열로 푸는 문제가 아닌 구현문제를 찾아서 선택했습니다.
 *
 * 문제 푼 데 걸린 시간은?
 * - 45분
 *
 * 시간 복잡도는 ? (잘 모르겠다면 예상)
 * - (예상)
 * - N의 범위는 1 ≤ N ≤ 10^9-1  -> 자리수 : 최대 9자리
 * - 한번 분할 시 최소 1자리 씩은 줄어든다. -> DFS depth : 2^9
 * - 분할 하는 경우의 수 : 최대 8C2
 * - 최약의 경우 시간 복잡도 : 8C2 * (2^9) = 14,336
 *
 * 문제 설명
 * - 수 N에 대해 일련의 연산을 거치며, 등장하는 숫자들에서 홀수를 최대한 많이 보고 싶다.
 * - 연산
 *  1. 수의 각 자리 숫자 중에서 홀수의 개수를 종이에 적는다.
 *  2. 수가 한 자리이면 더 이상 아무것도 하지 못하고 종료한다.
 *  3. 수가 두 자리이면 2개로 나눠서 합을 구하여 새로운 수로 생각한다.
 *  4. 수가 세 자리 이상이면 임의의 위치에서 끊어서 3개의 수로 분할하고, 3개를 더한 값을 새로운 수로 생각한다.
 * - 연산 도중 볼 수 있는 최종값 중 최솟값과 최대값을 구하자.
 *
 * 문제 풀이 설명
 * - DFS로 완전 탐색을 하며, 모든 경우의 수를 탐색
 * - 홀수의 숫자를 세어가며 탐색을 진행한다.
 *
 * - 자리수가 1자리의 경우 답을 업데이트
 * - 자리수가 2자리의 경우 두 수를 더한다.
 * - 자리수가 3자리의 경우 숫자를 2 덩어리로 끊는 조합을 찾아서 연산을 수행한다.
 */
public class Henry {
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        // 값 입력
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        Integer input  = Integer.valueOf(bufferedReader.readLine());
        dfs(0, getListFromNumber(input));

        System.out.println(min + " "+max);
    }

    static void dfs(int cnt, List<Integer> numberList) {
        int newCnt = cnt + countOddNumber(numberList);

        if (numberList.size() == 1) {
            max = Math.max(max, newCnt);
            min = Math.min(min, newCnt);
            return;
        }
        if (numberList.size() == 2) {
            int sum = numberList.get(0) + numberList.get(1);
            dfs(newCnt, getListFromNumber(sum));
            return;
        }

        // 3분할
        for (int i = 1; i < numberList.size() - 1; i++) {
            for (int j = i + 1; j < numberList.size(); j++) {
                int subSum1 = getNumberFromList(numberList.subList(0, i));
                int subSum2 = getNumberFromList(numberList.subList(i, j));
                int subSum3 = getNumberFromList(numberList.subList(j, numberList.size()));

                int sum = subSum1 + subSum2 + subSum3;
                dfs(newCnt, getListFromNumber(sum));
            }
        }
    }

    // 숫자 -> 각 자리수 List로 변환
    static List<Integer> getListFromNumber(int number) {
        List<Integer> reverseDigitList = new ArrayList<>();
        do {
            reverseDigitList.add(number % 10);
            number /= 10;
        } while (number != 0);

        // 순서 뒤집기
        List<Integer> result = new ArrayList<>();
        for (int i = reverseDigitList.size() - 1; i >= 0; i--) {
            result.add(reverseDigitList.get(i));
        }
        return result;
    }

    // 각 자리수 List -> 숫자 변환
    static int getNumberFromList(List<Integer> numberList) {
        int result = 0;

        for (int i = 0; i < numberList.size(); i++) {
            result *= 10;
            result += numberList.get(i);
        }

        return result;
    }

    // 홀수 개수 반환
    static int countOddNumber(List<Integer> numberList) {
        int count = 0;
        if (numberList == null) {
            return count;
        }
        for (int i = 0; i < numberList.size(); i++) {
            if (numberList.get(i) % 2 != 0) {
                count++;
            }
        }
        return count;
    }
}
