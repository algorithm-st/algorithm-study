package week7_shortdistance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 문제 설명
 * - V개의 마을과 E개의 도로로 구성되어 있는 도시
 * - 도로를 따라 운동을 하기 위한 경로를 찾는다.
 * - 싸이클을 이루는 도로의 길이의 합이 최소가 되는 도로 길이의 합을 출력
 *
 * 문제 풀이 설명
 * - 싸이클들의 길이를 찾는다.
 *  - 플로이드 워셜 알고리즘으로 모든 점 사이의 최단거리를 찾는다.
 *  - 찾아낸 최단거리를 이용해 싸이클의 길이를 찾는다.(정점 a, b에서 a->b와 b->a 간의 이동이 가능하다면 싸이클이다.)
 * - 찾아낸 사이클들의 길이 중 최소값을 찾는다.
 *
 * 왜 이 문제를 선택했나?
 * - 지난번 서울 지하철 2호선 문제에서 DFS로 싸이클을 찾았었는데, 다른 방법으로도 싸이클을 찾는 방법을 연습할 수 있어서 선택했습니다.
 *
 * 문제 푼 데 걸린 시간은?
 * - 1시간
 *
 * 시간 복잡도는?
 * - v^3 (플로이드 워셜 알고리즘)
 * - v가 400 미만이므로, 64,000,000 정도 나온다.
 */
public class Henry {
    static final int INF = 10000000;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        // 거리 배열 초기화
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        int v = Integer.valueOf(stringTokenizer.nextToken());
        int e = Integer.valueOf(stringTokenizer.nextToken());
        int[][] distanceMap = new int[v + 1][v + 1];
        for (int i = 1; i <= v; i++) {
            for (int j = 1; j <= v; j++) {
                if (i != j) {
                    distanceMap[i][j] = INF;
                }
            }
        }
        for (int i = 0; i < e; i++) {
            stringTokenizer = new StringTokenizer(bufferedReader.readLine());
            Integer from = Integer.valueOf(stringTokenizer.nextToken());
            Integer to = Integer.valueOf(stringTokenizer.nextToken());
            Integer distance = Integer.valueOf(stringTokenizer.nextToken());
            distanceMap[from][to] = distance;
        }


        // 플로이드 워셜 알고리즘 -> 모든 정점간의 최단 거리를 찾는다.
        for (int i = 1; i <= v; i++) {
            for (int j = 1; j <= v; j++) {
                for (int k = 1; k <= v; k++) {
                    distanceMap[j][k] = Math.min(distanceMap[j][k], distanceMap[j][i] + distanceMap[i][k]);
                }
            }
        }

        // 사이클 최소 길이 찾기
        for (int i = 1; i <= v; i++) {
            distanceMap[i][i] = INF;
        }
        for (int i = 1; i <= v; i++) {
            for (int j = 1; j <= v; j++) {
                if (i == j) {
                    continue;
                }
                if (distanceMap[i][j] != INF && distanceMap[j][i]!=INF) { //(i -> j)(j -> i)로 가는 경로가 존재하면 업데이트 가능
                    distanceMap[i][i] = Math.min(distanceMap[i][i], distanceMap[i][j] + distanceMap[j][i]);
                }
            }
        }
        int answer = INF;
        for (int i = 1; i <= v; i++) {
            answer = Math.min(answer, distanceMap[i][i]);
        }

        // 답 출력
        if (answer == INF) {
            System.out.println(-1);
        }else{
            System.out.println(answer);
        }
    }
}
