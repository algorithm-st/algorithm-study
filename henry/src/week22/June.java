package week22;

/**
 문제설명
 - 데이터베이스의 컬럼들은 모두 정수 타입인 컬럼으로 이루어짐
 - 첫 번째 컬럼은 기본키로서 모든 튜플에 대해 그 값이 중복되지 않도록 보장된다.
 - 해시 함수
 - col 번째 컬럼의 값을 기준으로 오름차순 정렬, 그 값이 동일하면 기본키인 첫번째 컬럼의 값을 기준으로 내림차순
 - 정렬된 데이터에서 S_i : i 번째 행의 튜플에 대해 각 컬럼의 값을 i로 나눈 나머지들의 합으로 정의
 - row_begin <= i <= row_end인 모든 S_i를 누적하여 bitwise XOR 한 값을 해시 값으로 반환
 */

import java.util.*;
public class June {
    static class Solution {
        public int solution(int[][] data, int col, int row_begin, int row_end) {
            int answer = 0;

            Arrays.sort(data, (a, b)->{
                int aIndex = a[0];
                int aCol = a[col-1];
                int bIndex = b[0];
                int bCol = b[col-1];

                if(aCol != bCol){
                    return aCol - bCol;
                }else{
                    return bIndex - aIndex;
                }
            });

            List<Integer> list = new ArrayList<>();
            for(int i=row_begin; i<=row_end; i++){
                int sum = 0;

                for(int d : data[i-1]){
                    sum += (d%i);
                }
                list.add(sum);
            }

            answer = list.get(0);
            for(int i=1; i<list.size(); i++){
                answer = answer ^ list.get(i);
            }

            return answer;
        }
    }
}
