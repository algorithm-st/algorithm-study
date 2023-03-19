import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Enzo {

    // 반시계
    public static int[] dy = {0,-1,0,1};
    public static int[] dx = {1,0,-1,0};
    public static void getAnswer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        boolean [][] checkPointTable = new boolean[101][101];
        for(int i=0;i<T;i++){
            int[] dragonCurve = Arrays.stream(br.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
            drawDragonCurve(checkPointTable,dragonCurve);
        }
        int answer = checkSquarePointTrue(checkPointTable);
        System.out.println(answer);
    }

    private static int checkSquarePointTrue(boolean[][] checkPointTable) {
        int answer = 0;
        for(int i=0;i<100;i++){
            for(int j=0;j<100;j++){
                if(checkPointTable[i][j] && checkPointTable[i+1][j] && checkPointTable[i][j+1] && checkPointTable[i+1][j+1]){
                    answer++;
                }
            }
        }
        return answer;
    }

    private static void drawDragonCurve(boolean[][] checkPointTable, int[] dragonCurve) {
        int g = dragonCurve[3];
        checkPointTable[dragonCurve[1]][dragonCurve[0]] = true;
        // y, x , end -> start 방향
        List<int[]> arrays = new ArrayList<>();
        arrays.add(new int[]{dragonCurve[1]+dy[dragonCurve[2]],dragonCurve[0]+dx[dragonCurve[2]],(dragonCurve[2]+2)%4});
        for(int i=0;i<g;i++){
            int N = arrays.size();
            int curY = arrays.get(N-1)[0];
            int curX = arrays.get(N-1)[1];
            for(int j=N-1;j>=0;j--){
                // end -> start 방향
                int reverseDir = (arrays.get(j)[2]+1)==4? 0 : arrays.get(j)[2]+1;
                // start -> end 방향
                int dir = (reverseDir+2)%4;
                int tempY = curY+dy[dir];
                int tempX = curX+dx[dir];
                arrays.add(new int[]{tempY,tempX,reverseDir});
                curY = tempY;
                curX = tempX;
            }
        }
        for(int[] i : arrays){
            if(i[0]>=0 && i[1]>=0 && i[0]<=100 && i[1]<=100){
                checkPointTable[i[0]][i[1]] = true;
            }
        }
    }

    public static void main(String []args) throws IOException {
        getAnswer();
    }
}
