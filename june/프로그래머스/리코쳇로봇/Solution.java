package june.프로그래머스.리코쳇로봇;

import java.util.*;

public class Solution {
    int[][] arr;
    int[] dx = {1,-1,0,0};
    int[] dy = {0,0,1,-1};
    public int solution(String[] board) {
        int answer = 0;
        int aLen = board.length;
        int bLen = board[0].length();
        arr = new int[aLen][bLen];
        int aStart = 0;
        int bStart = 0;

        for(int i = 0; i < aLen; i++){
            for(int j = 0; j < bLen; j++){
                if(board[i].charAt(j) == '.'){
                    arr[i][j] = 0;
                }else if(board[i].charAt(j) == 'D'){
                    arr[i][j] = 1;
                }else if(board[i].charAt(j) == 'R'){
                    arr[i][j] = 2;
                    aStart = i;
                    bStart = j;
                }else if(board[i].charAt(j) == 'G'){
                    arr[i][j] = 3;
                }
            }
        }

        LinkedList<int[]> q = new LinkedList();
        boolean[][] visited = new boolean[aLen][bLen];
        q.add(new int[]{aStart, bStart});
        visited[aStart][bStart] = true;

        while(!q.isEmpty()){
            int len = q.size();
            answer++;
            for(int i = 0; i < len; i++){
                int[] now = q.poll();
                for(int j = 0; j < 4; j++){
                    int nx = now[0] + dx[j];
                    int ny = now[1] + dy[j];

                    if(nx < 0 || ny<0||nx>=aLen||ny>=bLen) continue;

                    if(arr[nx][ny] == 1) continue;

                    // 방향대로 직진
                    while(nx>=0 && ny>=0 && nx<aLen && ny <bLen && arr[nx][ny] != 1){
                        nx += dx[j];
                        ny += dy[j];
                    }

                    nx -= dx[j];
                    ny -= dy[j];

                    if(arr[nx][ny] == 3) return answer;
                    if(visited[nx][ny]) continue;

                    visited[nx][ny] = true;
                    // System.out.println("nx , ny : " + nx + " , " + ny);
                    // print(visited);
                    q.add(new int[]{nx, ny});
                }
            }
        }

        return -1;
    }

    public void print(boolean[][] visited){
        for (int i = 0; i < visited.length; i++) {
            for (int j = 0; j < visited[0].length; j++) {
                if (visited[i][j]) {
                    System.out.print(1 + " ");
                } else {
                    System.out.print(0 + " ");
                }
            }
            System.out.println();
        }
    }
}
