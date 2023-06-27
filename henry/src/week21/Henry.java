package week21;

import java.util.*;

/**
 왜 이 문제를 선택했나?
 - 카카오 기출 중 하나 선택

 문제 푼 데 걸린 시간은?
 - 1시간

 시간 복잡도는 ? (잘 모르겠다면 예상)
 - 입력 배열의 크기(1 이상 100,000 이하)

 문제 설명
 - 진열대 번호 순서대로 보석들의 이름이 저장된 배열 gems가 매개 변수로 주어진다.
 - 특정 범위의 물건들을 모두 싹쓸이한다.
 - 진열된 모든 종류의 보석을 적어도 1개 이상 포함하는 가장 짧은 구간을 찾아서 구매

 문제 풀이 설명
 - 입력으로 주어진 보석의 종류를 먼저 계산
 - 투 포인터 알고리즘으로 구간을 찾는다
    1. rt 한칸 씩 증가하며 보석 카운트 업데이트
    2. lt 가 있는 보석이 2개 이상이면 증가 시켜도 무방하므로, lt에 있는 보석 카운트 1개가 될때까지 증가
    3. 구간 내에 모든 보석이 포함되어 있고, 구간의 길이가 더 짧으면 업데이트
 */
public class Henry {
    static class Solution {
        public int[] solution(String[] gems) {
            int[] answer = new int[2];

            Set<String> gemSet = new HashSet<>();
            for(String gem : gems){
                gemSet.add(gem);
            }
            int gemTypes = gemSet.size(); // 보석 종류 계산

            // 투포인터 알고리즘
            Set<String> twoPointGemSet = new HashSet<>(); // 보석 종류 저장 set
            Map<String, Integer> gemMap = new HashMap<>(); // 각 보석 별 count 저장 Map
            int lt = 0; int rt = -1;
            int answerWidth = Integer.MAX_VALUE;
            while(rt<gems.length-1){
                rt++;
                twoPointGemSet.add(gems[rt]);
                gemMap.put(gems[rt], gemMap.getOrDefault(gems[rt], 0) + 1);

                // lt가 만약 구간내에 2개 이상 있다면 lt는 오른쪽으로 한칸 이동 가능
                while(gemMap.get(gems[lt]) > 1){
                    gemMap.put(gems[lt], gemMap.get(gems[lt]) -1);
                    lt++;
                }

                // 구간 내에 모든 보석이 포함되어 있고, 구간의 길이가 더 짧으면 업데이트
                if(twoPointGemSet.size() == gemTypes && answerWidth > (rt-lt+1)){
                    answer[0] = lt+1;
                    answer[1] = rt+1;
                    answerWidth = rt-lt+1;
                }
            }
            return answer;
        }
    }
}
