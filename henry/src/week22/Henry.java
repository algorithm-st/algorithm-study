package week22;

/**
 왜 이 문제를 선택했나?
 - 레벨 3중 하나 선택

 문제 푸는 데 걸린 시간은
 - 1시간 반 (stations으로 List를 선언했는데 시간 초과 발생)

 시간 복잡도는?
 - O(n)

 문제 설명
 - N개의 아파트가 일렬로 쭉 늘어서 있다. 4g 기지국을 5g 기지국으로 바꾸려 한다.
 - 4g 기지국을 5g 기지국으로 바꾸면 어떤 아파트에는 전파가 도달하지 않는다.
 - 5g 기지국을 추가 최소 설치하며 모든 아파트에 전파를 전달하려한다.
 - w : 5g 기지국의 전파 도달 거리

 문제 풀이 설명
 - idx를 1부터 n까지 증가 시키며 새로 기지국을 짓는 경우를 카운트한다.
    - 기존 타워의 범위인 경우 -> 기존 타원의 오른쪽으로 idx를 이동 시킨다.
    - 기존 타워의 범위가 아닌 경우 -> 새로 타워를 짓고, 2*w 만큼 오른쪽으로 이동
 - 최종적으로 새로 지어진 타워수를 반환한다.

 */
public class Henry {
    static class Solution {
        public int solution(int n, int[] stations, int w) {
            int answer = 0;

            int stationIdx = 0; // 타워 인덱스

            int idx = 0;
            while(idx<n){
                idx++;

                // 타워 범위인 경우
                if(stationIdx < stations.length &&
                        idx >= Math.max(1, stations[stationIdx]-w) &&
                        idx <= Math.min(n, stations[stationIdx]+w)){

                    idx = Math.min(n, stations[stationIdx]+w); // 기존 타워 범위의 오른쪽으로 이동
                    stationIdx++;
                    continue;
                }

                // 타워 범위가 아닌 경우 -> 새로 타워를 짓는다.
                answer++;
                idx += 2*w; // 새로 지은 타워의 오른쪽으로 이동
            }

            return answer;
        }
    }
}
