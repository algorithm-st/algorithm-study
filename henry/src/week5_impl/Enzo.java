package week5_impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 제한 시간 : 2초
 * N x N 크기 공간
 * 물고기 M마리
 * 아기 상어 1마리
 * 아기 상어 이동
 * - 자기 크기 이하만 지나갈 수 있다.
 * - 자신의 크기보다 작은 물고기만 먹을 수 있다.
 *
 *
 *
 */
public class Enzo {
    static int[] dx = {-1, 0,0, 1};
    static int[] dy = {0, -1, 1, 0};

    static int n;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.valueOf(bufferedReader.readLine());
        map = new int[n][n];
        Shark babyShark = null;

        for (int i = 0; i < n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < n; j++) {
                int inputNumber = Integer.valueOf(stringTokenizer.nextToken());
                if (inputNumber == 9) {
                    babyShark = new Shark(i, j);
                    continue;
                }
                map[i][j] = inputNumber;
            }
        }


        int time = 0;
        Point nextPoint = babyShark.findNextPoint();

        while (nextPoint != null) {
            time+= nextPoint.time;
            babyShark.moveAndEat(nextPoint);
//            printStatus(babyShark, time);
            nextPoint = babyShark.findNextPoint();
        }

        System.out.println(time);
    }

    static public void printStatus(Shark babyShark, int time){
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("shark size : " + babyShark.size + ", time : " + time);
    }


    static class Shark{
        private int x;
        private int y;
        private int size;
        private int eatCount;

        public Shark(int x, int y) {
            this.x = x;
            this.y = y;
            this.size = 2; // 처음에 크기 2
            this.eatCount = 0;
        }

        public Point findNextPoint(){
            Point nextPoint = null;

            PriorityQueue<Point> priorityQueue = new PriorityQueue<>((a, b)->{
                if (a.time == b.time) {
                    if (a.x == b.x) {
                        return a.y - b.y;
                    }
                    return a.x - b.x;
                }
                return a.time - b.time;
            });

            boolean[][] ch = new boolean[n][n];

            priorityQueue.offer(new Point(this.x, this.y, 0));
            ch[this.x][this.y] = true;
            while (!priorityQueue.isEmpty()) {
                Point point = priorityQueue.poll();

                if (map[point.x][point.y] != 0 && map[point.x][point.y] < this.size) {
                    nextPoint = point;
                    break;
                }

                for (int i = 0; i < 4; i++) {
                    int nextX = point.x + dx[i];
                    int nextY = point.y + dy[i];

                    if (nextX < 0 || nextX >= n || nextY < 0 || nextY >= n || ch[nextX][nextY]) {
                        continue;
                    }

                    if (map[nextX][nextY] > this.size) {
                        continue;
                    }

                    ch[nextX][nextY] = true;
                    priorityQueue.offer(new Point(nextX, nextY, point.time+1));
                }
            }
            return nextPoint;
        }

        public void moveAndEat(Point nextPoint){
            if (nextPoint == null) {
                return;
            }

            this.x = nextPoint.x;
            this.y = nextPoint.y;

            eatCount++;
            if (this.eatCount == this.size) {
                this.sizeUp();
            }

            map[nextPoint.x][nextPoint.y] = 0;
        }

        private void sizeUp(){
            this.size++;
            this.eatCount=0;
        }
    }

    static class Point{
        int x;
        int y;
        int time;

        public Point(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }
}
