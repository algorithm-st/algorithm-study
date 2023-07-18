package june.프로그래머스.테이블해시함수;

import java.util.*;

public class Solution {
    public int solution(int[][] data, int col, int row_begin, int row_end) {
        int answer = 0;
        col = col-1;
        row_begin -= 1;
        int finalCol = col;
        Arrays.sort(data, (o1,o2) ->{
            if(o1[finalCol] == o2[finalCol]){
                return o2[0] - o1[0];
            }
            return o1[finalCol] - o2[finalCol];
        });

        for(int i=row_begin;i<row_end;i++){
            int finalI = i+1;
            int s = Arrays.stream(data[i])
                .map(j -> j%finalI)
                .sum();
            answer = answer^s;
        }
        
        return answer;
    }
}