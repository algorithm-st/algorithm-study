import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static int maxNum = Integer.MIN_VALUE;
    public static int N;
    public static String formula;

    public static void calNum(int index, int Num){
        if(index>N-1){
            maxNum = Math.max(maxNum,Num);
            return;
        }
        char operation = (index ==0) ? '+' : formula.charAt(index-1);
        if(index+2<N){
            int roundBracketCalNum = calCharacter(formula.charAt(index)-'0',formula.charAt(index+2)-'0',formula.charAt(index+1));
            calNum(index+4,calCharacter(Num,roundBracketCalNum,operation));
        }
        calNum(index+2,calCharacter(Num,formula.charAt(index)-'0',operation));
    }
    public static int calCharacter(int first, int second, char operation){
        switch(operation){
            case '+':
                return first+second;
            case '-':
                return first-second;
            case '*':
                return first*second;
        }
        return 0;
    }
    public static void getAnswer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        formula = br.readLine();
        calNum(0,0);
        System.out.println(maxNum);
    }
    public static void main(String args[]) throws Exception{
        getAnswer();
    }
}
