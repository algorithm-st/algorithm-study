import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class June {


    public static void getAnswer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int [][] table = new int[n+1][n+1];
        for(int i=0;i<m;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());
            if(table[start][end] == 0) table[start][end] = val;
            else table[start][end] = Math.min(table[start][end],val);
        }
        for(int i=0;i<=n;i++){
            for(int j=0;j<=n;j++){
                if(i==j) table[i][j] = 0;
                else if(table[i][j] ==0) table[i][j] = Integer.MAX_VALUE/2;
            }
        }

        for(int k=1;k<=n;k++){
            for(int i=1;i<=n;i++){
                for(int j=1;j<=n;j++){
                    table[i][j] = Math.min(table[i][j], table[i][k] + table[k][j]);
                }
            }
        }
        StringBuffer sb = new StringBuffer();
        for(int i=1;i<=n;i++){
            for(int j=1;j<=n;j++){
                if(table[i][j] == Integer.MAX_VALUE/2) sb.append("0 ");
                else sb.append(table[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());

    }
    public static void main(String []args) throws IOException {
        getAnswer();
    }
}
