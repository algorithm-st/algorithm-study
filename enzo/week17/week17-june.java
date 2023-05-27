import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class June {
    public int [] dy = {0,1,0,-1};
    public int [] dx = {1,0,-1,0};
    public int solution(String[] board) {
        int answer = 0;
        int n = board.length;
        int m = board[0].length();
        int startY = -1;
        int startX = -1;
        for(int i=0;i<n; i++){
            for(int j=0;j<m;j++){
                if(board[i].charAt(j) == 'R'){
                    startY = i;
                    startX = j;
                    break;
                }
            }
            if(startY != -1 && startX != -1) break;
        }
        Queue<int[]> queue = new LinkedList<>();
        int[][][] checkTable = new int[n][m][4];
        queue.add(new int[]{startY,startX,0});
        Arrays.fill(checkTable[startY][startX], 1);
        while(!queue.isEmpty()){
            int [] curIndex = queue.poll();
            if(board[curIndex[0]].charAt(curIndex[1]) == 'G'){
                return curIndex[2];
            }

            for(int i=0;i<4;i++){
                int tempY = curIndex[0];
                int tempX = curIndex[1];
                while(true){
                    int nextY = tempY+dy[i];
                    int nextX = tempX+dx[i];
                    if(isIn(n,m,nextY,nextX) && board[nextY].charAt(nextX) != 'D'){
                        tempY = nextY;
                        tempX = nextX;
                    }
                    else break;
                }
                if(checkTable[tempY][tempX][i] == 0){
                    checkTable[tempY][tempX][i] = curIndex[2] +1;
                    queue.add(new int[]{tempY,tempX,curIndex[2]+1});
                }
            }
        }
        return -1;
    }
    public boolean isIn(int n, int m, int y, int x){
        return y>=0 && x>=0 && y<n && x<m;
    }
}
