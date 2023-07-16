import java.util.*;
class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        Queue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        int total = 0;
        for(int i : works){
            queue.add(i);
            total += i;
        }
        if(total <= n){
            return 0;
        }
        while(n>0){
            int curNum = queue.poll();
            queue.add(curNum-1);
            n--;
        }
        for(int i : queue){
            answer += i*i;
        }
        return answer;
    }
}