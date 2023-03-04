package week5_impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 문제 설명
 * 학생들을 아래 자리 배치 방법에 맞게 배치 후, 모든 학생의 만족도 총합을 출력해야한다.
 * - 교실 N x N, 학생수 N^2
 * - 인접한 칸 : 상하좌우
 * - 각 학생은 좋아하는 학생이 4명씩 존재한다.
 * - 자리 배치 방법
 *  1. 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
 *  2. 1을 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
 *  3. 2를 만족하는 칸도 여러 개인 경우에는 행의 번호가 가장 작은 칸으로, 그러한 칸도 여러 개이면 열의 번호가 가장 작은 칸으로 자리를 정한다. (상, 좌, 우, 하)
 * - 만족도 계산 방법
 *  학생과 인접한 칸에 앉은 좋아하는 학생의 수를 구해야 한다. 그 값이 0이면 학생의 만족도는 0, 1이면 1, 2이면 10, 3이면 100, 4이면 1000이다.
 *
 * 문제 풀이 설명
 * - 자리 배치 방법에 맞게 comparable을 구현한 Point 클래스를 선언 후 우선 순위 큐로 앉을 자리를 찾는다.
 * - 자리를 배치 후, 모든 좌표에 대해 상하좌우를 탐색하며 포인트를 계산한다.
 *
 * 문제를 선택한 이유
 * - 복잡한 조건을 구현 연습에 도움이 될 것 같아서 선택했습니다.
 *
 * 문제 푸는데 걸린 시간
 * - 1시간
 *
 * 시간 복잡도
 * - 자리 배치 : 학생 수(N^2) * 검색(N*2) * 정렬(log(N^2))
 * - 점수 계산 : 맵 크기(N^2)
 * N이 최대 20 -> 자리 배치에 넣어보면 400*400*약(9) = 1,440,000 으로 시간 내 계산 가능
 *
 */
public class Henry {
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};

    static int n;
    static int[][] map;
    static List<Integer>[] likeStudentListArray;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.valueOf(bufferedReader.readLine());
        map = new int[n + 1][n + 1];
        likeStudentListArray = new List[n*n+1];

        for (int i = 1; i <= (n * n); i++) {
            StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

            Integer studentNumber = Integer.valueOf(stringTokenizer.nextToken());
            List<Integer> likeStudentNumberList = new ArrayList<>();
            for (int j = 0; j < 4; j++) {
                likeStudentNumberList.add(Integer.valueOf(stringTokenizer.nextToken()));
            }
            likeStudentListArray[studentNumber] = likeStudentNumberList;

            Point seatPosition = findSeatPosition(likeStudentNumberList);
            map[seatPosition.x][seatPosition.y] = studentNumber;
        }

        int answer =0;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                answer += getPoint(i, j);
            }
        }
        System.out.println(answer);
    }

    static Integer getPoint(int x, int y) {
        int count = 0;

        int studentNumber = map[x][y];

        for (int i = 0; i < 4; i++) {
            int nearX = x + dx[i];
            int nearY = y + dy[i];

            if (nearX < 1 || nearX > n || nearY < 1 || nearY > n) {
                continue;
            }

            if (likeStudentListArray[studentNumber].contains(map[nearX][nearY])) {
                count++;
            }
        }

        if (count == 0) {
            return 0;
        } else if (count == 1) {
            return 1;
        } else if (count == 2) {
            return 10;
        }else if(count==3){
            return 100;
        }else{
            return 1000;
        }
    }

    static Point findSeatPosition(List<Integer> likeStudentNumberList) {
        PriorityQueue<Point> priorityQueue = new PriorityQueue<>();
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (map[i][j] == 0) {
                    Point point = new Point(i, j, likeStudentNumberList);
                    priorityQueue.offer(point);
                }
            }
        }
        return priorityQueue.poll();
    }


    static class Point implements Comparable<Point>{
        int x;
        int y;
        int nearEmptyCount;
        int nearLikeStudentCount;

        public Point(int x, int y, List<Integer> likeStudentNumberList){
            this.x = x;
            this.y = y;
            this.nearEmptyCount = 0;
            this.nearLikeStudentCount = 0;

            for (int i = 0; i < 4; i++) {
                int nearX = x + dx[i];
                int nearY = y + dy[i];

                if (nearX < 1 || nearX > n || nearY < 1 || nearY > n) {
                    continue;
                }

                if (map[nearX][nearY] == 0) {
                    this.nearEmptyCount++;
                }

                if (likeStudentNumberList.contains(map[nearX][nearY])) {
                    this.nearLikeStudentCount++;
                }
            }
        }

        @Override
        public int compareTo(Point o) {
            if (o.nearLikeStudentCount != this.nearLikeStudentCount) {
                return o.nearLikeStudentCount - this.nearLikeStudentCount;
            } else if (o.nearEmptyCount != this.nearEmptyCount) {
                return o.nearEmptyCount - this.nearEmptyCount;
            } else if (this.x != o.x) {
                return this.x - o.x;
            }else{
                return this.y - o.y;
            }
        }
    }
}
