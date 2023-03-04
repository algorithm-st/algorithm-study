package bear.week2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 숫자야구 {

    static List<BaseBall> baseBallList = new ArrayList<>();

    static int N;

    public static void main(String[] args) throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(in.readLine());

        for(int i = 0; i < N; i++) {

            String input = in.readLine();
            StringTokenizer st = new StringTokenizer(input, " ");
            int expectNumber = Integer.parseInt(st.nextToken());
            int strike = Integer.parseInt(st.nextToken());
            int ball = Integer.parseInt(st.nextToken());

            BaseBall baseBall = new BaseBall(expectNumber, strike, ball);
            baseBallList.add(baseBall);

//           예상숫자를 넣고
        }
        System.out.println(expectNumber());

    }

    static int expectNumber() {
        int answer = 0;

        for(int i = 123; i < 988;i ++) {
            if(!isSameNumber(i)) continue;

            int result = 0;

            for(int j = 0; j < N; j++) {
                int strikeCnt = 0;
                int ballCnt = 0;

                BaseBall baseBall = baseBallList.get(j);
                String expectNumber = Integer.toString(baseBall.expectNumber);
                String iToString = Integer.toString(i);

                for(int k = 0; k < 3; k++) {
                    if(expectNumber.charAt(k) == iToString.charAt(k)) {
                        strikeCnt ++;
                    }
                }

                for(int t = 0; t < 3; t++) {
                    for(int q = 0; q < 3; q++) {
                        if(iToString.charAt(t) == expectNumber.charAt(q)) {
                            if(t != q) {
                                ballCnt ++;
                            }
                        }
                    }
                }

                if(baseBall.strike == strikeCnt && baseBall.ball == ballCnt) {
                    result ++;
                }
            }
            if(result == N) {
                answer ++;
            }
        }
        return answer;
    }

    static boolean isSameNumber(int number) {
        String numberToString = Integer.toString(number);

        if(numberToString.charAt(0) == numberToString.charAt(1)) {
            return false;
        }
        if(numberToString.charAt(1) == numberToString.charAt(2)) {
            return false;
        }
        if(numberToString.charAt(0) == numberToString.charAt(2)) {
            return false;
        }

        if(numberToString.charAt(0) == '0' || numberToString.charAt(1) == '0' || numberToString.charAt(2) == '0') {
            return false;
        }
        return true;
    }

    static class BaseBall {
        int expectNumber;
        int strike;
        int ball;

        public BaseBall(int expectNumber, int strike, int ball) {
            this.expectNumber = expectNumber;
            this.strike = strike;
            this.ball = ball;
        }
    }
}

