import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;


public class Main {
    public static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int gameCount = Integer.parseInt(br.readLine());
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i<gameCount;i++){
            String curGameStr = br.readLine();
            int targetNum = Integer.parseInt(br.readLine());
            sb.append(getGameAnswer(curGameStr,targetNum)+"\n");
        }
        System.out.println(sb.toString());
    }

    private static String getGameAnswer(String curGameStr, int targetNum) {
        int minLength = Integer.MAX_VALUE;
        int maxLength = Integer.MIN_VALUE;
        Deque<Integer>[] alphaQueue = new Deque[26];

        for(int i=0;i<26;i++){
            alphaQueue[i] = new ArrayDeque<>();
        }

        for(int i=0;i<curGameStr.length();i++){
            int curCharIndex = curGameStr.charAt(i) - 'a';
            alphaQueue[curCharIndex].add(i);
            if(alphaQueue[curCharIndex].size() == targetNum){
                int firstNum = alphaQueue[curCharIndex].peekFirst();
                int lastNum = alphaQueue[curCharIndex].peekLast();
                alphaQueue[curCharIndex].pollFirst();
                int curLength = lastNum - firstNum+1;
                minLength = Math.min(minLength,curLength);
                maxLength = Math.max(maxLength,curLength);
            }
        }
        if(minLength == Integer.MAX_VALUE && maxLength == Integer.MIN_VALUE) return "-1";
        String answer  = minLength + " " +maxLength;
        return answer;

    }
    public static void main(String args[]) throws Exception{
        solution();
    }
}
