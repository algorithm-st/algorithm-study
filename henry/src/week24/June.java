package week24;

/**
 문제 설명
 - 최소한의 객실만을 사용하여 예약 손님을 받는다.
 - 한 사용한 객실은 10분간 청소
 */
import java.util.*;
public class June {
    static class Solution {
        public int solution(String[][] book_time) {
            int answer = 0;
            int[] room = new int[24*60 + 10];

            for(String[] book : book_time){
                int startTime = getTime(book[0]);
                int endTime = getTime(book[1]);

                for(int i = startTime ; i< endTime + 10 ; i++){
                    room[i]++;
                }
            }

            for(int r : room){
                answer = Math.max(answer, r);
            }

            return answer;
        }

        int getTime(String time){
            Integer hour = Integer.valueOf(time.substring(0, 2));
            Integer min = Integer.valueOf(time.substring(3));

            return hour*60 + min;
        }
    }
}
