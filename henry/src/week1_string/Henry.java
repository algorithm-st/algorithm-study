package week1_string;

import java.util.*;


/**
 * 문자열 게임 2(백준)
 * https://www.acmicpc.net/problem/20437
 */
public class Henry {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int gameCount = scanner.nextInt();

        for (int k = 0; k < gameCount; k++) {
            String testString = scanner.next();
            int requireCount = scanner.nextInt();

            int minLength = Integer.MAX_VALUE;
            int maxLength = Integer.MIN_VALUE;

            /**
             * 알파벳 a부터 z까지 돌며, 최대 최소 길이를 업데이트
             */
            for (int i = 'a'; i <= 'z'; i++) {
                List<Integer> indexListOfChar = getIndexListOfChar(testString, (char) i);

                LengthResult lengthResult = getMinMaxLength(indexListOfChar, requireCount);
                minLength = Math.min(minLength, lengthResult.minLength);
                maxLength = Math.max(maxLength, lengthResult.maxLength);
            }

            // 최대 길이가 업데이트 되지 않았다면 만족하는 문자열이 없다는 의미 -> -1을 출력
            if (maxLength == Integer.MIN_VALUE) {
                System.out.println(-1);
                continue;
            }

            System.out.println(minLength + " "+ maxLength);
        }
    }

    /**
     *
     * @param indexListOfChar 특정 문자가 있는 위치 index List
     * @param requireCount 특정 문자를 포함해야 하는 개수
     * @return index List 에서 계산된 최대, 최소 길이를 반환
     */
    private static LengthResult getMinMaxLength(List<Integer> indexListOfChar, int requireCount){
        Integer minLength = Integer.MAX_VALUE;
        Integer maxLength = Integer.MIN_VALUE;

        for (int i = 0; i+requireCount-1 < indexListOfChar.size(); i++) {
            minLength = Math.min(minLength, indexListOfChar.get(i+requireCount-1) - indexListOfChar.get(i)+1);
            maxLength = Math.max(maxLength, indexListOfChar.get(i + requireCount - 1) - indexListOfChar.get(i)+1);

        }
        return new LengthResult(minLength, maxLength);
    }

    /**
     *
     * @param testString
     * @param c 체크할 문자
     * @return testString 에서 c가 있는 index List
     */
    private static List<Integer> getIndexListOfChar(String testString, char c) {
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < testString.length(); i++) {
            if (c == testString.charAt(i)) {
                result.add(i);
            }
        }
        return  result;
    }
}

class LengthResult{
    Integer minLength;
    Integer maxLength;

    public LengthResult(Integer minLength, Integer maxLength) {
        this.minLength = minLength;
        this.maxLength = maxLength;
    }
}