package june.이분탐색.입국심사;

import java.util.Arrays;

public class Main {
    class Solution {
        public long solution(int n, int[] times) {
             Arrays.sort(times);
            long left = 1;
            long right = (long) times[times.length - 1] *n;
            long mid;
            long min = 0;
            while(left <= right){
                mid = (left+right)/2;
                if(check(mid, times) >= n){
                    min = mid;
                    right = mid-1;
                }else{
                    left = mid+1;
                }
            }
            return min;
        }
        public long check(long mid, int[] times){
            long total = 0;
            for(long time: times){
                total += mid/time;
            }
            return total;
        }
    }

}
