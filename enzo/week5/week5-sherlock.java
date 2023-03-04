
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Sherlock {

    public static int [] dy = {-1,0,1,0};
    public static int [] dx = {0,1,0,-1};
    public static void getAnswer() throws Exception{
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int [][] table = new int[n][m];

        st = new StringTokenizer(br.readLine());
        int robotY = Integer.parseInt(st.nextToken());
        int robotX = Integer.parseInt(st.nextToken());
        int robotDir = Integer.parseInt(st.nextToken());

        for(int i=0;i<n;i++){
            table[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(s -> Integer.parseInt(s)).toArray();
        }



        int answer = 0;
        boolean flag= true;
        while(flag){
            answer += doOne(table,robotY,robotX);
            boolean hasMessy = false;
            int tempDir = robotDir;
            for(int i=0;i<4;i++){
                tempDir = tempDir-1<0? 3 : tempDir-1;
                int tempY = robotY + dy[tempDir];
                int tempX = robotX + dx[tempDir];
                if(tempY>=0 && tempX>=0 && tempY<n && tempX<m && table[tempY][tempX] == 0){
                    robotY = tempY;
                    robotX = tempX;
                    robotDir = tempDir;
                    hasMessy = true;
                    break;
                }
            }
            if(!hasMessy){
                int reverseDir = robotDir+2 >= 4 ? robotDir-2 : robotDir+2;
                robotY = robotY + dy[reverseDir];
                robotX = robotX + dx[reverseDir];
                if(table[robotY][robotX] == 1) {
                    break;
                }
            }
        }
        System.out.println(answer);
    }

    private static int doOne(int[][] table,int y, int x) {
        if(table[y][x] == 0) {
            table[y][x] = -1;
            return 1;
        }
        else return 0;
    }

    public static void main(String []args) throws Exception{
        getAnswer();
    }
}