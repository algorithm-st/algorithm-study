package june.프로그래머스.두큐합같게만들기;

import java.util.ArrayList;
import java.util.List;

public class Solution {
    public int solution(int[] queue1, int[] queue2) {
        List<Integer> arr = new ArrayList<>();
        long sum = 0;
        int count = 0;
        for(int q : queue1){
            arr.add(q);
            sum += q;
        }
        for(int q : queue2){
            arr.add(q);
            sum += q;
        }
        if(sum%2==1){
            return -1;
        }
        long half = sum/2;
        int start = 0;
        int end = queue1.length;
        long su = cal(start, end, arr);
        // System.out.println("절반 : " + half);
        while(end < queue1.length*2 && start < end){

            // System.out.println("start : " + start + " , " +"end : "+ end);
            // System.out.println("결과값 : " + su);

            if(su < half){
                // 반보다 작으면 2에서 빼기
                su += arr.get(end);
                end++;
                count++;
            }else if(su > half){
                // 반보다 크면 1에서 빼기
                su -= arr.get(start);
                start++;
                count++;
            }else{
                return count;
            }
        }
        // System.out.println(start + " : " + end);
        return -1;

    }

    public long cal(int start, int end, List<Integer> arr){
        long sum = 0;
        for(int i = start; i<end; i++){
            sum += arr.get(i);
        }
        return sum;
    }


}
