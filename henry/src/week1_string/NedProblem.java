package week1_string;

/**
 * 문자열 압축
 * https://school.programmers.co.kr/learn/courses/30/lessons/60057?language=java
 */
public class NedProblem {
    public static void main(String[] args) {
        System.out.println(solution("aabbaccc"));
    }

    /**
     * 문자열은 제일 앞부터 정해진 길이만큼 잘라야 한다.
     */
    public static int solution(String s) {
        int answer = 0;

        int inputStringLength = s.length();
        answer = inputStringLength;

        for (int sliceSize = 1; sliceSize <= inputStringLength/2; sliceSize++) {
            Integer compressedLength = getCompressedLength(s, sliceSize);
            answer = Math.min(answer, compressedLength);
        }

        return answer;
    }

    private static Integer getCompressedLength(String input, int sliceSize) {

        int result = 0;
        for (int checkIndex = 0; checkIndex < input.length(); ) {

            if (canBeCompressed(input, checkIndex, sliceSize)) {

                int compressCount = 1;

                while (canBeCompressed(input, checkIndex, sliceSize)) {
                    compressCount++;
                    checkIndex += sliceSize;
                }
                checkIndex += (sliceSize);

                result += (String.valueOf(compressCount).length() + sliceSize);
            }else{
                if (input.length()-1 < (checkIndex + sliceSize)) {
                    result += (input.length() - checkIndex);
                    break;
                }

                checkIndex += (sliceSize);
                result += sliceSize;
            }
        }
        return result;
    }

    private static Boolean canBeCompressed(String input, int checkIndex, int sliceSize) {
        if ((checkIndex + 2 * sliceSize -1) >= input.length()) {
            return false;
        }

        if (!input.substring(checkIndex, checkIndex + sliceSize)
                .equals(input.substring(checkIndex + sliceSize, checkIndex + 2 * sliceSize))) {
            return false;
        }

        return true;
    }
}
