package week2_complete_search;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class HenryBitMasking {
    private static int n = 0;
    private static int m = 0;
    private static int[][] inputArray;
    private static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String s = bufferedReader.readLine();
        StringTokenizer stringTokenizer = new StringTokenizer(s);

        n = Integer.valueOf(stringTokenizer.nextToken());
        m = Integer.valueOf(stringTokenizer.nextToken());
        answer = Integer.MIN_VALUE;
        inputArray = new int[n][m];
        for (int i = 0; i < n; i++) {
            String rowString = bufferedReader.readLine();
            for (int j = 0; j < m; j++) {
                inputArray[i][j] = Integer.valueOf(rowString.charAt(j)+"");
            }
        }

        getAnswerWithBitMasking();
        System.out.println(answer);
    }

    static void getAnswerWithBitMasking() {
        for (int i = 0; i < ((1 << (n*m))); i++) {
            int sum = getSumRow(i) + getSumCol(i);
            answer = Math.max(answer, sum);
        }
    }

    static int getBit(int r, int c, int bitList) {
        return (bitList >> (r * m + c)) & 1;
    }

    static int getSumRow(int bitList){
        int result = 0;
        for (int i = 0; i < n; i++) {
            int subSum = 0;

            for (int j = 0; j < m; j++) {
                if(getBit(i, j, bitList) == 1){
                    subSum = subSum * 10 + inputArray[i][j];
                }else{
                    result += subSum;
                    subSum = 0;
                }
            }
            result += subSum;
        }
        return result;
    }

    static int getSumCol(int bitList){
        int result = 0;
        for (int j = 0; j < m; j++) {
            int subSum = 0;

            for (int i = 0; i < n; i++) {
                if(getBit(i, j, bitList) == 0){
                    subSum = subSum * 10 + inputArray[i][j];
                }else{
                    result += subSum;
                    subSum = 0;
                }
            }
            result += subSum;
        }
        return result;
    }
}
