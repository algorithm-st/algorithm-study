import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 풀이 유형  bfs + priority queue, 메모제이션
 * 풀이
 * - priority queue ( 시간 -> virus index 순)
 * - bfs돌면서 방문한곳 virus index 적기
 * - s초 > return 0, s초 <= return virus index
 * 걸린 시간 25
 * 시간복잡도 N*N + Nlog(N)
 * 공간복잡도 N*N
 */
public class Henry {
    public static int[] dy = {0,1,0,-1};
    public static int[] dx = {1,0,-1,0};

    public static void getAnswer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int [][] table = new int[N][N];
        for(int i=0;i<N;i++){
            table[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
        }
        st = new StringTokenizer(br.readLine());
        int S = Integer.parseInt(st.nextToken());
        int Y = Integer.parseInt(st.nextToken())-1;
        int X = Integer.parseInt(st.nextToken())-1;
        // y, x, 초 , virus index
        Queue<int[]> queue = new PriorityQueue<>((o1, o2) -> {
            if(o1[2] == o2[2]) return o1[3] - o2[3];
            return o1[2] - o2[2];
        });

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(table[i][j] != 0){
                    queue.add(new int[]{i,j,0,table[i][j]});
                }
            }
        }
        while(!queue.isEmpty()){
            int [] curIndex = queue.poll();
            if(curIndex[2] > S) {
                System.out.println(0);
                return;
            }
            if(curIndex[0] == Y && curIndex[1] == X && curIndex[2] <= S){
                System.out.println(curIndex[3]);
                return;
            }
            for(int i=0;i<4;i++){
                int tempy = curIndex[0] + dy[i];
                int tempx = curIndex[1] + dx[i];
                if(tempy>=0 && tempx>=0 && tempy<N && tempx<N && table[tempy][tempx] ==0){
                    table[tempy][tempx] = curIndex[3];
                    queue.add(new int[]{tempy,tempx,curIndex[2]+1,curIndex[3]});
                }
            }
        }
    }

    public static void main(String args[]) throws Exception{
        getAnswer();
    }
}
