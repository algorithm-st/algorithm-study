package june.프로그래머스.연속펄스부분수열의합;

public class Solution {
    public long solution(int[] sequence) {
        long answer = 0;
        long[] sum = new long[sequence.length+1];

        for(int i = 1; i<sequence.length+1; i++){
            if(i%2==0){
                sum[i] = sum[i-1] + (long) sequence[i-1] * -1;
            }else{
                sum[i] = sum[i-1] + (long) sequence[i-1];
            }
        }
        long max =-100001;
        long min = 100001;
        
        for(int i = 0; i<sequence.length+1; i++){
            if (max < sum[i]) {
                max = sum[i];
            }
            if (min > sum[i]) {
                min = sum[i];
            }
        }
        
        return Math.abs(max - min);
    }
}