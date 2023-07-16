package week24;

/**
왜 이 문제를 선택했나?
- 카카오 기출 중 구현 관련 문제를 하나 선택

문제 푸는데 걸린 시간은?
- 못풀어서 푸는 방법 참고...

시간 복잡도는?
- 최대 4^23 (실제로는 이거보다 작기 때문에 시간 초과가 나지 않는다)

문제 설명
- 게임이 끝날 때까지 양 플레이어가 캐릭터를 몇 번 움직이게 될지 예측하려 한다.
- 보드 안에는 발판이 있는 부분과 없는 부분이 있다. 발판이 있는 곳에서만 캐릭터가 서있을 수 있다.
- 밟고 있던 발판은 그 위에 있던 캐릭터가 다른 곳으로 이동하여 다른 발판을 밞음과 동시에 사라집니다.
- 게임 종료 조건 2가지
    - 캐릭터의 상하좌우 주변 4칸이 모두 발판이 없거나 보드 밖이라서 이동할 수 없는 경우
    - 두 캐릭터가 같은 발판 위에 있을 때, 상대 플레이어의 캐릭터가 다른 발판으로 이동하여 자신의 캐릭터가 서있던 발판이 사라지게 되면
- 양 플레이어는 최적의 플레이를 한다.
    - 이길 수 있는 플레이어는 최대한 빨리 승리하도록 플레이하고
    - 질 수밖에 없는 플레이어는 최대한 오래 버티도록 플레이합니다.

문제 풀이
- 게임 보드의 크기가 5 x 5 이고, 플레이어가 움직일 때 발판이 사라지므로 완전탐색으로 문제를 풀 수 있다. -> DFS 활용
- 탐색을 하기전에는 누가 이길 수 밖에 없는지, 질 수 밖에 없는지 알 수가 없다. -> 탐색을 하며 백트래킹 방식으로 현재 위치에서 이길 수 있는지 체크
- 현재 플레이어가 이길 수 있다면 ? 최소거리 반환
- 현재 플레이어가 질 수밖에 없다면 ? 최대 거리 반환
 */

public class Henry {
    static class Solution {
        // 상 우 하 좌
        static int[] dr = {-1, 0, 1, 0}; static int[] dc = {0, 1, 0, -1};
        public int solution(int[][] board, int[] aloc, int[] bloc) {
            Result result = move(board, aloc, bloc, true);
            return result.cnt;
        }

        /**
         * @return 플레이어의 승리여부 및 그때의 이동 거리 반환
         */
        Result move(int[][] board, int[] aloc, int[] bloc, boolean isPlayerA){

            // 게임이 바로 끝나는 경우
            //1. 상하좌우 주변 4칸이 모두 발판이 없거나 보드 밖이라서 이동할 수 없는 경우
            boolean canMoveFlag = false; int r; int c;
            if(isPlayerA){
                r = aloc[0]; c = aloc[1];
            }else{
                r = bloc[0]; c = bloc[1];
            }
            for(int i=0; i<4; i++){
                int nr = r + dr[i]; int nc = c + dc[i];
                if(nr >=0 && nr <board.length && nc >= 0 && nc <board[0].length && board[nr][nc] == 1){
                    canMoveFlag = true;
                    break;
                }
            }
            if(!canMoveFlag){ // 현재 플레이어가 움직일 수 없다.
                return new Result(false, 0); // 현재 플레이어는 움직일 수 없고 패배
            }
            //2.두 캐릭터가 같은 발판 위에 있을 때 (여기서는 현재 플레이어는 움직일 수 있는 공간이 있음)
            if(aloc[0] == bloc[0] && aloc[1] == bloc[1]){
                return new Result(true, 1); // 현재 플레이어가 움직이면 발판이 사라져 상대편 패배
            }



            // 게임이 바로 안끝나는 경우 -> 현재 플레이어가 움직인 후 다음턴으로 넘긴다.
            boolean isCurrentPlayerWin = false;
            int minCnt = Integer.MAX_VALUE/2; int maxCnt = 0; // 최소, 최대 거리 업데이트를 위해 선언

            for(int i=0; i<4; i++){ // 상우하좌
                int nr = r + dr[i]; int nc = c + dc[i];

                // 움직일 수 있는 발판이 있다면
                if(nr >=0 && nr <board.length && nc >= 0 && nc <board[0].length && board[nr][nc] == 1){
                    board[r][c] = 0;

                    int[] next = {nr, nc};
                    Result result;
                    if(isPlayerA){
                        result = move(board, next, bloc, !isPlayerA);
                    }else{
                        result = move(board, aloc, next, !isPlayerA);
                    }

                    if(!result.win){ // 상대가 지는 결과를 반환하면 자신은 이기는 경우가 있다.
                        isCurrentPlayerWin = true;
                        minCnt = Math.min(minCnt, result.cnt+1); // 이길 수 있는 플레이어는 최대한 빨리 승리하도록 플레이
                    }else{
                        maxCnt = Math.max(maxCnt, result.cnt+1); // 질 수밖에 없는 플레이어는 최대한 오래 버티도록 플레이
                    }

                    board[r][c] = 1;
                }
            }
            if(isCurrentPlayerWin){
                return new Result(true, minCnt);
            }else{
                return new Result(false, maxCnt);
            }
        }

        static class Result{
            boolean win;
            int cnt;

            Result(boolean win, int cnt){
                this.win = win; this.cnt = cnt;
            }
        }
    }
}
