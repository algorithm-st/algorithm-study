package week23;

/*
x의 부분 수열이란
- x 의 몇몇 원소들을 제거하거나 그러지 않고 남은 원소들이 원래 순서를 유지하여 얻을 수 있는 새로운 수열

스타 수열
1. x의 길이가 2 이상의 짝수
2. x의 길이를 2n 이라 할때,다음과 같은 n개의 집합 {x[0], x[1]}, {x[2], x[3]}, ..., {x[2n-2], x[2n-1]} 의 교집합의 원소의 개수가 1 이상
3. 각 집합 내의 숫자가 다르다.

a의 부분 수열 중에서 가장 길이가 긴 스타 수열의 길이?
- 스타 수열이 없다면, 0을 return
*/
public class Enzo {
    static class Solution {
        public int solution(int[] a) {
            int answer = 0;

            int[] cntArray = new int[a.length]; // 각 숫자가 나오는 카운트 계산
            for(int num : a){
                cntArray[num]++;
            }

            for(int x = 0; x < cntArray.length; x++){
                if(cntArray[x] == 0 || answer/2 >= cntArray[x]){ // 숫자 카운트가 없거나, 중복으로 나오는 숫자로 만드는 조합이 정답보다 작을 경우
                    continue;
                }

                int subAnswer = 0;
                for(int j = 0; j<a.length -1; j++){
                    if((a[j]!=a[j+1]) && (a[j] == x || a[j+1] == x)){
                        j++;
                        subAnswer+=2;
                    }
                }
                answer = Math.max(answer, subAnswer);
            }

            return answer;
        }
    }
}
