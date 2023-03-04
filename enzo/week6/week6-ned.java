import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Ned {

    public static void getAnswer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i<T;i++){
            String pattern = br.readLine();
            int n = Integer.parseInt(br.readLine());
            String s = br.readLine();
            s = s.substring(1, s.length() - 1);
            int []array;
            if(n>0) array = Arrays.stream(s.split(",")).mapToInt(c->Integer.parseInt(c)).toArray();
            else array = new int[0];
            String curAnswer = doFunction(n,pattern,array);
            sb.append(curAnswer).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static String doFunction(int n, String pattern, int[] array) {
        boolean reverse = false;
        int startIndex = 0;
        int lastIndex  = array.length-1;
        for(int i=0;i<pattern.length();i++){
            char command = pattern.charAt(i);
            if(command == 'R'){
                reverse = !reverse;
            }
            else{
                if(reverse == false){
                    startIndex++;
                }
                else {
                    lastIndex--;
                }
            }
        }
        if(startIndex>lastIndex+1) return "error";
        StringBuffer sb = new StringBuffer();
        sb.append("[");
        if(reverse == false){
            for(int i=startIndex;i<=lastIndex;i++){
                if(i!=lastIndex) sb.append(array[i]).append(",");
                else sb.append(array[lastIndex]);
            }
        }
        else{
            for(int i=lastIndex;i>=startIndex;i--){
                if(i!=startIndex) sb.append(array[i]).append(",");
                else sb.append(array[startIndex]);
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public static void main(String args[]) throws IOException {
        getAnswer();
    }
}