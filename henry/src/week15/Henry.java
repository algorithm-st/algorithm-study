package week15;

public class Henry {
    /**
     왜 이 문제를 선택했나?
     - 프로그래머스 레벨 2 중에 하나 선택

     문제 푸는 데 걸린 시간?
     - 1시간 30분

     시간 복잡도는?
     - balls 배열의 길이 * 4

     문제 설명
     - 항상 같은 위치에 공을 놓고 쳐서 리스트에 담긴 위치에 놓인 공을 맞춘다.
     - 각각의 목표로한 공에 원쿠션으로 맞을 때까지 공이 굴러간 거리의 최소값의 제곱들을 배열로 반환한다.
     - 단, 꼭지점에 부딪힐 경우 진입 방향의 반대 방향으로 공이 진행된다.

     문제 풀이 설명
     - 벽을 쿠션으로 사용하는 경우가 항상 모서리를 쿠션으로 사용하는 경우보다 짧다 -> 모서리는 고려하지 않아도 된다.
     - 벽을 쿠션으로 사용하는 경우는 벽을 기준으로 대칭을 시켜 피타고라스의 정리로 굴러가는 거리를 구한다.
     - 4가지 벽면에 대해 모두 계산을 한뒤 최소값을 찾는다.
     - 벽에 도달하기 전에 목적구에 먼저 닿는 경우는 제외한다.

     */
    static class Solution {
        // m - 가로(열)
        // n - 세로(행)

        public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
            int[] answer = new int[balls.length];

            for(int i=0; i<balls.length; i++){
                int x = balls[i][0];
                int y = balls[i][1];

                int minDis = Integer.MAX_VALUE;
                // x = 0 쿠션
                if(!(startY==y && startX > x)){// 쿠션이 가능한지 체크(목적구에 먼저 닿는 경우)
                    int tmp = Integer.valueOf((int)(Math.pow(Math.abs(startY-y), 2)+
                            (int) Math.pow(startX+x, 2)));
                    minDis = Math.min(minDis,tmp);
                }

                // x = m 쿠션
                if(!(startY==y && startX < x)){// 쿠션이 가능한지 체크(목적구에 먼저 닿는 경우)
                    int tmp = Integer.valueOf(
                            (int) Math.pow(Math.abs(startY - y), 2)+
                                    (int) Math.pow((m-startX) + (m-x), 2)
                    );
                    minDis = Math.min(minDis, tmp);
                }

                // y = n 쿠션
                if(!(startX==x && startY < y)){// 쿠션이 가능한지 체크(목적구에 먼저 닿는 경우)
                    int tmp = Integer.valueOf(
                            (int)(Math.pow(Math.abs(startX - x), 2) +
                                    (int) Math.pow((n-startY)+(n-y), 2)));
                    minDis = Math.min(minDis, tmp);
                }

                // y = 0 쿠션
                if(!(startX == x && startY > y)){// 쿠션이 가능한지 체크(목적구에 먼저 닿는 경우)
                    int tmp = Integer.valueOf(
                            (int) Math.pow(Math.abs(startX - x), 2)+
                                    (int) Math.pow(startY+y, 2));
                    minDis = Math.min(minDis, tmp);
                }

                answer[i] = minDis;
            }
            return answer;
        }
    }
}
