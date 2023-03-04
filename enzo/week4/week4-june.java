import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class June {

    public static int [] dy = {0,1,0,-1};
    public static int [] dx = {1,0,-1,0};
    public static void getAnswer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int [][] array = new int[N][];
        for(int i=0;i<N;i++){
            array[i] = Arrays.stream(br.readLine().split("")).mapToInt(s->Integer.parseInt(s)).toArray();
        }
        boolean [][] check = new boolean[N][M];
        check[0][0] = true;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0,0,1});
        while(!queue.isEmpty()){
            int [] curIndex = queue.poll();
            if(curIndex[0] == N-1 && curIndex[1] == M-1){
                System.out.println(curIndex[2]);
                return;
            }
            for(int i=0;i<4;i++){
                int tempy = curIndex[0] + dy[i];
                int tempx = curIndex[1] + dx[i];
                if(tempy>=0 && tempx>=0 && tempy<N && tempx<M && array[tempy][tempx] == 1 && check[tempy][tempx] == false ){
                    check[tempy][tempx] = true;
                    queue.add(new int[]{tempy,tempx,curIndex[2]+1});
                }
            }
        }
    }
    public static void main(String [] args) throws Exception{
        getAnswer();
    }
}