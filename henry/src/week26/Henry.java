package week26;

/**
 왜 이 문제를 선택했나?
 - 레벨 3중 하나 선택

 문제 푸는데 걸린 시간은?
 - 30분

 시간 복잡도는?
 - N * log(N) - 배열 정렬

 문제 설명
 - 2 x N 명의 사원들이 있다.
 - 각 경기당 A팀에서 한 사원이, B팀에서 한 사원이 나와 서로의 수를 공개합니다. 그때 숫자가 큰 쪽이 승리하게 되고, 승리한 사원이 속한 팀은 승점을 1점 얻게 됩니다. 만약 숫자가 같다면 누구도 승점을 얻지 않는다.
 - 각 사원은 딱 한번씩 경기를 한다.
 - A팀은 출전 순서가 정해져있고, B팀은 승점을 가장 높이는 방법으로 팀원들의 출전 순서를 정했다. 이때 B 팀이 얻는 승점을 구해주세요.

 문제 풀이
 - 그리디하게 푼다.
 - 가장 작은 a는 그 수보다 가장 조금 큰 b로 이긴다.
 - A와 B를 오름차순 정렬 후 작은 수부터 투포인터 알고리즘으로 비교해나간다.
 */


import java.util.*;
public class Henry {
    static class Solution {
        public int solution(int[] A, int[] B) {
            int answer = 0;
            int n = A.length;

            Arrays.sort(A); Arrays.sort(B);

            int aPoint = 0; int bPoint = 0;

            while(aPoint < n && bPoint< n){
                int a = A[aPoint]; int b = B[bPoint];

                if(a < b){
                    answer++;
                    aPoint++; bPoint++;
                }else{ // 현재 b가 가장 작은 a를 이길 수 없다? -> 해당 수는 버린다.
                    bPoint++;
                }
            }

            return answer;
        }
    }
}
