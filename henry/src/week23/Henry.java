package week23;

public class Henry {
    static class Solution
    {
        public int solution(String s)
        {
            int answer = 0;

            for(int i=s.length(); i>=1; i--){
                if(test(s, i)){
                    answer = i;
                    break;
                }
            }

            return answer;
        }

        boolean test(String s, int length){
            for(int i=0; i+length <= s.length(); i++){

                boolean flag = true;
                for(int j=0; j<length/2; j++){
                    char left = s.charAt(i+j);
                    char right = s.charAt(i+length-j-1);

                    if(left != right){
                        flag = false;
                        break;
                    }
                }

                if(flag){
                    return true;
                }
            }
            return false;
        }
    }
}
