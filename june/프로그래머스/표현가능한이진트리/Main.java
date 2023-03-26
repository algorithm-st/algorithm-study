package june.프로그래머스.표현가능한이진트리;

public class Main {

    class Solution {
        public int[] solution(long[] numbers) {
            int[] answer = new int[numbers.length];
            int i = 0;
            for(long num : numbers){
                System.out.println(Long.toBinaryString(num));
                answer[i++] = check(Long.toBinaryString(num));
            }
            return answer;
        }

        public int check(String num){
            if(num.length() <=1){
                return num.charAt(0) == '1' ? 1:0;
            }
            if(num.length() %2 == 0){
                StringBuilder sb = new StringBuilder("0");
                sb.append(num);
                num = sb.toString();
            }
            for(int i = 1; i<num.length(); i+=2){
                if(num.charAt(i) != '1'){
                    return 0;
                }
            }
            return 1;
        }
    }

}
