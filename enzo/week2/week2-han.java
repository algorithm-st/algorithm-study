import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void getAnswer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long [] array = new long[12];
        array[1] = 1; // 1
        array[2] = 2; // 1+1,2
        array[3] = 4; // 1+1,
        for(int i=4;i<array.length;i++){
            array[i] = array[i-1]+array[i-2]+array[i-3];
        }
        StringBuilder sb=  new StringBuilder();
        for(int i=0;i<N;i++){
            int curNum = Integer.parseInt(br.readLine());
            sb.append(array[curNum]).append("\n");
        }
        System.out.println(sb.toString());
    }
    public static void main(String args[]) throws Exception{
        getAnswer();
    }
}