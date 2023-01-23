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

            for (int i = 'a'; i <= 'z'; i++) {
                List<Integer> indexListOfChar = getIndexListOfChar(testString, (char) i);

                LengthResult lengthResult = getMinMaxLength(indexListOfChar, requireCount);
                minLength = Math.min(minLength, lengthResult.minLength);
                maxLength = Math.max(maxLength, lengthResult.maxLength);
            }

            if (maxLength == Integer.MIN_VALUE) {
                System.out.println(-1);
                continue;
            }

            System.out.println(minLength + " "+ maxLength);
        }
    }

    private static LengthResult getMinMaxLength(List<Integer> indexListOfChar, int requireCount){
        Integer minLength = Integer.MAX_VALUE;
        Integer maxLength = Integer.MIN_VALUE;

        for (int i = 0; i+requireCount-1 < indexListOfChar.size(); i++) {
            minLength = Math.min(minLength, indexListOfChar.get(i+requireCount-1) - indexListOfChar.get(i)+1);
            maxLength = Math.max(maxLength, indexListOfChar.get(i + requireCount - 1) - indexListOfChar.get(i)+1);

        }
        return new LengthResult(minLength, maxLength);
    }

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