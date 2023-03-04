import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Ned {

    public static void getAnswer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N][];
        for(int i=0;i<N;i++){
            arr[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
        }

        int startY =0;
        int startX =0;
        int endY = N-1;
        int endX = M-1;
        while(startX<endX && startY<endY){
            doRotate(startX,startY,endX,endY,R,arr);
            startX++;
            startY++;
            endX--;
            endY--;
        }
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                sb.append(arr[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void doRotate(int startX, int startY, int endX, int endY, int r, int[][] arr) {
        int R =  endY- startY+1;
        int C = endX - startX+1;
        int total = 2*R+2*C-4;
        int [] tempArr = new int[total];
        int count = 0;
        for(int i=0;i<C-1;i++){
            tempArr[count++] = arr[startY][startX+i];
        }
        for(int i=0;i<R-1;i++){
            tempArr[count++] = arr[startY+i][endX];
        }
        for(int i=0;i<C-1;i++){
            tempArr[count++] = arr[endY][endX-i];
        }
        for(int i=0;i<R-1;i++){
            tempArr[count++] = arr[endY-i][startX];
        }

        for(int i=0;i<C-1;i++,r++){
            arr[startY][startX+i] = tempArr[r%total];
        }
        for(int i=0;i<R-1;i++,r++){
            arr[startY+i][endX] = tempArr[r%total];
        }
        for(int i=0;i<C-1;i++,r++){
            arr[endY][endX-i] = tempArr[r%total];
        }
        for(int i=0;i<R-1;i++,r++){
            arr[endY-i][startX] = tempArr[r%total];
        }
    }

    public static void main(String[] args) throws  IOException{
        getAnswer();
    }
}