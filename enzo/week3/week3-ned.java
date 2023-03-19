
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Ned {

    public static int N;
    public static int[][] table;
    public static int[] dy = {0,1,0,-1};
    public static int[] dx = {1,0,-1,0};
    public static int dfs(int y, int x){
        if(table[y][x] == 0) return 0;

        table[y][x] = 0;
        int num = 1;

        for(int i=0;i<4;i++){
            int tempy = y + dy[i];
            int tempx = x + dx[i];
            if(tempy>=0&& tempx>=0 && tempy<N && tempx<N && table[tempy][tempx] == 1){
                num += dfs(tempy,tempx);
            }
        }
        return num;
    }
    private static void getAnswer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        table = new int[N][];
        for(int i=0;i<N;i++){
            table[i] = Arrays.stream(br.readLine().split("")).mapToInt(s->Integer.parseInt(s)).toArray();
        }
        List<Integer> array = new ArrayList<>();
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(table[i][j] ==1){
                    array.add(dfs(i,j));
                }
            }
        }
        Collections.sort(array);
        System.out.println(array.size());
        array.forEach(i->System.out.println(i));
    }

    public static void main(String [] args) throws Exception{
        getAnswer();
    }
}
