mport java.util.*;
class Solution {
    public int solution(String s) {
        int answer = s.length();
        for(int i=1;i<=s.length()/2;i++){
            answer = Math.min(answer,getLengthZipString(i,s));
        }
        return answer;
    }
    public int getLengthZipString(int splitCount, String targetString){
        int answer= 0;
        String beforeString = targetString.substring(0,splitCount);
        int count = 1;
        int index =splitCount;
        for(index = splitCount;index<targetString.length();index+=splitCount){

            if(index+splitCount>targetString.length()) break;

            String curString = targetString.substring(index,index+splitCount);
            if(beforeString.equals(curString)){
                count++;
            }
            else{
                if(count>1) answer += String.valueOf(count).length();
                answer += splitCount;
                count =1;
                beforeString = curString;
            }
        }

        if(count>1) answer += String.valueOf(count).length();
        answer += splitCount;

        if(index<targetString.length()){
            answer += targetString.length() - index;
        }
        return answer;
    }
}