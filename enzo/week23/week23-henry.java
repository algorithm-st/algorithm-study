class Solution
{
    public int solution(String s)
    {
        int answer = 0;

        for(int i=0;i<s.length();i++){
            // 홀수 일 때
            answer = Math.max(answer, getLength(s, i-1, i+1, 1));
            // 짝수 일 때
            answer = Math.max(answer, getLength(s, i, i+1, 0));
        }

        return answer;
    }
    public int getLength(String s, int left, int right, int count){
        while(left>=0 && right<s.length()){
            if(s.charAt(left) == s.charAt(right)){
                count += 2;
                left -=1;
                right +=1;
            }
            else{
                break;
            }
        }
        return count;
    }
}