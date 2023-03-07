import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class JuneBonus {

    public static void getAnswer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int [][] edge = new int[m][];
        for(int i=0;i<edge.length;i++){
            edge[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
        }
        long [] minAnswer = new long[n+1];
        Arrays.fill(minAnswer,Integer.MAX_VALUE);
        minAnswer[1] = 0;
        boolean circle = false;
        for(int i=1;i<=n;i++){
            for(int j=0;j<m;j++){
                int start = edge[j][0];
                int end = edge[j][1];
                int val = edge[j][2];
                if(minAnswer[start] != Integer.MAX_VALUE && minAnswer[end] > minAnswer[start] + val){
                    if(i == n){
                        circle = true;
                    }
                    minAnswer[end] = minAnswer[start] + val;
                }
            }
        }
        StringBuffer sb = new StringBuffer();
        if(circle) sb.append("-1");
        else{
            for(int i=2;i<=n;i++){
                if(minAnswer[i] == Integer.MAX_VALUE) sb.append("-1");
                else sb.append(minAnswer[i]);
                sb.append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    public static void main(String [] args) throws IOException {
        getAnswer();
    }
}