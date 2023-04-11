package june.프로그래머스.미로탈출명령어;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution {

    static int[][] miro;
    static int K;
    static int X;
    static int Y;
    static int R;
    static int C;
    static int N;
    static int M;
    static String[] arr;
    static String[] s = new String[]{"l", "r", "u", "d"};
    static List<String> answer = new ArrayList<>();

    public static void main(String[] args) {
        String solution = solution(3, 4, 2, 3, 3, 1, 5);
        System.out.println(solution);
    }

    public static String solution(int n, int m, int x, int y, int r, int c, int k) {
        // 초기화
        miro = new int[n+1][m+1];
        N = n;
        M = m;
        K = k;
        X = x;
        Y = y;
        R = r;
        C = c;
        arr = new String[k];

        // 1) dfs 만들기
        dfs(0);
        if (answer.isEmpty()) {
            return "impossible";
        }
        answer.sort(String::compareTo);
        return answer.get(0);
    }

    private static void dfs(int index) {
        if (index == K) {
            // 종료
            cal();
        } else {
            for (int i = 0; i < 4; i++) {
                arr[index] = s[i];
                dfs(index + 1);
            }
        }

    }

    private static void cal() {
        int a = X;
        int b = Y;

        for (int i = 0; i < K; i++) {
            switch (arr[i]) {
                case "l":
                    b -= 1;
                    break;
                case "r":
                    b += 1;
                    break;
                case "u":
                    a -= 1;
                    break;
                case "d":
                    a += 1;
                    break;
            }
            if (a <= 0 || b <= 0 || a > N || b > M) {
                return;
            }
        }
        if (a == R && b == C) {
            answer.add(Arrays.stream(arr).collect(Collectors.joining()));
        }
    }
    // 2) 정답 계산 함수 만들기
}
