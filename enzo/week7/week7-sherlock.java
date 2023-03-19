import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Sherlock {

    public static void getAnswer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] table  = new int[N][];
        for(int i=0;i<N;i++){
            table[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
        }
        for(int k=0;k<N;k++){
            for(int i=0;i<N;i++){
                for(int j=0;j<N;j++){
                    if(table[i][k]>0 && table[k][j]>0) table[i][j] = 1;
                }
            }
        }
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                sb.append(table[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
    public static void main(String[] args) throws IOException {
        getAnswer();
    }
}
