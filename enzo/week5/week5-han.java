import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Han {

    public static void getAnswer() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        for(int i =0;i<3;i++){
            int oneCount = Arrays.stream(br.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).sum();
            if(oneCount == 1){
                sb.append("C");
            }
            else if(oneCount == 2){
                sb.append("B");
            }
            else if(oneCount == 3){
                sb.append("A");
            }
            else if(oneCount == 4){
                sb.append("E");
            }
            else{
                sb.append("D");
            }
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }
    public static void main(String [] args) throws Exception{
        getAnswer();
    }
}
