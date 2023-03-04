import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * dfs에서 반복문 조심하자.. 특히 for문안에서 index를 누적시키고 있다면, for문을 재귀로 바꾸는 방법이 더 안전하다.
 */
public class June {

    public static boolean checkAnswer = false;

    public static int[][] answerSudoku = new int[9][9];

    /**
     * 처음 풀이 실패 코드
     * @param count
     * @param sudoku
     */
    public static void dfsFail(int count, int[][] sudoku){
        if(checkAnswer) return;
        while(count<81){
            int y = count/9;
            int x = count%9;
            if(sudoku[y][x] == 0){
                boolean flag = false;
                for(int i = 1;i<=9;i++){
                    if(checkPossibleNumber(sudoku,y,x,i)) {
                        flag = true;
                        sudoku[y][x] = i;
                        dfsFail(count + 1, sudoku);
                        sudoku[y][x] = 0;
                    }
                }
                if(flag == false) return;
            }
            count++;
        }
        if(checkAnswer == false){
            checkAnswer = true;
            copyArray(sudoku,answerSudoku);
        }
    }

    /**
     * 성공 코드
     * @param count
     * @param sudoku
     */
    // 정답인 케이스만 주어짐
    public static void dfs(int count, int[][] sudoku){
        if(checkAnswer) return;
        int y = count/9;
        int x = count%9;
        if(count == 81){
            checkAnswer = true;
            copyArray(sudoku,answerSudoku);
            return;
        }

        if(sudoku[y][x] != 0){
            dfs(count+1,sudoku);
        }
        else{
            for(int i=1; i<=9;i++){
                if(checkPossibleNumber(sudoku,y,x,i)) {
                    sudoku[y][x] = i;
                    dfs(count + 1, sudoku);
                    sudoku[y][x] = 0;
                }
            }
        }
    }

    private static boolean checkPossibleNumber(int[][] sudoku, int y, int x , int targetNum) {
        // 가로
        for(int i=0;i<9;i++){
            if(sudoku[y][i] == targetNum) return false;
        }
        // 세로
        for(int i=0;i<9;i++){
            if(sudoku[i][x] == targetNum) return false;
        }
        // 사각
        int squareY = (y/3)*3;
        int squareX = (x/3)*3;
        for(int i=squareY;i<squareY+3;i++){
            for(int j=squareX;j<squareX+3;j++){
                if(sudoku[i][j] == targetNum) return false;
            }
        }
        return true;
    }

    private static void copyArray(int[][] sudoku, int[][] answerSudoku) {
        for(int i = 0; i<sudoku.length;i++){
            for(int j = 0; j<sudoku[0].length;j++){
                answerSudoku[i][j] = sudoku[i][j];
            }
        }
    }

    public static void getAnswer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int [][] sudoku = new int[9][];
        for(int i = 0; i<9;i++){
            sudoku[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
        }
        dfs(0,sudoku);
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<9;i++){
            for(int j= 0; j<9; j++){
                sb.append(answerSudoku[i][j]);
                if(j == 8) sb.append("\n");
                else sb.append(" ");
            }
        }
        System.out.println(sb.toString());
    }

    public static void main(String args[]) throws Exception{
        getAnswer();
    }
}
