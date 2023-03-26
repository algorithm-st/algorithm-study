package week10;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Bear {
    static int m, n;
    static int[][] board;
    static int answer = -1;
    static int[] dx={-1, 0, 1, 0}, dy={0, 1, 0, -1};

    public static void main(String[] args){
        Scanner kb = new Scanner(System.in);
        m = kb.nextInt(); n = kb.nextInt(); board = new int[n+1][m+1];
        for(int i=1; i<=n; i++)
            for(int j=1; j<=m; j++) board[i][j]= kb.nextInt();
        BFS();
        System.out.println(answer);
    }


    static void BFS(){
        int L = 0;
        Queue<Point> Q = new LinkedList<>();
        for(int i=1; i<=n; i++)
            for(int j=1; j<=m; j++)
                if(board[i][j]==1) Q.offer(new Point(i, j));
        while(!Q.isEmpty()){
            if(check()){
                answer=L;
                return;
            }else{
                int size = Q.size();
                for(int i=0; i<size; i++){
                    Point tmp = Q.poll();
                    for(int j=0; j<4; j++){
                        int nx = tmp.x+dx[j];
                        int ny = tmp.y + dy[j];
                        if(nx<1 || nx>n || ny<1 || ny>m) continue;
                        if(board[nx][ny]==0){
                            board[nx][ny] =1;
                            Q.offer(new Point(nx, ny));
                        }
                    }
                }
            }
            L++;
        }
    }

    static boolean check(){
        boolean flag = true;
        for(int i=1; i<=n; i++)
            for(int j=1; j<=m; j++)
                if(board[i][j]==0) return false;
        return flag;
    }

    static class Point{
        public int x, y;
        Point(int x, int y){
            this.x=x;
            this.y=y;
        }
    }
}
