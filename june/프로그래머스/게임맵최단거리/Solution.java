package june.프로그래머스.게임맵최단거리;

import java.util.LinkedList;

public class Solution {
    public int solution(int[][] maps) {
        int answer = 1;
        int n = maps.length;
        int m = maps[0].length;
        int[] dx = {1,-1,0,0};
        int[] dy = {0,0,1,-1};
        if (n == 1 && m == 1) {
            return 1;
        }
        
        LinkedList<int[]> q = new LinkedList();
        boolean[][] visited = new boolean[n][m];
        q.add(new int[]{0,0});
        visited[0][0] = true;
        while(!q.isEmpty()){
            int len = q.size();
            for(int i = 0; i < len; i++){
                int[] now = q.poll();
                for(int j = 0; j < 4; j++){
                    int nx = now[0] + dx[j];
                    int ny = now[1] + dy[j];

                    if (nx == n - 1 && ny == m - 1) {
                        return answer + 1;
                    }

                    if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                        continue;
                    }
                    if (visited[nx][ny]) {
                        continue;
                    }
                    if (maps[nx][ny] == 0) {
                        continue;
                    }
                    
                    visited[nx][ny] = true;
                    q.add(new int[]{nx,ny});
                }
                // print(n,m,visited);
                    
            }
            answer++;
            // System.out.println("count : " + answer);
        }
        
        
        
        return -1;
    }
    public void print(int n, int m, boolean[][] visited){
        for(int i = 0; i<n; i++){
            for(int j =0; j<m; j++){
                if(visited[i][j]){
                    System.out.print(1 + " ");    
                }else{
                    System.out.print(0 + " ");    
                }
            }
            System.out.println();    
        }
    }
}