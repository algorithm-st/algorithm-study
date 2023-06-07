import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Henry {
    public int[] dy = {0,1,0,-1};
    public int[] dx = {1,0,-1,0};
    public int solution(int[][] board) {
        int answer = 0;
        int n = board.length;
        int m = board[0].length;
        int [][][] priceBoard = new int[n][m][4];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                Arrays.fill(priceBoard[i][j],Integer.MAX_VALUE/2);
            }
        }
        // y, x, dir, price
        Queue<int[]> queue = new PriorityQueue<>((o1,o2)->{
            return o1[3] - o2[3];
        });
        for(int i=0;i<4;i++){
            priceBoard[0][0][i] = 0;
            queue.add(new int[]{0,0,i,0});
        }
        while(!queue.isEmpty()){
            int [] curIndex = queue.poll();
            if(curIndex[0] == n-1 && curIndex[1] == m-1) return curIndex[3];
            for(int i=0;i<4;i++){
                int tempY = curIndex[0] + dy[i];
                int tempX = curIndex[1] + dx[i];
                if(tempY>=0 && tempY<n && tempX>=0 && tempX<m && board[tempY][tempX] == 0){
                    int tempPrice = (curIndex[2] == i) ? 100 : 600;
                    int totalPrice = tempPrice + curIndex[3];
                    if(totalPrice < priceBoard[tempY][tempX][i]){
                        queue.add(new int[]{tempY,tempX,i,totalPrice});
                        priceBoard[tempY][tempX][i] = totalPrice;
                    }
                }
            }
        }
        return answer;
    }
}
