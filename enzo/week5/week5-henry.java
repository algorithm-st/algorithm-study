import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Henry {

    public static int [] dy = {-1,0,1,0};
    public static int [] dx = {0,1,0,-1};

    public static void getAnswer() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int [][] table = new int[N][N];
        Map<Integer,int[]> map = new HashMap<>();
        for(int i=0;i<N*N;i++){
            int[] val = Arrays.stream(br.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
            update(N,table,val);
            map.put(val[0],val);
        }
        int answer = 0;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                int[] curCal = calCurrentTable(N, table, map.get(table[i][j]), i, j);
                switch(curCal[0]){
                    case 0 :
                        answer += 0;
                        break;
                    case 1 :
                        answer += 1;
                        break;
                    case 2 :
                        answer += 10;
                        break;
                    case 3 :
                        answer +=100;
                        break;
                    case 4 :
                        answer +=1000;
                        break;
                }
            }
        }
        System.out.println(answer);
    }
    public static void update(int N, int[][] table, int[] val){
        // 0 : 좋아하는 사람 수 , 1 : 비어있는 칸, 2 : y , 3: x
        Queue<int[]> queue = new PriorityQueue<>((o1,o2)->{
            if(o1[0] == o2[0]){
                if(o1[1] == o2[1]){
                    if(o1[2] == o2[2]){
                        return o1[3] - o2[3];
                    }
                    return o1[2] - o2[2];
                }
                return o2[1] - o1[1];
            }
            return o2[0] - o1[0];
        });
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(table[i][j] !=0) continue;
                queue.add(calCurrentTable(N,table,val,i,j));
            }
        }
        int [] pq = queue.poll();
        table[pq[2]][pq[3]] = val[0];
    }

    private static int[] calCurrentTable(int N, int[][] table, int[] val, int y, int x) {
        int [] calArray = new int[4];
        calArray[2] = y;
        calArray[3] = x;
        for(int i=0;i<4;i++){
            int tempy = y + dy[i];
            int tempx = x + dx[i];
            if(tempy>=0 && tempx>=0 && tempy<N && tempx<N){
                if(table[tempy][tempx] == 0 ){
                    calArray[1] +=1;
                    continue;
                }
                for(int j=1;j<val.length;j++){
                    if(table[tempy][tempx] == val[j]){
                        calArray[0] +=1;
                    }
                }
            }
        }
        return calArray;
    }

    public static void main(String args[]) throws Exception{
        getAnswer();
    }
}
