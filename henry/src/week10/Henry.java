package week10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 문제 설명
 * - 성을 향해 몰려오는 적을 잡는 턴 방식의 게임
 * - N * M 인 격자판으로 나타낼 수 있다.
 * - N+1번 행의 모든 칸에는 성이 있다. 궁수 3명 배치
 * - 궁수가 공격하는 적은 거리가 D 이하인 적 중에서 가장 가까운 적, 여럿일 경우는 가장 왼쪽에 있는 적 공격
 * - 같은 적이 여러 궁수에게 공격당할 수 있다.
 * - 궁수의 공격이 끝나면, 적이 아래로 한칸 이동 / 성이 있는 칸으로 이동한 경우에는 게임에서 제외
 * - 모든 적이 격자판에서 제외되면 게임이 끝난다 / 궁수의 공격으로 제거할 수 있는 적의 최대 수를 계산하는 문제
 *
 * 문제 풀이 설명
 * - 문제에서 설명한대로 구현을 하면 됩니다.
 * 1. 3명의 궁수를 배치할 수 있는 모든 조합에 대해 게임 진행 : 각 게임에서 계산된 카운트로 답을 업데이트
 * 2. 규칙( 궁수가 적을 잡은 후, 적이 이동)에 맞게 턴을 반복해서 진행
 *     - 우선순위 큐로 BFS 탐색을 하며 각 궁수가 공격할 적을 찾는다.
 *     - 선택된 적들을 제거
 *     - 적들이 아래로 1칸씩 이동
 *     - 모든 적이 제거되면 게임 종료
 *
 * 왜 이 문제를 선택했나?
 * - 구현, 시물레이션 문제가 많이 나오기 때문에 어려운 난이도의 문제를 풀어보자는 의미로 선정
 *
 * 풀이 시간
 * - 2시간
 *
 * 시잔 복잡도는
 * - 궁수 선택 조합(M^3) * 게임 진행(적수(N * M) * BFS(D*log(D^2))
 */
public class Henry {
    static int[] dr = {0, -1, 0};
    static int[] dc = {-1, 0, 1};

    static int N, M, D;
    static int[][] map;

    static int answer = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        N = Integer.valueOf(stringTokenizer.nextToken());
        M = Integer.valueOf(stringTokenizer.nextToken());
        D = Integer.valueOf(stringTokenizer.nextToken());
        map = new int[N + 1][M];

        for (int i = 0; i < N; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.valueOf(stringTokenizer.nextToken());
            }
        }

        // 궁수를 배치하는 조합
        for (int i = 0; i < M - 2; i++) {
            for (int j = i + 1; j < M - 1; j++) {
                for (int k = j + 1; k < M; k++) {
                    game(i, j, k);
                }
            }
        }

        System.out.println(answer);
    }

    public static void game(int archer1, int archer2, int archer3) {
        Archer[] archerArray = {new Archer(N, archer1, D),
                new Archer(N, archer2, D), new Archer(N, archer3, D)};

        int[][] tmpMap = new int[N + 1][M];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                tmpMap[i][j] = map[i][j];
            }
        }

        int totalCatchCount = 0;

        while (!isEndGame(tmpMap)) {
            int catchCount = doTurnAndReturnCatchCount(tmpMap, archerArray);
            totalCatchCount += catchCount;
        }

        answer = Math.max(answer, totalCatchCount);
    }

    public static int doTurnAndReturnCatchCount(int[][] tmpMap, Archer[] archerArray) {
        int cnt = 0;

        // 활을 쏜다.
        Set<Point> targetList = new HashSet<>();
        for (Archer archer : archerArray) {
            Optional<Point> target = archer.findTarget(tmpMap);

            if (target.isPresent()) {
                targetList.add(target.get());
            }
        }
        for (Point point : targetList) {
            tmpMap[point.r][point.c] = 0;
            cnt++;
        }

        moveEnemy(tmpMap);

        return cnt;
    }

    public static void moveEnemy(int[][] tmpMap) {
        for (int j = 0; j < M; j++) {
            tmpMap[N-1][j] = 0;
        }

        for (int i = N - 1; i > 0; i--) {
            for (int j = 0; j < M; j++) {
                tmpMap[i][j] = tmpMap[i - 1][j];
                tmpMap[i-1][j] = 0;
            }
        }
    }

    public static boolean isEndGame(int[][] tmpMap) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (tmpMap[i][j] == 1) {
                    return false;
                }
            }
        }

        return true;
    }

    public static class Archer{
        int r;
        int c;
        int d;

        public Archer(int r, int c, int d) {
            this.r = r;
            this.c = c;
            this.d = d;
        }

        public Optional<Point> findTarget(int[][] tmpMap) {
            Point result = null;

            PriorityQueue<Point> priorityQueue = new PriorityQueue<>();
            boolean[][] ch = new boolean[N + 1][M];
            Point startaPoint = new Point(this.r, this.c, 0);
            ch[startaPoint.r][startaPoint.c] = true;
            priorityQueue.add(startaPoint);

            while (!priorityQueue.isEmpty()) {
                Point currentPoint = priorityQueue.poll();

                if (tmpMap[currentPoint.r][currentPoint.c] == 1) {
                    result = currentPoint;
                }

                if (result != null) {
                    break;
                }

                for (int i = 0; i < 3; i++) {
                    int nextR = currentPoint.r + dr[i];
                    int nextC = currentPoint.c + dc[i];

                    if (nextR < 0 || nextR > N || nextC < 0 || nextC > (M - 1)) {
                        continue;
                    }

                    if (ch[nextR][nextC]) {
                        continue;
                    }

                    Point newPoint = new Point(nextR, nextC, currentPoint.distance + 1);
                    if (newPoint.distance > D) {
                        continue;
                    }

                    ch[nextR][nextC] = true;
                    priorityQueue.add(newPoint);
                }
            }

            return Optional.ofNullable(result);
        }
    }

    public static class Point implements Comparable<Point>{
        int r;
        int c;
        int distance;

        public Point(int r, int c, int distance) {
            this.r = r;
            this.c = c;
            this.distance = distance;
        }

        @Override
        public int compareTo(Point o) {
            if (this.distance == o.distance) {
                return this.c - o.c;
            }else{
                return this.distance - o.distance;
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Point point = (Point) o;
            return r == point.r && c == point.c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }
    }
}
