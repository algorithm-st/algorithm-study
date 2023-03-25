import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Henry {

    public static int maxAnswer = 0;
    public static int curAnswer = 0;
    public static void getAnswer() throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        int [][] table = new int[N+1][M];
        for(int i=0;i<N;i++){
            table[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
        }
        int [][] archer = new int[3][2];
        for(int i=0;i<M-2;i++){
            for(int j=i+1;j<M-1;j++){
                for(int k=j+1;k<M;k++){
                    archer[0][0] = N;
                    archer[0][1] = i;
                    archer[1][0] = N;
                    archer[1][1] = j;
                    archer[2][0] = N;
                    archer[2][1] = k;
                    curAnswer = 0;
                    playGame(N,M,D,table,archer);
                    maxAnswer = Math.max(maxAnswer,curAnswer);

                }
            }
        }
        System.out.println(maxAnswer);
    }

    private static void playGame(int n, int m, int d, int[][] table, int[][] archer) {

        int [][] curTable = new int[n+1][m];
        for(int i=0;i<=n;i++){
            for(int j=0;j<m;j++){
                curTable[i][j] = table[i][j];
            }
        }
        int step = 0;
        while(step<n){
            attack(n,m,d,archer,curTable);
            move(n,m,curTable);
            step++;
        }
    }

    private static void attack(int n, int m, int d, int[][] archer, int[][] curTable) {
        int[][] erase = new int[3][2];
        for(int i=0;i<3;i++){
            Arrays.fill(erase[i], Integer.MAX_VALUE);
        }
        for(int i=0;i<3;i++){
            for(int j=1;j<=d;j++){
                boolean flag = false;
                for(int k=1;k<=j;k++){
                    int targetY = archer[i][0] - k;
                    int leftX = archer[i][1] - (j-k);
                    int rightX = archer[i][1] + (j-k);
                    if(targetY>=0){
                        if(leftX>=0 && curTable[targetY][leftX] == 1){
                            flag = true;
                            if(erase[i][1]>leftX){
                                erase[i][0] = targetY;
                                erase[i][1] = leftX;
                            }
                        }
                        if(rightX<m && curTable[targetY][rightX] == 1){
                            flag = true;
                            if(erase[i][1]>rightX){
                                erase[i][0] = targetY;
                                erase[i][1] = rightX;
                            }
                        }
                    }
                }
                if(flag){
                    break;
                }
            }
        }
        for(int i=0;i<erase.length;i++){
            if(erase[i][0] == Integer.MAX_VALUE) continue;
            if(curTable[erase[i][0]][erase[i][1]] == 1) curAnswer++;
            curTable[erase[i][0]][erase[i][1]] = 0;
        }
    }
    private static void move(int n, int m, int[][] curTable){
        for(int i=n-1;i>0;i--){
            for(int j=0;j<m;j++){
                curTable[i][j] = curTable[i-1][j];
            }
        }
        Arrays.fill(curTable[0],0);
    }

    public static void main(String []args) throws IOException {
        getAnswer();
    }
}
