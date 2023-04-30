package week14;

class Ned {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];

        Board board = new Board(rows, columns);
        for(int i=0; i< queries.length; i++){
            answer[i] = board.rotate(queries[i][0], queries[i][1], queries[i][2], queries[i][3]);
        }
        return answer;
    }
}

class Point{
    int r, c;
    Point(int r, int c){
        this.r = r; this.c = c;
    }
}

class Board{
    int[] dr = {0, 1, 0, -1};
    int[] dc = {1, 0, -1, 0};

    int r, c;
    int[][] board;

    Board(int r, int c){
        this.r = r; this.c = c;
        board = new int[r+1][c+1];
        int cnt = 0;
        for(int i=1; i<=r; i++)
            for(int j=1; j<=c; j++)
                board[i][j] = ++cnt;
    }

    int rotate(int r1, int c1, int r2, int c2){
        int result = Integer.MAX_VALUE;
        int colCnt = c2 - c1;
        int rowCnt = r2 - r1;
        int idx = 0;

        Point currentPoint = new Point(r1, c1);
        int[][] tmpBoard = new int[r+1][c+1];

        for(idx=0; idx<4; idx++){
            if(idx%2==0){
                for(int j=0; j<colCnt; j++){
                    Point nextPoint = new Point(currentPoint.r + dr[idx], currentPoint.c + dc[idx]);
                    tmpBoard[nextPoint.r][nextPoint.c] = board[currentPoint.r][currentPoint.c];
                    result = Math.min(result, board[currentPoint.r][currentPoint.c]);
                    currentPoint = nextPoint;
                }
            }else{
                for(int j=0; j<rowCnt; j++){
                    Point nextPoint = new Point(currentPoint.r + dr[idx], currentPoint.c + dc[idx]);
                    tmpBoard[nextPoint.r][nextPoint.c] = board[currentPoint.r][currentPoint.c];
                    result = Math.min(result, board[currentPoint.r][currentPoint.c]);
                    currentPoint = nextPoint;
                }
            }
        }

        currentPoint = new Point(r1, c1);
        for(idx=0; idx<4; idx++){
            if(idx%2==0){
                for(int j=0; j<colCnt; j++){
                    Point nextPoint = new Point(currentPoint.r + dr[idx], currentPoint.c + dc[idx]);
                    board[currentPoint.r][currentPoint.c] = tmpBoard[currentPoint.r][currentPoint.c];
                    currentPoint = nextPoint;
                }
            }else{
                for(int j=0; j<rowCnt; j++){
                    Point nextPoint = new Point(currentPoint.r + dr[idx], currentPoint.c + dc[idx]);
                    board[currentPoint.r][currentPoint.c] = tmpBoard[currentPoint.r][currentPoint.c];
                    currentPoint = nextPoint;
                }
            }
        }
        return result;
    }
}
