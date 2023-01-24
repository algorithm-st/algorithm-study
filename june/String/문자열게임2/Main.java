package june.String.문자열게임2;

import java.util.Scanner;

public class Main {
    // 중간부터 map 써서 카운트해나감
    // K개 포함하는 순간
    public static void main(String[] args) {
//2
//superaquatornado
//2
//abcdefghijklmnopqrstuvwxyz
//5
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            String str = sc.next();
            int K = sc.nextInt();
            Game game = new Game(str, K);
            game.print();
        }
    }

    static class Game {

        private String str;
        private int K;
        private int min;
        private int max;

        public Game(String str, int K) {
            this.str = str;
            this.K = K;
            min = Integer.MAX_VALUE;
            max = 0;
            count();
        }

        public void print() {
            if (max == 0 || min == Integer.MAX_VALUE) {
                System.out.println(-1);
                return;
            }
            System.out.println(min + " " + max);
        }

        private void count() {
            for (int i = 0; i < str.length(); i++) {
                char now = str.charAt(i);
                int count = 0;
                for (int j = i; j < str.length(); j++) {
                    if (now == str.charAt(j)) {
                        count++;
                        if (count == K) {
                            min = Math.min(min, j - i + 1);
                            max = Math.max(max, j - i + 1);
                            break;
                        }
                    }
                }
            }
        }

    }

}
