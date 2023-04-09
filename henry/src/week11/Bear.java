package week11;

import java.util.*;

/**
 * 문제 설명
 * - N은 짝수이다.
 *
 */
public class Bear {
    static int answer = Integer.MAX_VALUE;
    static int n;
    static int[][] score;
    static boolean[] ch;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        score = new int[n+1][n+1];
        ch = new boolean[n+1];

        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                score[i][j] = scanner.nextInt();
            }
        }

        dfs(0, 0, 1);
        System.out.println(answer);
    }

    static void dfs(int team1Cnt, int team2Cnt, int idx){
        if (team1Cnt > (n / 2) || team2Cnt > (n / 2)) {
            return;
        }

        if (idx == n + 1) {
            int team1Score = 0;
            int team2Score = 0;

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= n; j++) {
                    if (ch[i] && ch[j]) {
                        team1Score += score[i][j];
                    }else if(!ch[i] && !ch[j]){
                        team2Score += score[i][j];
                    }
                }
            }
            int diff = Math.abs(team1Score - team2Score);
            answer = Math.min(answer, diff);
            return;
        }

        ch[idx] = true;
        dfs(team1Cnt + 1,  team2Cnt, idx + 1);
        ch[idx] = false;
        dfs(team1Cnt, team2Cnt + 1, idx + 1);
    }
}
