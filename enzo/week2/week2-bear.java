import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {


    public static void getAnswer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String [][] arr = new String[N][3];
        for(int i =0 ; i<N;i++){
            arr[i] = Arrays.stream(br.readLine().split(" ")).toArray(String[]::new);
        }
        int answer = 0;
        for(int i=0;i<1000;i++){
            boolean flag = true;
            String curNum = String.format("%03d",i);
            if(!checkPossible(curNum)) continue;

            for(int j=0;j<N;j++){
                if(!checkBallAndStrike(curNum,arr[j][0],Integer.parseInt(arr[j][1]),Integer.parseInt(arr[j][2]))){
                    flag= false;
                    break;
                }
            }
            if(flag) answer++;
        }
        System.out.println(answer);
    }

    private static boolean checkPossible(String curNum) {
        if(curNum.contains("0")) return false;
        if(curNum.charAt(0) == curNum.charAt(1) || curNum.charAt(0) == curNum.charAt(2) || curNum.charAt(1) == curNum.charAt(2)) return false;
        return true;
    }

    private static boolean checkBallAndStrike(String curNum, String targetNum, int strike, int ball) {
        int curStrike = 0;
        int curBall = 0;
        for(int i = 0;i<3;i++){
            if(curNum.charAt(i) == targetNum.charAt(i)) {
                curStrike++;
            }
            else if(curNum.contains(String.valueOf(targetNum.charAt(i)))){
                curBall++;
            }
        }
        if(curStrike == strike && curBall == ball) return true;
        return false;
    }
}