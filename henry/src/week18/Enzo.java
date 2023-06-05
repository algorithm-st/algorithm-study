package week18;

import java.util.*;

/**
 문제 설명
 - 각 사원마다 근무 태도 점수 + 동료 평가 점수가 기록
 - 만약 어떤 사원이 다른 임의의 사원보다 두 점수가 모두 낮은 경우가 한 번이라도 있다면 그 사원은 인센티브를 받지 못한다.
 - 나머지 - 두 점수의 합이 높은 순 -> 인센티브 차등 지급
 - 점수 합 동일하면 동 석차, 동석차의 수만큼 다음 석차는 건너 뛴다.
 */

public class Enzo {
    static class Solution {
        public int solution(int[][] scores) {
            int answer = 1;

            int[] wanho = scores[0];

            if(scores.length == 1){
                return answer;
            }

            Arrays.sort(scores, (s1, s2)->{
                if(s1[0] != s2[0]){
                    return s2[0] - s1[0];
                }else{
                    return s1[1] - s2[1];
                }
            });

            int coworkerScoreMax = -1;

            for(int i=0; i<scores.length; i++){
                int[] score = scores[i];
                if(coworkerScoreMax > score[1]){// 성과금 x
                    if(score.equals(wanho)){
                        return -1;
                    }
                    continue;
                }else{
                    coworkerScoreMax = Math.max(coworkerScoreMax, score[1]);

                    if((wanho[0] + wanho[1]) < (score[0] + score[1])){
                        answer++;
                    }
                }
            }

            return answer;
        }
    }
}
