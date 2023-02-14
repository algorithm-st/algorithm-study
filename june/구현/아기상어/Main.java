package june.구현.아기상어;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;

public class Main {

    //    3
//0 0 0
//0 0 0
//0 9 0
    public static int[][] arr;
    public static int N;
    public static boolean[][] visited;

    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N][N];
        visited = new boolean[N][N];
        Shark shark = null;
        for (int i = 0; i < N; i++) {
            String[] s = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                arr[i][j] = Integer.parseInt(s[j]);
                if (arr[i][j] == 9) {
                    shark = new Shark(i, j, 2);
                }
            }
        }
        shark.bfs();
    }

    public static class Shark {

        public int x;
        public int y;
        public int size;
        public int eatCount = 0;
        public int time = 0;


        public Shark(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
        }

        public void bfs() {
            LinkedList<Position> q = new LinkedList<>();
            q.add(new Position(x, y, 0));
            arr[x][y] = 0;
            visited[x][y] = true;
            while (!q.isEmpty()) {
                int len = q.size();
                ArrayList<Position> possibleFish = new ArrayList<>();
                for (int i = 0; i < len; i++) {
                    Position current = q.pop();
                    for (int j = 0; j < 4; j++) {
                        int nx = current.x + dx[j];
                        int ny = current.y + dy[j];

                        if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                            continue;
                        }
                        if (visited[nx][ny]) {
                            continue;
                        }
                        if (arr[nx][ny] > size) {
                            continue;
                        }
                        if (arr[nx][ny] < size && arr[nx][ny] != 0) {
                            possibleFish.add(new Position(nx, ny, current.time + 1));
                            visited[nx][ny] = true;
                            continue;
                        }
                        q.add(new Position(nx, ny, current.time + 1));
                        visited[nx][ny] = true;
                    }
                }
                if (!possibleFish.isEmpty()) {
                    Collections.sort(possibleFish, (o1, o2) -> {
                        if (o1.x == o2.x) {
                            return o1.y - o2.y;
                        }
                        return o1.x - o2.x;
                    });
                    Position position = possibleFish.get(0);
                    x = position.x;
                    y = position.y;
                    arr[x][y] = 0;
                    eatCount++;
                    time += position.time;
                    visited = new boolean[N][N];
                    visited[x][y] = true;
                    if (eatCount == size) {
                        size++;
                        eatCount = 0;
                    }
                    position.time = 0;
                    q = new LinkedList<>();
                    q.add(position);
                }
            }
            System.out.println(time);
        }
    }

    public static class Position {

        int x;
        int y;
        int time;

        public Position(int x, int y, int time) {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }



}
