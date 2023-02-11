
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 문제 난이도 easy
 * 풀이 유형  bfs + 메모제이션
 * 풀이
 * - int[][] 배열로 각 좌표 배추 1로 설정
 * - bfs돌면서 방문한곳 0으로 초기화
 * - 만약에 인접해있지 않는다면 answer++
 * - return answer
 * 걸린 시간 12분
 * 시간복잡도 N*M*T
 * 공간복잡도 N*M*T
 */
public class Sherlock {
    public static int[] dy = {0,1,0,-1};
    public static int[] dx = {1,0,-1,0};
    public static void getAnswer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<T;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int[][] array = new int[N][M];
            for(int j=0;j<K;j++){
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                array[y][x] = 1;
            }
            int curAnswer = bfsCount(N,M,array);
            sb.append(curAnswer).append("\n");
        }
        System.out.println(sb.toString());
    }
    public static int bfsCount(int N, int M, int[][] array){
        int answer =0;
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(array[i][j] == 1){
                    answer++;
                    array[i][j] = 0;
                    Queue<int[]> queue = new LinkedList<>();
                    queue.add(new int[]{i,j});
                    while(!queue.isEmpty()){
                        int [] curIndex = queue.poll();
                        for(int k=0;k<4;k++){
                            int tempy = curIndex[0] + dy[k];
                            int tempx = curIndex[1] + dx[k];
                            if(tempy>=0 && tempx>=0 && tempy<N && tempx<M && array[tempy][tempx]==1){
                                array[tempy][tempx] = 0;
                                queue.add(new int[]{tempy,tempx});
                            }
                        }
                    }
                }
            }
        }
        return answer;
    }
    public static void main(String args[]) throws Exception{
        getAnswer();
    }
}
