import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static int maxAnswer = 0 ;
    public static boolean [][] check;
    public static int [][] array;
    public static int N,M;
    public static void dfs(int y, int x){
        if(y == N && x == 0){
            int curAnswer = calArray();
            maxAnswer = Math.max(maxAnswer,curAnswer);
            return;
        }
        int nextY = y;
        int nextX = x+1;
        if(nextX == M){
            nextY  = y+1;
            nextX = 0;
        }
        // check 된 경우
        check[y][x] = true;
        dfs(nextY,nextX);
        check[y][x] = false;
        //check 되지 않은 경우
        dfs(nextY,nextX);
    }

    // check 안되면 가로, check 되면 세로
    private static int calArray() {
        int answer = 0;
        boolean [][] curCheckArray = new boolean[N][M];
        for(int i=0;i<N;i++){
            for(int j=0;j<M;j++){
                if(curCheckArray[i][j]) continue;
                if(check[i][j]){
                    answer += sumCol(i,j,curCheckArray);
                }
                else{
                    answer += sumRow(i,j,curCheckArray);
                }
            }
        }
        return answer;
    }

    // 가로 계산
    private static int sumRow(int y, int x, boolean[][] curCheckArray) {
        int answer = 0;
        while(x != M && !check[y][x]){
            answer = answer*10 + array[y][x];
            curCheckArray[y][x] = true;
            x++;
        }
        return answer;
    }

    // 세로 계산
    private static int sumCol(int y, int x, boolean[][] curCheckArray) {
        int answer = 0;
        while(y !=N && check[y][x]){
            answer = answer *10 + array[y][x];
            curCheckArray[y][x] = true;
            y++;
        }
        return answer;
    }

    public static void getAnswer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        array = new int[N][];
        check = new boolean[N][M];

        for(int i = 0; i<N;i++){
            array[i] = Arrays.stream(br.readLine().split("")).mapToInt(s->Integer.parseInt(s)).toArray();
        }
        dfs(0,0);
        System.out.println(maxAnswer);
    }
    public static void main(String args[]) throws Exception {
        getAnswer();
    }
}
