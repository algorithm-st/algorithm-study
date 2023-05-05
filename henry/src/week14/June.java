package week14;

import java.util.*;

public class June {


    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] picks = {1, 3, 2};
        String[] minerals = {"diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"};

        int answer = solution.solution(picks, minerals);
        System.out.println("answer = " + answer);

    }

    /**
     문제 설명
     - 곡괭이 각가 0 ~ 5개
     - 곡괭이로 광물을 캘 때는 피로도가 소모된다.
     - 각 곡굉이는 5개 캔 후에 더이상 사용 x

     - 규칙
     1. 한번 사용하기 시작한 곡괭이는 사용할 수 없을 때까지 사용
     2. 광물은 주어진 순서로만 캔다.
     3. 모든 광물 캐거나 or 더 이상 사용할 곡괭이가 없을 때까지 캔다.

     최소한의 피로도 return
     */

    static class Solution {
        static int answer = Integer.MAX_VALUE;
        static int dia, iron, stone;

        public int solution(int[] picks, String[] minerals) {
            dia = picks[0]; iron = picks[1]; stone = picks[2];

            dfs(0, 0, 0, 0, minerals);
            return answer;
        }

        static int[][] tireds = {
                {1, 1, 1},
                {5, 1, 1},
                {25, 5, 1}
        };

        void dfs(int cur, int cnt, int idx, int tired, String[] minerals){
            if(tired > answer){
                return;
            }

            if(minerals.length == idx || ((dia==0 && iron ==0 && stone==0) && (cnt ==0))){
                answer = Math.min(answer, tired);
                return;
            }

            int mineral = 0;
            switch(minerals[idx]){
                case "diamond" :
                    mineral = 0; break;
                case "iron":
                    mineral = 1; break;
                case "stone":
                    mineral = 2; break;
                default:
                    break;
            }

            if(cnt > 0){
                int thisTired = tireds[cur][mineral];
                dfs(cur, cnt-1, idx+1, tired+thisTired, minerals);
            }else{
                if(dia>0){
                    dia--;
                    dfs(0, 5, idx, tired, minerals);
                    dia++;
                }

                if(iron>0){
                    iron--;
                    dfs(1, 5, idx, tired, minerals);
                    iron++;
                }

                if(stone > 0){
                    stone--;
                    dfs(2, 5, idx, tired, minerals);
                    stone++;
                }
            }
        }
    }
}
