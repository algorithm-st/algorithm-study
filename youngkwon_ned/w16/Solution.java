package youngkwon_ned.w16;

/**
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/1832">보행자 천국</a>
 */
public class Solution {
    int MOD = 20170805;
    private int[][] maps;
    private int[][] cityMap;

    public static void main(String[] args) {
        Solution s = new Solution();
        int[][] array = {
                {0, 2, 0, 0, 0, 2},
                {0, 0, 2, 0, 1, 0},
                {1, 0, 0, 2, 2, 0}};

        int solution = s.solution(3, 6, array);
        System.out.println("solution = " + solution);
    }

    public int solution(int m, int n, int[][] cityMap) {
        maps = new int[m + 1][n + 1];
        this.cityMap = cityMap;

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == 1 && j == 1) {
                    maps[i][j] = 1;
                } else {
                    if (cityMap[i - 1][j - 1] == 0) {
                        maps[i][j] = (getTopValue(i, j) + getLeftValue(i, j)) % MOD;
                    } else {
                        maps[i][j] = 0;
                    }
                }

            }
        }
        return maps[m][n];
    }

    private int getTopValue(int rowIndex, int columnIndex) {
        for (int i = rowIndex - 1; i > 0; i--) {
            if (cityMap[i - 1][columnIndex - 1] != 2)
                return maps[i][columnIndex];
        }

        return 0;
    }

    private int getLeftValue(int rowIndex, int columnIndex) {
        for (int i = columnIndex - 1; i > 0; i--) {
            if (cityMap[rowIndex - 1][i - 1] != 2)
                return maps[rowIndex][i];
        }

        return 0;
    }
}
