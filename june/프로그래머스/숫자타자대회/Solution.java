package june.프로그래머스.숫자타자대회;

import java.util.*;


public class Solution {
	int[][] move = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	int[][] diagonal = {{-1, -1}, {1, -1}, {-1, 1}, {1, 1}}; 
	char[][] board = {{'1', '2', '3'}, {'4', '5', '6'}, {'7', '8', '9'}, {'*', '0', '#'}};
	int[][][] dp;
	int[][] w = new int[10][10];
	int[] number;
	int INF = 10000000;
	int n;

	public int solution(String numbers) {
		n = numbers.length();
		number = new int[n];
		dp = new int[n][10][10];

		for(int i=0;i<n;i++) {
			number[i] = numbers.charAt(i) - '0';
            for (int j = 0; j < 10; j++) {
                Arrays.fill(dp[i][j], INF);
            }
		}

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                w[i][j] = 100;
            }
        }

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] != '*' && board[i][j] != '#') {
                    BFS(i, j, board[i][j] - '0');
                }
            }
        }

		return getMinTime(0, 4, 6);
	}

	int getMinTime(int idx, int left, int right) {
        if (idx == n) {
            return 0;
        }

		if(dp[idx][left][right] == INF) {
			int first = INF;
			int second = INF;
            if (right != number[idx]) // 눌러야 할 숫자에 오른쪽 손가락이 위치해있다면 왼쪽 손가락으로 누를 수 없음
            {
                first = w[left][number[idx]] + getMinTime(idx + 1, number[idx], right);
            }
            if (left != number[idx]) // 눌러야 할 숫자에 왼쪽 손가락이 위치해있다면 오른쪽 손가락으로 누를 수 없음
            {
                second = w[right][number[idx]] + getMinTime(idx + 1, left, number[idx]);
            }
			
			dp[idx][left][right] = Math.min(first, second);
		}

		return dp[idx][left][right];
	}

	void BFS(int startR, int startC, int num) {
		Queue<int[]> q = new LinkedList<>();

		q.offer(new int[] {startR, startC, num, 0});
		w[num][num] = 1;

		while(!q.isEmpty()) {
			int[] now = q.poll();

			for(int i=0;i<4;i++) { // 상하좌우 이동
				int nextR = now[0] + move[i][0];
				int nextC = now[1] + move[i][1];
    
				if(check(nextR, nextC) && board[nextR][nextC] != '*' && board[nextR][nextC] != '#'
						&& w[num][board[nextR][nextC] - '0'] > now[3] + 2) {
					w[num][board[nextR][nextC] - '0'] = now[3] + 2;
					w[board[nextR][nextC] - '0'][num] = now[3] + 2;
					q.offer(new int[] {nextR, nextC, board[nextR][nextC] - '0', now[3] + 2});
				}
			}

			for(int i=0;i<4;i++) { // 대각선 이동
				int nextR = now[0] + diagonal[i][0];
				int nextC = now[1] + diagonal[i][1];

				if(check(nextR, nextC) && board[nextR][nextC] != '*' && board[nextR][nextC] != '#'
						&& w[num][board[nextR][nextC] - '0'] > now[3] + 3) {
					w[num][board[nextR][nextC] - '0'] = now[3] + 3;
					w[board[nextR][nextC] - '0'][num] = now[3] + 3;
					q.offer(new int[] {nextR, nextC, board[nextR][nextC] - '0', now[3] + 3});
				}
			}
		}
	}
    
    boolean check(int r, int c) {
        if (r < 0 || r >= 4 || c < 0 || c >= 3) {
            return false;
        }
		return true;
	}
    
}