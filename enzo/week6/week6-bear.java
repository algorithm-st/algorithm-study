
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Bear {

    public static void getAnswer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int [] switchArr = new int[N+1];
        String[] s = br.readLine().split(" ");
        for(int i=1;i<=N;i++){
            switchArr[i] = Integer.parseInt(s[i-1]);
        }
        int studentCount = Integer.parseInt(br.readLine());
        for(int i=0;i<studentCount;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int gen = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            if(gen == 1){
                changeSwitchMan(switchArr,num);
            }
            else{
                changeSwitchWoman(switchArr,num);
            }
        }
        StringBuffer sb = new StringBuffer();
        for(int i=1; i<switchArr.length;i++){
            if(i!=1 && (i-1)%20 == 0) sb.append("\n");
            sb.append(switchArr[i]).append(" ");
        }
        System.out.println(sb.toString());
    }

    private static void changeSwitchWoman(int[] switchArr, int num) {
        int left = num-1;
        int right = num+1;
        switchArr[num] = Math.abs(switchArr[num]-1);
        while(left>0 && right<switchArr.length){
            if(switchArr[left] != switchArr[right]) break;
            switchArr[left] = Math.abs(switchArr[left]-1);
            switchArr[right] = Math.abs(switchArr[right]-1);
            left--;
            right++;
        }
    }

    private static void changeSwitchMan(int[] switchArr, int num) {
        for(int i=num;i<switchArr.length;i+=num){
            switchArr[i] = Math.abs(switchArr[i]-1);
        }
    }

    public static void main(String [] args) throws IOException {
        getAnswer();
    }
}
