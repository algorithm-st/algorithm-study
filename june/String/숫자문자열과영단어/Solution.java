package june.String.숫자문자열과영단어;

public class Solution {

    public int solution(String s) {
        String[] arr = {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        for (int i = 0; i < arr.length; i++) {
            s = s.replaceAll(arr[i], String.valueOf(i));
        }
        return Integer.parseInt(s);
    }

}

class Run {

    public static void main(String[] args) {
        Solution solution = new Solution();
        System.out.println(solution.solution("one4seveneight") == 1478);
        System.out.println(solution.solution("23four5six7") == 234567);

    }

}
