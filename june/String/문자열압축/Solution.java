package june.String.문자열압축;

public class Solution {
    // 요구사항
    // 문자열을 잘라 압축하여 표현한 문자열 중 가장 짧은 것의 길이
    // abcabcabcabcdededededede -> 2abcabc2dedede
    // 1부터 길이의 절반까지 반복문 돌면서
    // 앞에서부터 갯수만큼 같은지 확인하고
        // 같으면 다음 +i 문자열부터 확인
        // 다르면 +1 문자열부터 확인
    // 총길이 count 변수로 카운트하고 min 값 구하기
    public int solution(String s) {

        return 1;
    }
}

class Run {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution("aabbaccc") == 7);
        System.out.println(solution.solution("ababcdcdababcdcd") == 9);
        System.out.println(solution.solution("abcabcdede") == 8);
        System.out.println(solution.solution("abcabcabcabcdededededede") == 14);
        System.out.println(solution.solution("xababcdcdababcdcd") == 17);

    }

}
