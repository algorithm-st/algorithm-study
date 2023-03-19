
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Henry {

    public static void getAnswer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken());
        int E = Integer.parseInt(st.nextToken());
        int[][] table = new int[V][V];
        for(int i=0;i<E;i++){
            String[] nums = br.readLine().split(" ");
            int start = Integer.parseInt(nums[0])-1;
            int end = Integer.parseInt(nums[1])-1;
            int val = Integer.parseInt(nums[2]);
            table[start][end] = val;
        }
        for(int i=0;i<V;i++){
            for(int j=0;j<V;j++){
                if(i==j) table[i][j] = 0;
                else if(table[i][j] == 0){
                    table[i][j] = Integer.MAX_VALUE/2;
                }
            }
        }
        for(int k=0;k<V;k++){
            for(int i=0;i<V;i++){
                for(int j=0;j<V;j++){
                    table[i][j] = Math.min(table[i][j],table[i][k]+table[k][j]);
                }
            }
        }
        int answer = Integer.MAX_VALUE/2;
        for(int i=0;i<V;i++){
            for(int j=0;j<V;j++){
                if(i==j) continue;
                answer = Math.min(answer,table[i][j]+table[j][i]);
            }
        }
        if(answer == Integer.MAX_VALUE/2){
            System.out.println(-1);
        }
        else System.out.println(answer);
    }
    public static void main(String []args) throws IOException {
        getAnswer();
    }
}
