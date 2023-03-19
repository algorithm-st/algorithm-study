
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Henry {

    public static int maxAnswer = Integer.MIN_VALUE;
    public static int minAnswer = Integer.MAX_VALUE;
    public static void dfs(String s, int total){
        int count = (int)Arrays.stream(s.split("")).filter(i -> Integer.parseInt(i) % 2 == 1).count();
        if(s.length() == 1) {
            maxAnswer = Math.max(maxAnswer,total+count);
            minAnswer = Math.min(minAnswer,total+count);
        }
        int nextNum = 0;
        if(s.length() == 2){
            nextNum = (s.charAt(0) - '0') + (s.charAt(1)-'0');
            dfs(Integer.toString(nextNum),total+count);
        }
        else{
            for(int i = 1;i<s.length()-1;i++){
                for(int j=i+1;j<s.length();j++){
                    int first = Integer.parseInt(s.substring(0,i));
                    int second = Integer.parseInt(s.substring(i,j));
                    int third = Integer.parseInt(s.substring(j));
                    int nextFlag = first+second+third;
                    dfs(Integer.toString(nextFlag),total+count);
                }
            }
        }
    }
    public static void getAnswer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str= br.readLine();
        dfs(str, 0);
        System.out.println(minAnswer+" "+maxAnswer);
    }
    public static void main(String []args) throws IOException {
        getAnswer();
    }
}
