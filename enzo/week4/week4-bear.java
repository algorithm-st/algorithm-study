import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 풀이 유형  bfs, 메모제이션 + (tree map)
 * 풀이
 * - tree map으로 높이 만큼 배열 저장
 * - 저장된 높이 만큼 for문으로 0으로 table 변경
 * - 변경된 table bfs로 최대 영역 수 갱신
 * 걸린 시간 25
 * 시간복잡도 N*N*N
 */
public class Bear {
    public static int[] dy = {0,1,0,-1};
    public static int[] dx = {1,0,-1,0};
    public static void getAnswer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int [][] table = new int[N][N];
        for(int i =0; i<N;i++){
            table[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
        }
        Map<Integer, List<int[]>> map = new TreeMap<>();
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(!map.containsKey(table[i][j])){
                    map.put(table[i][j],new ArrayList<>());
                }
                map.get(table[i][j]).add(new int[]{i,j});
            }
        }
        int answer = 1;
        for(int i : map.keySet()){
            for(int[] index : map.get(i)){
                table[index[0]][index[1]] = 0;
            }
            answer = Math.max(answer,doBfsGetAreaCount(N,table));
        }
        System.out.println(answer);
    }

    private static int doBfsGetAreaCount(int N, int[][] table) {
        int answer = 0;
        boolean [][] check = new boolean[N][N];
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(table[i][j] !=0 && check[i][j]==false){
                    answer++;
                    Queue<int[]> queue = new LinkedList<>();
                    check[i][j] = true;
                    queue.add(new int[]{i,j});
                    while(!queue.isEmpty()){
                        int [] curIndex = queue.poll();
                        for(int k=0;k<4;k++){
                            int tempy = curIndex[0] + dy[k];
                            int tempx = curIndex[1] + dx[k];
                            if(tempy>=0&& tempx>=0&& tempy<N && tempx<N && check[tempy][tempx]==false && table[tempy][tempx]!=0){
                                check[tempy][tempx] = true;
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
