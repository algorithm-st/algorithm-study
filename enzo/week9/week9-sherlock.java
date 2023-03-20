
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sherlock {

    public static void getAnswer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        double x = Double.parseDouble(st.nextToken());
        double y = Double.parseDouble(st.nextToken());
        double c = Double.parseDouble(st.nextToken());
        double min = 0;
        double max = Math.min(y,x);
        while(max-min>=0.001){
            double mid = (min+max)/2;
            double xVal = Math.sqrt(Math.pow(x,2)-Math.pow(mid,2));
            double yVal = Math.sqrt(Math.pow(y,2)-Math.pow(mid,2));
            double result = c*(1/xVal + 1/yVal);
            if (result>1){
                max = mid;
            }
            else{
                min = mid;
            }
        }
        System.out.println(String.format("%.3f",min));
    }
    public static void main(String []args) throws IOException {
        getAnswer();
    }
}
