package week11;

import java.util.*;

/**
 * 왜 이문제를 선택했나?
 * - 네이버 코테가 주로 구현 문제라 간단한 구현 문제 연습을 위해
 *
 * 문제 푸는데 걸린 시간?
 * - 30분 + a
 *
 * 시간 복잡도
 * - H * W (전제 2차원 배열을 탐색)
 *
 * 문제 설명
 * - 블록이 존재하고 위에서 비가 내렸을 때 고이는 빗물의 총량을 계산
 *
 * 문제 풀이
 * - 각 행의 블록 기준으로 양쪽에 블록이 존재한다면 해당 블록에는 빗물이 고인다.
 * - 각 행에 고이는 빗물의 수를 계산해 누적시키며 답을 구한다.
 */
public class Henry {

    static int h;
    static int w;
    static int[][] map;
    public static void main(String[] args) {
        int answer = 0;

        // 입력 받기
        Scanner scanner = new Scanner(System.in);
        h = scanner.nextInt();
        w = scanner.nextInt();
        map = new int[h][w];
        for(int i=0; i<w; i++){
            int height = scanner.nextInt();
            for(int j=0; j<height; j++){
                map[h-j-1][i] = 1;
            }
        }

        for(int i=0; i<h; i++){ // 각행에 고이는 빗물의 수를 누적합으로 더한다.
            answer += countRowRainNumber(map[i]);
        }
        System.out.println(answer);
    }

    /**
     * 각 행에 고이는 빗물의 수를 계산
     * @param rowArray - 행 배열
     * @return - 행에 고이는 빗물의 수
     */
    private static int countRowRainNumber(int[] rowArray){
        int width = rowArray.length;
        int result = 0;

        boolean firstBlockFlag = false; // 왼쪽부터 탐색하는데 이전에 블록이 있는가?
        int tmpCnt = 0;
        for(int i=0; i<width; i++){
            if(rowArray[i] == 1){ // 블록이면
                firstBlockFlag = true; // 해당행에 블록이 존재한다.
                result += tmpCnt; // 지금까지 센 카운트 더하기
                tmpCnt = 0;
            }else if(rowArray[i] == 0){
                if(firstBlockFlag) { // 이전에 블록이 있었다면 카운트
                    tmpCnt++;
                }
            }
        }
        return result;
    }
}
