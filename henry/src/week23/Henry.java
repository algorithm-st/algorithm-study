package week23;

/**
 왜 이 문제를 선택했나?
 - 레벨 3 문제 중 하나 선택

 문제 푼 데 걸린 시간은?
 - 30분

 시간 복잡도는?
 - 문자열 s의 길이 : n
 - O(n) = n^3

 문제 설명
 - 앞 뒤를 뒤집어도 똑같은 문자열을 팰린드롬이라고 한다.
 - 문자열 s가 주어질 때, s의 부분 문자열 중 가장 긴 팰린드록의 길이 return

 문제 풀이
 - 모든 길이에 대해 펠린드롬 문자열이 있는지 체크(가장 긴 펠린드롬의 길이를 반환해야 하므로, 긴 길이부터 체크)
 - 특정 길이에 대해 슬라이딩 윈도우 방식으로 문자가 펠린드롬인지 체크
 */
public class Henry {
    static class Solution
    {
        public int solution(String s)
        {
            int answer = 0;

            // 모든 길이에 대해 펠린드롬 문자열이 있는지 체크(가장 긴 펠린드롬의 길이를 반환해야 하므로, 긴 길이부터 체크)
            for(int i=s.length(); i>=1; i--){
                if(test(s, i)){
                    answer = i;
                    break;
                }
            }

            return answer;
        }

        boolean test(String s, int length){
            // 특정 길이에 대해 슬라이딩 윈도우 방식으로 문자가 펠린드롬인지 체크
            for(int i=0; i+length <= s.length(); i++){

                boolean flag = true;
                for(int j=0; j<length/2; j++){
                    char left = s.charAt(i+j);
                    char right = s.charAt(i+length-j-1);

                    if(left != right){
                        flag = false;
                        break; // 문자 하나가 다르다면 뒤는 체크할 필요가 없다.
                    }
                }

                if(flag){
                    return true; // 펠린드롬이 하나라도 있다면 해당 길이는 가능하므로 바로 return
                }
            }
            return false;
        }
    }
}
