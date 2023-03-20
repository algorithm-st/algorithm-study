import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Ned {

    public static void getAnswer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int [] array = Arrays.stream(br.readLine().split(" ")).mapToInt(k->Integer.parseInt(k)).toArray();
        Arrays.sort(array);
        int answer =0;
        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(i==j) continue;
                int min = 0;
                int max = N-1;
                boolean flag = false;
                while(min<=max){
                    int mid = (max+min)/2;
                    if(array[mid]+array[j] == array[i]){
                        if(mid != j && mid !=i){
                            flag= true;
                            break;
                        }
                    }
                    if(array[mid]+array[j] <= array[i]){
                        min = mid+1;
                    }
                    else{
                        max = mid-1;
                    }
                }
                if(flag){
                    answer++;
                    break;
                }
            }

        }
        System.out.println(answer);
    }
    public static void main(String [] args) throws IOException {
        getAnswer();
    }
}