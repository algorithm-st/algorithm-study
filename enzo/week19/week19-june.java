class Solution {
    public int solution(String[] board) {
        int answer = -1;
        int xCount = 0;
        int oCount = 0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board[i].charAt(j) == 'O'){
                    oCount++;
                }
                if(board[i].charAt(j) == 'X'){
                    xCount++;
                }
            }
        }
        boolean oWin = isWin(board, 'O');
        boolean xWin = isWin(board, 'X');
        if(oCount >= xCount && oCount - xCount <= 1 ){
            if(oWin == true && xWin == true){
                answer = 0;
            }
            else if(xWin == true && oCount > xCount){
                answer = 0;
            }
            else if(oWin == true && oCount == xCount){
                answer =0;
            }
            else {
                answer = 1;
            }
        }
        else {
            answer = 0;
        }
        return answer;
    }
    public boolean isWin(String[] board, char winChar){
        for(int i=0; i<3; i++){
            if(board[i].charAt(0) == winChar && board[i].charAt(0) == board[i].charAt(1) && board[i].charAt(1) == board[i].charAt(2)){
                return true;
            }
            if(board[0].charAt(i) == winChar && board[0].charAt(i) == board[1].charAt(i) && board[1].charAt(i) == board[2].charAt(i)){
                return true;
            }
        }
        if(board[0].charAt(0) == winChar && board[0].charAt(0) == board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(2)){
            return true;
        }
        if(board[0].charAt(2) == winChar && board[0].charAt(2) == board[1].charAt(1) && board[1].charAt(1) == board[2].charAt(0)){
            return true;
        }
        return false;

    }
}