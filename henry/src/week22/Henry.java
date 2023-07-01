package week22;

/**
 문제 설명
 - N개의 아파트가 일렬로 쭉 늘어서 있다. 4g 기지국을 5g 기지국으로 바꾸려 한다.
 - 4g 기지국을 5g 기지국으로 바꾸면 어떤 아파트에는 전파가 도달하지 않는다.
 - 5g 기지국을 추가 최소 설치하며 모든 아파트에 전파를 전달하려한다.
 - w : 전파 도달 거리
 */
public class Henry {
    static class Solution {
        public int solution(int n, int[] stations, int w) {
            int answer = 0;

            int stationIdx = 0;

            int idx = 0;
            while(idx<n){
                idx++;

                // 타워 범위인 경우
                if(stationIdx < stations.length &&
                        idx >= Math.max(1, stations[stationIdx]-w) &&
                        idx <= Math.min(n, stations[stationIdx]+w)){

                    idx = Math.min(n, stations[stationIdx]+w);
                    stationIdx++;
                    continue;
                }

                // 타워 범위가 아닌 경우
                answer++;
                idx += 2*w;
            }

            return answer;
        }
    }
}
