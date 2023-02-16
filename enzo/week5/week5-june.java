
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class June {

    public static void getAnswer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int [] array = Arrays.stream(br.readLine().split(" ")).mapToInt(i->Integer.parseInt(i)).toArray();
        int [] answer  = new int[N];
        // 빈칸은 나보다 높은 값이 들어가는 칸이다
        for(int i=0;i<N;i++){
            int count = array[i];
            for(int j=0;j<N;j++){
                if(answer[j] != 0 ) continue;
                if(count == 0){
                    answer[j] = i+1;
                    break;
                }
                count--;
            }

        }
        StringBuffer sb = new StringBuffer();
        for(int i : answer){
            sb.append(i).append(" ");
        }
        System.out.println(sb.toString());
    }
    public static void main(String args[]) throws Exception{
        getAnswer();
    }
}