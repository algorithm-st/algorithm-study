package week14;

import java.util.*;

class Henry {
    static int[][] map = new int[102][102];
    static int answer = Integer.MAX_VALUE;

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {


        for(int[] rec : rectangle){
            drawRectangle(rec);
        }

        for(int[] rec: rectangle){
            removeInnerRectangle(rec);
        }

        BFS(characterX, characterY, itemX, itemY);
        return answer/2;
    }

    // 상, 좌, 하, 우
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};

    void BFS(int characterX, int characterY, int itemX, int itemY){
        boolean[][] ch = new boolean[102][102];
        Queue<Point> queue = new LinkedList<>();
        ch[characterY*2][characterX*2] = true;
        queue.offer(new Point(characterX*2, characterY*2, 0));

        while(!queue.isEmpty()){
            Point point = queue.poll();

            if(point.x == itemX*2 && point.y == itemY*2){
                answer = Math.min(answer, point.dis);
            }

            for(int i=0; i<4; i++){
                int nX = point.x + dx[i];
                int nY = point.y + dy[i];

                if(map[nY][nX] == 0 || ch[nY][nX]){
                    continue;
                }

                ch[nY][nX] = true;
                queue.offer(new Point(nX, nY, point.dis+1));
            }
        }
    }


    void drawRectangle(int[] rec){
        for(int i=rec[1]*2 ; i<=rec[3]*2; i++){
            for(int j=rec[0]*2; j<=rec[2]*2; j++){
                map[i][j] = 1;
            }
        }
    }

    void removeInnerRectangle(int[] rec){
        for(int i=rec[1]*2+1 ; i<=rec[3]*2-1; i++){
            for(int j=rec[0]*2+1; j<=rec[2]*2-1; j++){
                map[i][j] = 0;
            }
        }
    }

    static class Point{
        int x;
        int y;
        int dis;

        Point(int x, int y, int dis){
            this.x = x;
            this.y = y;
            this.dis = dis;
        }
    }
}
