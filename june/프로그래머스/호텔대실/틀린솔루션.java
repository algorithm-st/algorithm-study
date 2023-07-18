package june.프로그래머스.호텔대실;

import java.util.*;
import java.time.*;

public class 틀린솔루션 {
    public int solution(String[][] book_time) {
        int answer = 0;
        List<Integer> from = new ArrayList<>();
        List<Integer> to = new ArrayList<>();
        
        for(String[] book : book_time){
            from.add(toInt(book[0]));
            to.add(toInt(book[1]));
        }
        
        Collections.sort(from);
        Collections.sort(to);
        
        int f = 0;
        int t = 0;
        int count = 0;
        int lastOut = 1000000;
        while(f<from.size() && t<to.size()){
            int fTop = from.get(f);
            int tTop = to.get(t);
            System.out.println("fTop : " + fTop);
            System.out.println("tTop : " + tTop);
            if(fTop<tTop){
                f++;
                if(fTop<lastOut){
                    count++;     
                }
            }else if(fTop > tTop){
                t++;
                count--;
                lastOut = tTop+10;
            }else{
                f++;
                t++;
            }
            answer = Math.max(count, answer);
            System.out.println("last : " + lastOut);
            System.out.println();
        }
        
        
        return answer;
    }
    
    public int toInt(String time){
        LocalTime t = LocalTime.parse(time);
        return t.getHour()*60 + t.getMinute();
    }
}