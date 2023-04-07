package week11;

import java.util.*;
/**
 * 다리 만들기
 * - 한 섬과 다른 서믈 잇는 다리 하나만을 만들기로 함
 * - 다리를 가장 짧게
 * - N * N 크기의 이차원 평면상에 존재
 * - 섬 - 동서남븍
 */
public class June {

    static int n;
    static int[][] map;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        map = new int[n][n];

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                map[i][j] = scanner.nextInt();
            }
        }

        markIslandNumber();
        System.out.println(findShortestLength());
    }

    private static int findShortestLength(){
        int answer = Integer.MAX_VALUE;

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++) {

                if(map[i][j]==0) {
                    continue;
                }

                int shortestLengthFromPoint = findShortestLengthFromPoint(i, j);
                if (shortestLengthFromPoint == 0) {
                    continue;
                }
                answer = Math.min(answer, shortestLengthFromPoint);
            }
        }

        return answer;
    }

    private static int findShortestLengthFromPoint(int x, int y){
        int result = 0;
        int pointNumber = map[x][y];
        boolean[][] ch = new boolean[n][n];

        Queue<Point> queue = new LinkedList<>();
        ch[x][y] = true;
        queue.offer(new Point(x, y, 0));

        while(!queue.isEmpty()){
            Point point = queue.poll();

            if(map[point.x][point.y] !=0 && map[point.x][point.y] != pointNumber){
                result = point.dis - 1; // 1개 빼야함
                break;
            }

            for(int k=0; k<4; k++){
                int nextX = point.x + dx[k];
                int nextY = point.y + dy[k];

                if(nextX < 0 || nextX >= n || nextY <0 || nextY >=n){
                    continue;
                }

                if(ch[nextX][nextY]){
                    continue;
                }

                if(map[nextX][nextY] == pointNumber){
                    continue;
                }

                ch[nextX][nextY] = true;
                queue.offer(new Point(nextX, nextY, point.dis+1));
            }
        }

        return result;
    }


    private static void markIslandNumber(){
        int number = 1;

        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(map[i][j]==1){
                    map[i][j] = ++number;

                    Queue<Point> queue = new LinkedList<>();
                    queue.offer(new Point(i, j));


                    while (!queue.isEmpty()) {
                        Point point = queue.poll();

                        for(int k=0; k<4; k++){
                            int nextX = point.x + dx[k];
                            int nextY = point.y + dy[k];

                            if(nextX < 0 || nextX >= n || nextY <0 || nextY >=n){
                                continue;
                            }

                            if(map[nextX][nextY] !=1){
                                continue;
                            }

                            map[nextX][nextY] = number;
                            queue.offer(new Point(nextX, nextY));
                        }
                    }
                }
            }
        }
    }

    private static class Point{
        int x;
        int y;
        int dis;

        Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        Point(int x, int y, int dis){
            this.x = x;
            this.y = y;
            this.dis = dis;
        }
    }
}
