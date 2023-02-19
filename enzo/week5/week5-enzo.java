
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class Enzo {

    // 우선 순위 위쪽, 왼쪽, 아래, 오른쪽
    public static int [] dy = {-1,0,1,0};
    public static int [] dx = {0,-1,0,1};
    static class Shark {
        int level = 2;
        int status =0;
        int y;
        int x;
        public Shark(int y, int x) {
            this.y = y;
            this.x = x;
            this.level = 2;
            this.status = 0;
        }
        public void eat(){
            status +=1;
            if(this.status == this.level){
                level +=1;
                this.status = 0;
            }
        }
        public boolean isPossibleEat(int size){
            if(size<level && size>0) return true;
            return false;
        }

        public void movePosition(int y , int x) {
            this.y = y;
            this.x= x;
        }
    }
    public static void getAnswer() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int [][] table = new int[N][];
        for(int i =0;i<N;i++){
            table[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
        }
        Shark shark = null;
        for(int i=0;i<N;i++){
            for(int j =0;j<N;j++){
                if(table[i][j] == 9){
                    shark = new Shark(i,j);
                    table[i][j] =0;
                }
            }
        }
        int answer = 0;
        boolean flag = true;
        while(flag){
            int curSecond = startSharkMove(N,shark,table);
            answer += curSecond;
            if(curSecond == 0) flag = false;
        }
        System.out.println(answer);
    }

    private static int startSharkMove(int N, Shark shark, int[][] table) {
        boolean [][] check = new boolean[N][N];
        Queue<int[]> queue = new LinkedList<>();
        int startY = shark.y;
        int startX = shark.x;
        check[startY][startX] = true;
        queue.add(new int[]{startY,startX,0});
        int sharkPosY = Integer.MAX_VALUE;
        int sharkPosX = Integer.MAX_VALUE;
        int queueLevel = 0;
        while(!queue.isEmpty()){
            int[] curIndex = queue.poll();
            if(queueLevel>0 && queueLevel+1 == curIndex[2]) break;
            if(shark.isPossibleEat(table[curIndex[0]][curIndex[1]])){
                queueLevel = curIndex[2];
                if(curIndex[0]<sharkPosY){
                    sharkPosY = curIndex[0];
                    sharkPosX = curIndex[1];
                }
                else if(curIndex[0] == sharkPosY && curIndex[1] < sharkPosX){
                    sharkPosX = curIndex[1];
                }
            }
            for(int k=0;k<4;k++){
                int tempy = curIndex[0]+ dy[k];
                int tempx = curIndex[1] + dx[k];
                if(tempy>=0 && tempx>=0 && tempy<N && tempx<N && !check[tempy][tempx] && shark.level >= table[tempy][tempx]){
                    check[tempy][tempx] = true;
                    queue.add(new int[]{tempy,tempx,curIndex[2]+1});
                }
            }
        }

        if(queueLevel> 0){
            shark.movePosition(sharkPosY,sharkPosX);
            table[sharkPosY][sharkPosX]  = 0;
            shark.eat();
            return queueLevel;
        }
        return 0;
    }

    public static void main(String args[]) throws Exception{
        getAnswer();
    }
}
