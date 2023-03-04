package sherlock.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Sherlock {

    static int n, m, r, c, d;
    static int[][] board;
    static int count = 1; //시작 지점은 항상 청소한다.
    static int[] dx = {-1, 0, 1, 0}; //북, 동, 남, 서 순서대로
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(r, c, d);
        System.out.println(count);
    }

    public static void dfs(int x, int y, int dir) {
        board[x][y] = 2; //청소 했다는 의미

        for(int i = 0; i < 4; i++) {
            dir -= 1; //왼쪽 방향으로 돌면서 탐색
            if(dir == -1) dir = 3;

            int next_x = x + dx[dir];
            int next_y = y + dy[dir];
            if(next_x >= 0 && next_y >= 0 && next_x < n && next_y < m) {
                if(board[next_x][next_y] == 0) { //벽도 아니고 이미 청소한 곳도 아니라면 청소하러 이동한다
                    count++;
                    dfs(next_x, next_y, dir);
                    //일반적인 dfs는 가다가 길이 막히면 다시 되돌아와서 해당 위치부터 계산하지만, 이 문제는 후진할 때만 이전 길을 되돌가 가며 확인할 수 있으므로 return을 해서 다시 되돌아 와도 더 이상 움직이면 안된다.
                    return;
                }
            }
        }

        //반목문을 빠져 나왔단는 것은 주변에 더 이상 청소할 공간이 없다는 의미
        int d = (dir + 2) % 4; //반대 방향으로 후진하기 위함.
        int back_x = x + dx[d];//후진
        int back_y = y + dy[d];//후진
        if(back_x >= 0 && back_y >= 0 && back_x < n && back_y < m && board[back_x][back_y] != 1) {
            dfs(back_x, back_y, dir); //후진할 때 방향을 유지해야 한다.
        }
    }
}
