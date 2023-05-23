package june.프로그래머스.광고삽입;

import java.util.*;

public class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        int playTime = convertSecond(play_time);
        int advTime = convertSecond(adv_time);
        int[] playCount = new int[100*60*60];
        
        for(String log : logs){
            String[] split = log.split("-");
            int start = convertSecond(split[0]);
            int end = convertSecond(split[1]);
            for(int i = start; i< end; i++){
                playCount[i]++;
            }
        }
        
        long sum = 0;
        for(int i =0; i<advTime; i++){
            sum += playCount[i];
        }
        long max = sum;
        int index = 0;
        for(int i = advTime; i<playTime; i++){
            sum = sum + playCount[i] - playCount[i - advTime];
            if(sum > max){
                max = sum;
                index = i - advTime + 1;
            }
        }
        
        return String.format("%02d:%02d:%02d", index/3600, (index/60)%60,index%60);
    }
    
    int convertSecond(String time){
        int[] parse = Arrays.stream(time.split(":"))
            .mapToInt(Integer::parseInt)
            .toArray();
        return parse[0] * 60*60 + parse[1] * 60 + parse[2];
    }
}

// 시간을 초로 변환하는 함수
// 변환하고 log 사이의 값들을 카운트 ++
// 시작부터 제일 큰값 찾기 고고