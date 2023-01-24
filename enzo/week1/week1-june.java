
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class Main{

    public static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String[] strArray = new String[N];
        for(int i=0;i<N;i++){
            strArray[i] = br.readLine();
        }
        Arrays.sort(strArray,(o1,o2)->{
            if(o1.length() == o2.length()){
                return o1.compareTo(o2);
            }
            return o1.length() - o2.length();
        });
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<strArray.length;i++){
            if(i>0 && strArray[i-1].equals(strArray[i])) continue;
            sb.append(strArray[i]+"\n");
        }
        System.out.println(sb.toString());
        br.close();
    }
    public static void main(String[] args) throws Exception {
        solution();
    }
}