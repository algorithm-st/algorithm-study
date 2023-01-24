package youngkwon_ned.week1;

public class Solution3 {

    public static void main(String[] args) {
        Solution3 s = new Solution3();
        int solution = s.solution("2three45sixseven");
        System.out.println("solution = " + solution);
    }
    private static String[] alphabets =
        {"zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};

    public int solution(String s) {
        for (int i = 0; i < alphabets.length; i++) {
            s = s.replaceAll(alphabets[i], String.valueOf(i));
        }
        return Integer.parseInt(s);
    }

}
