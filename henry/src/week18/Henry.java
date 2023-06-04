package week18;

/**
 왜 이 문제를 선택했나?
 - 레벨 3중에서 하나 고름

 문제 푸는데 걸린 시간은?
 - 1시간

 시간 복잡도는?
 - 3n

 문제 설명
 - n개의 풍선이 있다.
     1. 임의의 인접한 두 풍선을 고른 뒤, 두 풍선 중 하나를 터트린다.
     2. 터진 풍선으로 인해 풍선들 사이에 빈 공간이 생겼다면, 밀착
 - 두 풍선 중, 번호가 더 작은 풍선을 터트리는 행위는 최대 1번 가능
 - 풍선들을 1개만 남을 때까지 터트렸을 때, 최후까지 남기는 것이 가능한 풍선들의 개수?

 문제 풀이
 ---- ? ----
 - ? 기준으로 왼쪽, 오른쪽 최소 숫자를 찾는다.(왼쪽, 오른쪽에서는 큰 풍선만 터트린다 -> 작은 풍선만 남는다.)
 - 최대 1번은 번호가 더 작은 풍선 터트리기 가능 -> 양쪽중 MIN 값이 1개라도 ? 보다크면 안터진다.
 - DP 배열을 선언해 왼쪽, 오른쪽 부터 현재 위치까지 최소 숫자를 기록
 - 처음과 마지막 풍선은 무조건 남을 수 있다.
 */
import java.util.*;

public class Henry {
    static class Solution {
        public int solution(int[] a) {
            int answer = 2; // 처음과 마지막 풍선을 무조건 남을 수 있다.


            int[] leftMin = new int[a.length]; // 왼쪽부터 현재 위치까지 최소값 저장 DP 배열
            leftMin[0] = a[0];
            for(int i=1; i<a.length; i++){
                leftMin[i] = Math.min(leftMin[i-1], a[i]);
            }

            int[] rightMin = new int[a.length]; // 오른쪽부터 현재 위치까지 최소값 저장 DP 배열
            rightMin[a.length-1] = a[a.length-1];
            for(int i=a.length-2; i>=0; i--){
                rightMin[i] = Math.min(rightMin[i+1], a[i]);
            }

            for(int i=1; i<a.length-1; i++){
                // 현재 위치 기준 양쪽 MIN 값이 큰게 하나라도 있으면? 안터진다.
                if((leftMin[i-1] > a[i]) ||(rightMin[i+1] > a[i])){
                    answer++;
                }
            }

            return answer;
        }
    }
}
