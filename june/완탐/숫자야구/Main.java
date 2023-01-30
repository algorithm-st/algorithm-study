package june.완탐.숫자야구;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
//4
//123 1 1
//356 1 0
//327 2 0
//489 0 1
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(bufferedReader.readLine());
        ArrayList<Num> nums = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String[] s = bufferedReader.readLine().split(" ");
            Num num = new Num(s[0], Integer.parseInt(s[1]), Integer.parseInt(s[2]));
            nums.add(num);
        }
        int count = 0;
        for (int i = 123; i <= 987; i++) {
            int pass = 0;
            for (Num num : nums) {
                if (num.pass(String.valueOf(i))) {
                    pass++;
                }
            }
            if (pass == nums.size()) {
                count++;
            }
        }
        System.out.println(count);
    }

}

class Num {

    String num;
    int strike;
    int ball;

    public Num(String num, int strike, int ball) {
        this.num = num;
        this.strike = strike;
        this.ball = ball;
    }

    public boolean pass(String num2) {
        if (!valid(num2)) {
            return false;
        }
        int st = 0;
        int ba = 0;
        for (int i = 0; i < num.length(); i++) {
            for (int j = 0; j < num2.length(); j++) {
                if (num.charAt(i) == num2.charAt(j)) {
                    ba++;
                    if (i == j) {
                        st++;
                    }
                }
            }
        }
        ba -= st;
        if (st == strike && ba == ball) {
            return true;
        }
        return false;
    }

    public boolean valid(String num) {
        for (int i = 0; i < num.length(); i++) {
            if (num.charAt(i) == '0') {
                return false;
            }
            if (num.charAt(i) == num.charAt((i + 1) % 3) || num.charAt(i) == num.charAt((i + 2) % 3)) {
                return false;
            }
        }
        return true;
    }
}

