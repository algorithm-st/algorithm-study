package week12;


/**
 * 왜 이문제를 선택했나?
 * - 많이 안풀어본 유형 같아 선택해 보았습니다.
 *
 * 문제 푼 데 걸린 시간은?
 * - 1시간
 *
 * 시간 복잡도는?
 * - e^2 + e 이하 (break 문으로 반복문 연산 감소)
 *
 * 문제 설명
 *  1. 적당한 수 e
 *  2. e 이하의 임의의 수 s 여러개
 *  3. 각 s에 대해서 s보다 크거나 같고, e 보다 작거나 같은 수 중에서 억억단에서 가장 많이 등장한 수를 답한다.
 *         - 만약 가장 많이 등장한 수가 여러개라면 그 중 가장 작은 수를 답한다.
 *
 *  문제 풀이 설명
 *  1. 억억단에서 각 숫자가 몇번 등장하는지 카운트한다.
 *  2. e부터 아래로 내려가며 가장 많이 등장한 수를 업데이트 해간다.
 */
public class Henry {
    public int[] solution(int e, int[] starts) {
        int[] answer = new int[starts.length];

        // 각 숫자가 몇번 등장하는지 카운트 한다. -
        int[] cntArray = new int[5000001];
        for(int i=1; i<=e; i++){
            for(int j=1; j<=e; j++){
                int multiply = i * j;
                if(multiply>5000000){
                    break;
                }
                cntArray[multiply] = cntArray[multiply]+1;
            }
        }


        // e부터 아래로 내려가며 i ~ e 사이의 가장 많이 등장한 수를 기록한다.
        int[] maxCntNumberArray = new int[e+1];
        int currentMaxNumber = e;
        int currentMaxNumberCnt = cntArray[e];
        maxCntNumberArray[e] = e;

        for(int i=e-1; i>=1; i--){
            int numberCnt = cntArray[i];

            if(numberCnt >= currentMaxNumberCnt){ // 가장 많이 등장한 수가 여러개라면, 가장 작은 수로 답한다.
                currentMaxNumber = i;
                currentMaxNumberCnt = numberCnt;
            }
            maxCntNumberArray[i] = currentMaxNumber;
        }

        // 답 만들기
        for(int i=0; i<starts.length; i++){
            answer[i] = maxCntNumberArray[starts[i]];
        }
        return answer;
    }
}
