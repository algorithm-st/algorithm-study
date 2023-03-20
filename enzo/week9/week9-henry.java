import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Henry {

    public static void getAnswer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        long [] array = new long[N];
        for(int i=0;i<N;i++){
            array[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(array);
        long minCount = 1;
        long maxCount = array[array.length-1]+1;
        while(minCount<maxCount){
            long midNum = (minCount+maxCount)/2;
            if(possibleNum(array,midNum,C)){
                minCount = midNum+1;
            }
            else{
                maxCount = midNum;
            }
        }
        System.out.println(minCount-1);
    }

    private static boolean possibleNum(long[] array, long midNum, int count) {
        long start = array[0];
        int curCount = 1;
        for(int i=1; i<array.length;i++){
            if(array[i]-start>=midNum){
                start = array[i];
                curCount++;
            }
        }
        if(curCount>=count) return true;
        return false;
    }

    public static void main(String []args) throws IOException {
        getAnswer();
    }
}
