import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Bear {

    public static int [] dy = {0,1,0,-1};
    public static int [] dx = {1,0,-1,0};
    public static void getAnswer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int [][] table = new int[N][];

        for(int i=0;i<N;i++){
            table[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
        }
        Queue<int[]> queue = new LinkedList<>();
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(table[i][j] == 1){
                    queue.add(new int[]{i,j,0});
                }
            }
        }
        int day = 0;
        while(!queue.isEmpty()){
            int [] cur = queue.poll();
            day = Math.max(day,cur[2]);
            for(int k=0;k<4;k++){
                int tempy = cur[0] + dy[k];
                int tempx = cur[1] + dx[k];
                if(tempy>=0 && tempx>=0 && tempy<N && tempx<M && table[tempy][tempx] == 0){
                    queue.add(new int[]{tempy,tempx,cur[2]+1});
                    table[tempy][tempx] = 1;
                }
            }
        }
        boolean flag = false;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(table[i][j] == 0){
                    flag = true;
                    break;
                }
            }
            if(flag) break;
        }
        if(flag) System.out.println(-1);
        else System.out.println(day);
    }
    public static void main(String args[]) throws IOException {
        getAnswer();
    }
}
