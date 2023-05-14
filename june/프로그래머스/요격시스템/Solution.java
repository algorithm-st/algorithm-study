package june.프로그래머스.요격시스템;

import java.util.Arrays;

public class Solution {

    public static void main(String[] args) {
        int[][] targets = {{4, 5}, {4, 8}, {10, 14}, {11, 13}, {5, 12}, {3, 7}, {1, 4}};

        System.out.println(solution(targets));
    }

    public static int solution(int[][] targets) {
        int answer = 1;

        Arrays.sort(targets, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o1[1] - o2[1];
            }
            return o1[0] - o2[0];
        });

        int end = targets[0][1];

        for (int[] target : targets) {
            if(target[0] >= end){
                end = target[1];
                answer++;
            }
        }
        return answer;
    }
}