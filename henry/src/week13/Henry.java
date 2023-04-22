package week13;

/**
 * 왜 이 문제를 선택했나?
 * - 완전 탐색 문제를 풀어보고 싶어 선택
 *
 * 문제 푼 데 걸린 시간은?
 * - 1시간
 *
 * 문제 설명
 * - 2개의 매장 설치, N개의 건물과 M개의 양방향 도로
 * - 건물은 1번부터 N번의 번호를 가지고 있다.
 * - 건물 X의 접근성 : X에서 가장 가까운 치킨집까지 왕복하는 최단시간
 * - 즉, "모든 건물에서 가장 가까운 치킨집까지 왕복하는 최단 시간의 총합"을 최소화할 수 있는 건물 2개를 골라서 치킨집을 열려고 하는 것
 * - 출력 : 최적의 위치가 될 수 있는 건물 2개의 번호 및 모든 건물에서 가장 가까운 치킨집까지 완복하는 최단 시간의 총합
 * - 건물 조합이 여러 개라면, 건물 번호 중 작은 게 더 작을수록, 작은 번호가 같다면 큰 번호가 더 작을수록 좋은 건물 조합
 *
 * 문재 풀이
 * - 2개의 조합으로 치킨집을 선택을 한 뒤 거리를 계산하면 시간초과가 발생한다.
 * - 각 건물들 사이의 거리를 미리 구한 뒤, 이를 이용해 답을 갱신해 나간다.
 * - 1. 플로이드 와샬 알고리즘으로 모든 점 사이의 거리를 구한다.
 * - 2. 각 조합에 대해 왕복 거리의 총합을 구한 뒤 거리가 작다면 업데이트
 *
 * 시간 복잡도는?
 * - 플로이드워샬 (n^3) + 조합(n^2) * 거리계산(n) = n^3
 *
 */

import java.util.*;

public class Henry {

    static int n, m;
    static int[][] distance;
    static int minDistance = Integer.MAX_VALUE;
    static List<Integer> answerNodeList;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        m = scanner.nextInt();
        distance = new int[n+1][n+1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (i == j) {
                    distance[i][j] = 0;
                }else {
                    distance[i][j] = 30000; // 무한대
                }
            }
        }

        for(int i=0; i<m; i++){
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            distance[a][b] = 1;
            distance[b][a] = 1;
        }

        // 플로이드워샬
        for (int k = 1; k <= n; k++) {
            for(int i=1; i<=n ;i++){
                for (int j = 1; j <= n; j++) {
                    if (distance[i][j] > (distance[i][k] + distance[k][j])) {
                        distance[i][j] = distance[i][k] + distance[k][j];
                    }
                }
            }
        }

        // 답 갱신
        for(int i=1; i<= n-1; i++){
            for(int j= i+1; j<= n ; j++){
                int totalDistance = getTotalDistance(i, j);
                if(minDistance > totalDistance){
                    answerNodeList = Arrays.asList(i, j);
                    minDistance = totalDistance;
                }
            }
        }

        System.out.println(answerNodeList.get(0) + " " + answerNodeList.get(1) + " " + minDistance);
    }

    static int getTotalDistance(int chicken1, int chicken2) {
        int totalDistance = 0;

        for (int i = 1; i <= n; i++) {
            // 두 치킨집 중 가까운 거리로 선택
            int distanceFromNodeToChicken = Math.min(distance[i][chicken1], distance[i][chicken2]);
            totalDistance+= distanceFromNodeToChicken;
        }

        return totalDistance * 2; // 왕복 거리
    }
}
