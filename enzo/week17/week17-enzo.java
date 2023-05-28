
import java.util.HashMap;
import java.util.Map;

public class Enzo {
    public int solution(String numbers) {

        Map<String, int[]> phone = initPhone();
        int [] firstHand = phone.get("4");
        int [] secondHand = phone.get("6");
        // dp[0] current, dp[1] next
        // dp[0...1][firstHand][secondHand]
        int [][][] dp = new int[2][10][10];
        for(int i=0;i<2;i++){
            for(int j=0;j<10;j++){
                for(int k=0;k<10;k++){
                    dp[i][j][k] = Integer.MAX_VALUE/2;
                }
            }
        }
        dp[0][4][6] = 0;
        String[] phoneNumList = numbers.split("");
        for(String phoneNum : phoneNumList){
            int curNum = Integer.parseInt(phoneNum);
            int[] curPhoneNum = phone.get(phoneNum);
            for(int i=0;i<10;i++){
                for(int j=0;j<10;j++){
                    if(i == j) continue;
                    dp[1][curNum][j] = Math.min(dp[1][curNum][j],dp[0][i][j] + getLength(curPhoneNum,phone.get(""+i)));
                    dp[1][i][curNum] = Math.min(dp[1][i][curNum],dp[0][i][j] + getLength(curPhoneNum,phone.get(""+j)));
                }
            }
            int [][] temp = dp[0];
            dp[0] = dp[1];
            dp[1] = temp;
            for(int i=0;i<10;i++){
                for(int j=0;j<10;j++){
                    dp[1][i][j] = Integer.MAX_VALUE/2;
                }
            }
        }
        int answer =  Integer.MAX_VALUE/2;
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                answer = Math.min(answer,dp[0][i][j]);
            }
        }
        return answer;
    }

    public int getLength(int[] pos1, int[] pos2){
        int len = 0;
        int yGap = Math.abs(pos1[0] - pos2[0]);
        int xGap = Math.abs(pos1[1] - pos2[1]);

        if(yGap ==0 && xGap == 0) return 1;

        if(yGap > 0 && xGap > 0){
            int minGap = Math.min(yGap, xGap);
            len += minGap * 3;
            yGap -= minGap;
            xGap -= minGap;
        }
        if(yGap > 0 || xGap > 0){
            int restMaxGap = Math.max(yGap, xGap);
            len += restMaxGap *2;
        }
        return len;
    }
    public Map<String, int[]> initPhone(){
        Map<String, int[]> map = new HashMap<>();
        map.put("1",new int[]{0,0});
        map.put("2",new int[]{0,1});
        map.put("3",new int[]{0,2});

        map.put("4",new int[]{1,0});
        map.put("5",new int[]{1,1});
        map.put("6",new int[]{1,2});

        map.put("7",new int[]{2,0});
        map.put("8",new int[]{2,1});
        map.put("9",new int[]{2,2});

        map.put("*",new int[]{3,0});
        map.put("0",new int[]{3,1});
        map.put("#",new int[]{3,2});
        return map;
    }
    public static void main(String[] args){
        Enzo e = new Enzo();
        e.solution("4444");
    }
}
