package week11;

import java.util.*;

class Ned {
    public int solution(int[] queue1, int[] queue2) {
        int answer = -2;

        long sum1 = 0;
        long sum2 = 0;

        long targetSum = 0;

        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();

        for(int tmp : queue1){
            sum1 += tmp;
            q1.offer(tmp);
        }

        for(int tmp : queue2){
            sum2 += tmp;
            q2.offer(tmp);
        }

        targetSum = (sum1 + sum2) /2;

        int cnt = 0;
        int maxCnt = queue1.length * 4;

        while(cnt <= maxCnt){
            if(targetSum == sum1 && targetSum==sum2){
                break;
            }

            cnt++;
            if(sum1 > targetSum){
                int q1Number = q1.poll();
                sum1-= q1Number;

                q2.offer(q1Number);
                sum2+=q1Number;

            }else{
                int q2Number = q2.poll();
                sum2-= q2Number;

                q1.offer(q2Number);
                sum1+=q2Number;
            }
        }

        if(cnt>maxCnt){
            answer = -1;
        }else{
            answer = cnt;
        }

        return answer;
    }
}
