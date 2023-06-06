package youngkwon_ned.programmers;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/17680">[1차] 캐시</a>
 */
public class Solution2 {
    private static int answer = 0;

    public static void main(String[] args) {
        Solution2 s = new Solution2();
//        int solution = s.solution(3, new String[]{"Jeju", "Pangyo", "jeju", "NewYork", "LA", "Jeju", "Pangyo", "Seoul", "NewYork", "LA"});
        int solution = s.solution(0, new String[]{"LA", "LA"});
        System.out.println("solution = " + solution);
    }

    public int solution(int cacheSize, String[] cities) {
        Queue<String> queue = new LinkedList<>();
        for (String city : cities) {
            city = city.toUpperCase();
            if (queue.contains(city) && queue.size() <= cacheSize) {
                answer += 1;
                queue.remove(city);
                queue.add(city);
                continue;
            }

            if ((queue.size() >= cacheSize)) {
                queue.poll();
            }
            queue.add(city);
            answer += 5;
        }
        return answer;
    }
}
