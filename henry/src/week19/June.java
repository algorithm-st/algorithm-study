package week19;

/**
 문제 설명
 - 3 x 3의 빈칸으로 이루어진 게임판에 선공이 "O", 후공이 "X"
 - 가로, 세로, 대각선으로 3개가 같은 표시가 만들어지면 같은 표시를 만든 사람이 승리
 - 9칸이 다 차서 더이상 표시 불가 -> 무승부

 - 규칙을 어기는 실수
 1. "O"를 표시할 차례인데 "X"를 표시하거나 반대로 "X"를 표시할 차례인데 "O"를 표시한다.
 2. 선공이나 후공이 승리해서 게임이 종료되었음에도 그 게임을 진행한다.

 - 나올 수 있는 게임 상황이라면 1, 아니라면 0을 return
 */
public class June {
    static class Solution {
        boolean flag = false;
        char[][] tmpBoard = new char[3][3];
        public int solution(String[] board) {
            for(int i=0; i<3; i++){
                for(int j=0; j<3; j++){
                    tmpBoard[i][j] = '.';
                }
            }

            dfs(tmpBoard, board, true, 0);
            if(flag){
                return 1;
            }else{
                return 0;
            }
        }

        void dfs(char[][] tmpBoard, String[] board, boolean isCircle, int cnt){
            if(flag){
                return;
            }

            boolean sameFlag = true;
            for(int i=0; i<3; i++){
                if(!board[i].equals(String.valueOf(tmpBoard[i]))){
                    sameFlag = false;
                }
                if(!sameFlag){
                    break;
                }
            }
            if(sameFlag){
                flag = true;
                return;
            }

            if(cnt>=9){
                return;
            }

            // 게임 종료 체크
            if(isFinish(tmpBoard)){
                return;
            }

            for(int i=0; i<3; i++){
                for(int j=0; j<3; j++){
                    if('.' == tmpBoard[i][j]){
                        if(isCircle){
                            tmpBoard[i][j] = 'O';
                        }else{
                            tmpBoard[i][j] = 'X';
                        }
                        dfs(tmpBoard, board, !isCircle, cnt+1);
                        tmpBoard[i][j] = '.';
                    }
                }
            }
        }

        boolean isFinish(char[][] tmpBoard){

            char[] alphabets = {'O', 'X'};

            for(char alphabet : alphabets){
                //가로
                for(int i=0; i<3; i++){
                    if(tmpBoard[i][0] == alphabet && tmpBoard[i][1] == alphabet && tmpBoard[i][2] == alphabet){
                        return true;
                    }
                }
                // 세로
                for(int i=0; i<3; i++){
                    if(tmpBoard[0][i] == alphabet && tmpBoard[1][i] == alphabet && tmpBoard[2][i] == alphabet){
                        return true;
                    }
                }

                // 대각선
                if(tmpBoard[0][0] == alphabet && tmpBoard[1][1] == alphabet && tmpBoard[2][2] == alphabet){
                    return true;
                }
                if(tmpBoard[2][0] == alphabet && tmpBoard[1][1] == alphabet && tmpBoard[0][2] == alphabet){
                    return true;
                }
            }

            return false;
        }
    }
}
