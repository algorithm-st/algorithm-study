import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Bear {

    public static void getAnswer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int [] array = Arrays.stream(br.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
        int min = 0;
        int max = 1_000_000_000;
        while(min<max){
            int mid = (min+max)/2;
            long total = 0;
            for(int i=0;i<array.length;i++){
                if(mid<array[i]){
                    total += array[i]-mid;
                }
            }
            if(total>=(long)M){
                min = mid+1;
            }
            else max = mid;
        }
        System.out.println(min-1);

    }
    public static void main(String args[]) throws IOException {
        getAnswer();
    }
}
