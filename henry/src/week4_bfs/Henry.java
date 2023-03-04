package week4_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

/**
 * 왜 이 문제를 선택했나?
 * - bfs로 탐색을 할 때 우선순위 큐를 활용해 볼 수 있는 문제라 선택했습니다.
 *
 * 문제 푼 데 걸린 시간은?
 * - 57분
 *
 * 시간 복잡도는 ? (잘 모르겠다면 예상)
 * - n^2(시험관 칸의 개수) * logn(우선순위 큐에 항목을 넣을 때 정렬)
 *
 * 문제 설명
 * - 바이러스가 존재하는 칸이 있다.
 * - 바이러스는 1초마다 상, 하, 좌, 우의 방향으로 증식해 나간다.
 * - 단, 매 초마다 번호가 낮은 종류의 바이러스부터 먼저 증식한다. 또한 증식 과정에서 특정한 칸에 이미 어떠한 바이러스가 존재한다면, 그 곳에는 다른 바이러스가 들어갈 수 없다.
 * - S초가 지난 후에 (X,Y)에 존재하는 바이러스의 종류를 출력하는 프로그램을 작성하시오. 만약 S초가 지난 후에 해당 위치에 바이러스가 존재하지 않는다면, 0을 출력한다.
 *
 * 문제 풀이 설명
 * - 각 점의 좌표, 증식 시간, 바이러스의 종류를 가지고 있는 Point 클래스를 선언
 * - 증식 시간이 빠를 수록, 증식 시간이 같다면 낮은 바이러스 종류의 Point를 우선으로 반환하는 우선순위큐를 사용해서 바이러스를 증식 시킨다.
 */
public class Henry {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        // init
        String[] inputLine = bufferedReader.readLine().split(" ");
        int n = Integer.valueOf(inputLine[0]);
        int k = Integer.valueOf(inputLine[1]);
        int[][] map = new int[n+1][n+1];
        for (int i = 1; i <= n; i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 1; j <= n; j++) {
                map[i][j] = Integer.valueOf(stringTokenizer.nextToken());
            }
        }
        inputLine = bufferedReader.readLine().split(" ");
        int s = Integer.valueOf(inputLine[0]);
        int finalCheckX = Integer.valueOf(inputLine[1]);
        int finalCheckY = Integer.valueOf(inputLine[2]);

        int answer = bfs(n, map, s, finalCheckX, finalCheckY);
        System.out.println(answer);
    }


    static int bfs(int n, int[][] map, int s, int finalCheckX, int finalCheckY) {

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        PriorityQueue<Point> priorityQueue = new PriorityQueue<>((a, b) -> {
            if (a.time == b.time) {
                return a.virusNumber - b.virusNumber;
            }else{
                return a.time - b.time;
            }
        });
        initQueue(priorityQueue, map, n);

        while (!priorityQueue.isEmpty()) {

            Point point = priorityQueue.poll();

            if (s == point.time) {
                break; // 여기서 return 하면 안됩니다..! s값이 값을 다 채우는 시간보다 클 수 있기 때문이에요.
            }

            for (int i = 0; i < 4; i++) {
                int nextX = point.x + dx[i];
                int nextY = point.y + dy[i];
                if (nextX <= 0 || nextX > n || nextY <= 0 || nextY > n) {
                    continue;
                }
                if (map[nextX][nextY]!=0) {
                    continue;
                }

                map[nextX][nextY] = point.virusNumber;
                priorityQueue.offer(new Point(nextX, nextY, point.time + 1, point.virusNumber));
            }
        }

        return map[finalCheckX][finalCheckY];
    }

    static void initQueue(Queue<Point> queue, int[][] map, int n) {
        queue.clear();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (map[i][j] != 0) {
                    queue.offer(new Point(i, j, 0, map[i][j]));
                }
            }
        }
    }

    private static class Point{
        int x;
        int y;
        int time;
        int virusNumber;

        public Point(int x, int y, int time, int virusNumber) {
            this.x = x;
            this.y = y;
            this.time = time;
            this.virusNumber = virusNumber;
        }
    }
}
