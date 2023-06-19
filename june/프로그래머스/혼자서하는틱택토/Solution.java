package june.프로그래머스.혼자서하는틱택토;

public class Solution {
    public int solution(String[] board) {

        int O = 0;
        int X = 0;
        
        for(String line : board){
            String[] row = line.split("");
            for(String l : row){
                if(l.equals("O")){
                    O++;
                }else if(l.equals("X")){
                    X++;
                }
            }
        }

        if (Math.abs(O - X) > 1) {
            return 0; // O, X가 2개 이상 차이나면
        }
        if (X - O > 0) {
            return 0; // X가 O 보다 많으면
        }
        if(X==O){
            if (bingo(board, 'O')) {
                return 0; // O,X 갯수 같은데 O가 빙고이면
            }
        }
        if(O-X>0){
            if (bingo(board, 'X')) {
                return 0; // O가 더 많은데 X가 빙고이면
            }
        }
        
        return 1;
    }
    
    public boolean bingo(String[] board, char mark){
        int count = 0;
        // 가로
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if (board[i].charAt(j) == mark) {
                    count++;
                }
                if (count == 3) {
                    return true;
                }
            }
            count = 0;
        }
        
        // 세로
        count = 0;
        for(int i=0; i<3; i++){
            for(int j=0; j<3; j++){
                if (board[j].charAt(i) == mark) {
                    count++;
                }
                if (count == 3) {
                    return true;
                }
            }
            count = 0;
        }
        
        // 대각
        count = 0;
        for(int i=0; i<3; i++){
            if (board[i].charAt(i) == mark) {
                count++;
            }
            if (count == 3) {
                return true;
            }
        }
        count = 0;
        for(int i=0; i<3; i++){
            if (board[i].charAt(2 - i) == mark) {
                count++;
            }
            if (count == 3) {
                return true;
            }
        }
        return false;
    }
}