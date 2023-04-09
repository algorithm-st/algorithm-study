package youngkwon_ned.week10;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/118667">두 큐 합 같게 만들기</a>
 */
public class Main {
    public static void main(String[] args) {
//        3,2,7,2 ,4,6,5,1
        int[] arr1 = {1,1,1,1,1,1};
        int[] arr2 = {1,1,1,1,11,1};
        Main m = new Main();
        int solution = m.solution(arr1, arr2);
        System.out.println("solution = " + solution);
    }

    public int solution(int[] queue1, int[] queue2) {
        Queue<Integer> q1 = new LinkedList<>();
        Queue<Integer> q2 = new LinkedList<>();
        addQueue(queue1, q1);
        addQueue(queue2, q2);
        double sum1 = q1.stream().mapToDouble(value -> value).sum();
        double sum2 = q2.stream().mapToDouble(value -> value).sum();
        double sum = sum1 + sum2;
        double average = sum / 2;
        if (sum % 2 == 1) return -1;
        int limit = (queue1.length + queue2.length) * 2;
        int count1 = 0;
        int count2 = 0;

        while (count1 <= limit && count2 <= limit) {
            if (sum1 == average) {
                return count1 + count2;
            }
            if (sum1 < average){
                sum1+=q2.peek();
                sum2-=q2.peek();
                q1.add(q2.poll());
                count1++;
            }else {
                sum1-=q1.peek();
                sum2+=q1.peek();
                q2.add(q1.poll());
                count2++;
            }
        }

        return -1;

    }

    private static void addQueue(int[] queue, Queue<Integer> q) {
        for (int j : queue) {
            q.add(j);
        }
    }
}
