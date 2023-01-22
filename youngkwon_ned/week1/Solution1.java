package youngkwon_ned.week1;

/**
 * 문자열 압축
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/60057">...</a>
 */
public class Solution1 {

    public static void main(String[] args) {
        int result = solution("abcabcabcabcdededededede");
//        int result = solution("abacasdas");
//        int result = solution("aaaaaaaaa");
        System.out.println("result = " + result);
    }

    private static int solution(String s) {
        int answer = s.length();
        int length = s.length() / 2;
        for (int i = 1; i <= length; i++) {
            // 중복인지 검사할 문자열
            String str = s.substring(0, i);
            // 몇개가 중복인지 카운트
            int count = 1;
            StringBuilder sb = new StringBuilder();
            for (int j = i; j <= s.length(); j += i) {
                int end = Math.min((j + i), s.length());
                String nextStr = s.substring(j, end);
                if (str.equals(nextStr)) {
                    count++;
                } else {
                    if (count >= 2) {
                        sb.append(count);
                    }
                    sb.append(str);
                    count = 1;
                    str = nextStr;
                }
            }
            sb.append(str);
            answer = Math.min(answer, sb.toString().length());
        }
        return answer;
    }
}
