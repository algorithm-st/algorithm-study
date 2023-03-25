import java.util.*;

class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for(int i=0;i<numbers.length;i++){
            String s = getString(numbers[i]);
            int curAnswer = getAnswer(0,s.length()-1,s);
            if(curAnswer == -1){
                answer[i] = 0;
            }
            else answer[i] = 1;
        }
        return answer;
    }
    public String getString(long number){
        StringBuffer sb = new StringBuffer();
        while(number!=0){
            sb.append(number%2);
            number /=2;
        }
        String s = sb.reverse().toString();
        int length = s.length();
        int count = 1;
        int num = 1;
        while(length>num){
            count++;
            num = (int)Math.pow(2,count)-1;
        }
        int restLength = num - length;
        char [] arr = new char[restLength];
        for(int i=0;i<restLength;i++){
            arr[i] = '0';
        }
        return String.valueOf(arr) + s;
    }
    public int getAnswer(int start, int end, String s){
        if(start == end){
            return s.charAt(start)-'0';
        }
        int mid = (start+end)/2;
        int curMid = s.charAt(mid) - '0';
        int left = getAnswer(start,mid-1,s);
        int right = getAnswer(mid+1,end,s);

        if(left == -1 || right == -1) return -1;
        else if(curMid == 1) return 1;
        else if(curMid == 0){
            if(left == 1 || right == 1) return -1;
        }
        return 0;
    }
}