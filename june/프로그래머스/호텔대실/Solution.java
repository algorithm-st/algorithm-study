package june.프로그래머스.호텔대실;

import java.util.*;
import java.time.*;

class Solution {
    public final int MAX_TIME = 1_450; // 24*60+10;
    public final int CLEAN_TIME = 10; // 청소시간
    public int solution(String[][] book_time) {
        int answer = 0;
        int[] room = new int[MAX_TIME];
        
        for(String[] book : book_time){
            int from = toInt(book[0]);
            int to = toInt(book[1]);
            
            room[from] += 1;
            room[to+CLEAN_TIME] -= 1;
        }
        
        for(int i =1;i<MAX_TIME;i++){
            room[i] += room[i-1];
            answer = Math.max(answer, room[i]);
        }
        
        return answer;
    }
    
    public int toInt(String time){
        LocalTime t = LocalTime.parse(time);
        return t.getHour()*60 + t.getMinute();
    }
}