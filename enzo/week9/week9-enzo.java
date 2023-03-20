import java.util.*;
class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        long min = 1;
        long max = 1;
        for(int i :times){
            max = Math.max(max,i);
        }
        max = max*n;
        long ln = n;
        while(min<max){
            long mid = (min+max)/2;
            long count =0;
            for(int time : times){
                count += mid/time;
            }
            if(count<ln){
                min = mid+1;
            }
            else max = mid;
        }
        return min;
    }
}