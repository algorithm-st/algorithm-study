import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Han {

    public static void getAnswer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        //  <0 1이 이김  0 비김  >0 2가 이김
        int maxNum = 48*60;
        int firstCount = 0;
        int firstTime = 0;
        int secondCount = 0;
        int secondTime = 0;
        int beforeSecond = 0;
        for(int i=0;i<N;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int tempTeamNum = Integer.parseInt(st.nextToken());
            int tempSecond = getSecond(st.nextToken());
            if(firstCount>secondCount){
                firstTime += tempSecond-beforeSecond;
            }
            else if(firstCount<secondCount){
                secondTime += tempSecond-beforeSecond;
            }

            if (tempTeamNum == 1) firstCount++;
            else secondCount++;

            beforeSecond = tempSecond;
        }

        if(firstCount > secondCount){
            firstTime += maxNum-beforeSecond;
        }
        else if(secondCount > firstCount){
            secondTime += maxNum-beforeSecond;
        }

        System.out.println(convertTimeFormat(firstTime));
        System.out.println(convertTimeFormat(secondTime));

    }

    private static String convertTimeFormat(int time) {
        int min = time/60;
        int second = time%60;
        return String.format("%02d:%02d",min,second);
    }

    private static int getSecond(String nextToken) {
        String[] split = nextToken.split(":");
        return Integer.parseInt(split[0])*60 + Integer.parseInt(split[1]);
    }

    public static void main(String []args) throws IOException {
        getAnswer();
    }
}
