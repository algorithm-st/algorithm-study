import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Han {

    public static void getAnswer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuffer sb = new StringBuffer();
        for(int i=0;i<T;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            String[] array = new String[N];
            for(int j=0;j<N;j++){
                array[j] = br.readLine();
            }
            sb.append(getMaxStudent(N,M,array)).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static int getMaxStudent(int n, int m, String[] array) {
        int length = 1<<m;
        Map<Integer,int[]> map = getOneCountMap(m);
        int [][] dp = new int[n][length];
        int [] arrayBit = getArrayBit(array);
        for(int i=0;i<length;i++){
            if(isPossibleLR(map,i) && isTablePossible(arrayBit[0],i)){
                dp[0][i] = map.get(i)[0];
            }
        }

        for(int i=1;i<n;i++){
            for(int j=0;j<length;j++){
                if(!isPossibleLR(map,j) || !isTablePossible(arrayBit[i-1],j)) continue;
                for(int k=0;k<length;k++){
                    if(isPossibleLR(map,k|j) && isTablePossible(arrayBit[i],k)){
                        dp[i][k] = Math.max(dp[i-1][j]+map.get(k)[0],dp[i][k]);
                    }
                }
            }
        }
        int answer = Integer.MIN_VALUE;
        for(int i=0;i<length;i++){
            answer = Math.max(dp[n-1][i],answer);
        }
        return answer;
    }

    // x == 1 , . ==0;
    private static int[] getArrayBit(String[] array) {
        int [] bit = new int[array.length];
        for(int i=0;i<array.length;i++){
            int num = 0;
            for(int j=0;j<array[i].length();j++){
                if(array[i].charAt(j) == 'x'){
                    num += 1<<j;
                }
            }
            bit[i] = num;
        }
        return bit;
    }

    private static boolean isPossibleLR(Map<Integer,int[]> map, int j) {
        if(map.get(j)[1] == 0) return false;
        return true;
    }


    private static boolean isTablePossible(int stepBit, int target) {
        if((stepBit & target) >0) return false;
        return true;
    }

    private static Map<Integer, int[]> getOneCountMap(int m) {
        Map<Integer,int[]> map = new HashMap<>();
        int length = 1<<m;
        for(int i=0;i<length;i++){
            int count = 0;
            int check = 1;
            for(int j=0;(1<<j)<length;j++){
                int num = 1<<j;
                if((num&i) == num){
                    int next = 1<<(j+1);
                    if((next&i) == next){
                        check = 0;
                    }
                    count++;
                }

            }
            map.put(i,new int[]{count,check});
        }
        return map;
    }


    public static void main(String []args) throws IOException {
        getAnswer();
    }
}
