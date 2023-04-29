package june.프로그래머스.아이템줍기;

import java.util.*;

public class Solution {
    public int[] dx = {0,0,1,-1};
    public int[] dy = {1,-1,0,0};
    
    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY){
        int minX = 0;
        int minY = 0;
        int maxX = 0;
        int maxY = 0;
        List<Rec> recList = new ArrayList();
        for(int[] rec : rectangle){
            minX = Math.min(rec[0], minX);
            minY = Math.min(rec[1], minY);
            maxX = Math.max(rec[2], maxX);
            maxY = Math.max(rec[3], maxY);
            recList.add(new Rec(rec[0], rec[1], rec[2], rec[3]));
        }
        Queue<int[]> q = new LinkedList();
        boolean[][] visited = new boolean[51][51];
        for(int i =0; i<recList.size(); i++){
            if(recList.get(i).on(characterX, characterY)){
                q.add(new int[]{characterX, characterY, i});
                visited[characterX][characterY] = true;
            }
        }
    
        int count = 0;
        while(!q.isEmpty()){
            int len = q.size();
            
            // print(minX, minY, maxX, maxY,visited);
            // System.out.println();
            for(int i = 0; i < len; i++){
                int[] now = q.poll();
                for(int j = 0 ; j<4; j++){
                    boolean flag = true;
                    int nx = now[0] + dx[j];
                    int ny = now[1] + dy[j];
                    
                    if(nx == itemX && ny == itemY) {
                        return count+1;
                    }

                    if (visited[nx][ny]) {
                        continue;
                    }
                    
                    for(int k = 0; k < recList.size(); k++){
                        if (k == now[2]) {
                            continue;
                        }
                        if(recList.get(k).on(nx, ny)){
                            if(!recList.get(now[2]).contain(nx,ny)){
                                q.add(new int[]{nx, ny, k});
                                visited[nx][ny] = true;
                                flag = false;   
                            }
                        }
                    }
                    if(flag && recList.get(now[2]).on(nx,ny)){
                        q.add(new int[]{nx, ny, now[2]});
                        visited[nx][ny] = true;
                    }
                    
                }
            }
            count++;
        }
        
        return count;
    }
    
    public void print(int minX, int minY, int maxX, int maxY, boolean[][] arr){
        int a = 0;
        for(int j = maxY; j>=minY; j--){
            for(int i = minX ; i <= maxX; i++){

                if (arr[i][j]) {
                    a = 1;
                } else {
                    a = 0;
                }
                System.out.print(a + " ");
            }
            System.out.println();
        }
    }
}

class Rec{
    int x1;
    int y1;
    int x2;
    int y2;
    
    public Rec(int x1, int y1, int x2, int y2){
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }
    
    public boolean on(int a, int b){
        if(a == x1 || a == x2){
            return b <=y2 && b>=y1;
        }else if(b == y1 || b == y2){
            return a <= x2 && a >=x1;
        }
        return false;
    }
    
    public boolean contain(int a, int b){
        if(a <=x2 && a>=x1){
            return b<=y2 && b>=y1;
        }
        return false;
    }
}