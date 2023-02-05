package week3_dfs;


import java.io.*;
import java.util.*;


public class June {

    static boolean answer = false;
    static ArrayList<Point> emptyPointList = new ArrayList<>();
    static int[][] board  = new int[9][9];

    static int[] getMiddle = {1, 1, 1, 4, 4, 4, 7, 7, 7};
    static int[] dr = {-1, -1, -1, 0, 0, 0, 1, 1, 1};
    static int[] dc = {-1, 0, 1, -1, 0, 1, -1, 0, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i=0; i<9; i++){
            String line = br.readLine();
            StringTokenizer st = new StringTokenizer(line," ");
            for(int j=0; j<9; j++) {
                board[i][j] = Integer.valueOf(st.nextToken());

                if (board[i][j] == 0) {
                    emptyPointList.add(new Point(i, j));
                }
            }
        }

        DFS(0);
    }
    static void DFS(int idx){
        if(answer==true) return ;

        if (idx == emptyPointList.size()) {
            answer = true;
            for(int i=0; i<9; i++){
                for(int j=0; j<9; j++){
                    System.out.print(board[i][j]+ " ");
                }
                System.out.println();
            }
        }else{
            Point emptyPoint = emptyPointList.get(idx);
            for(int j=1; j<=9; j++){
                board[emptyPoint.r][emptyPoint.c] = j;
                if(isValid(emptyPoint.r, emptyPoint.c)) {
                    DFS(idx + 1);
                }
                board[emptyPoint.r][emptyPoint.c] = 0;
            }
        }
    }

    static boolean isValid(int r, int c){
        int[] ch1 = new int[10];
        int[] ch2 = new int[10];
        int[] ch3 = new int[10];

        // 가로줄, 세로줄 체크
        for(int i=0; i<9; i++){
            ch1[board[r][i]]++;
            ch2[board[i][c]]++;

            if(board[r][i]!=0) {
                if (ch1[board[r][i]] == 2) {
                    return false;
                }
            }

            if(board[i][c]!=0){
                if(ch2[board[i][c]]==2) {
                    return false;
                }
            }
        }

        // 정사각형 체크
        int middleR = getMiddle[r];
        int middleC = getMiddle[c];
        for(int i=0; i<9; i++){
            int nr = middleR + dr[i];
            int nc = middleC + dc[i];
            if(board[nr][nc]!=0) {
                ch3[board[nr][nc]]++;
                if (ch3[board[nr][nc]] == 2) {
                    return false;
                }
            }
        }
        return true;
    }

    static class Point{
        int r, c;
        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
