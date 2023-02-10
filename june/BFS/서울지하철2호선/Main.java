package june.BFS.서울지하철2호선;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

// 시간
// 1시간

//문제 풀이 설명
// Station 클래스 만들어서 인접 station 과 순환선과 거리, 순환여부를 들고있는다.
// 순환여부를 계산하는 BFS 만들어서 rotate = true, false 계산해서 rotate 이면 거리 =0, false면 거리계산함수()
// 거리계산 함수는 rotate = true인 station 만날때까지 BFS 돌리려고 했음

// 순환여부 계산 하는 함수 만들다가 실패...ㅜㅜ
public class Main {
    public static int N;
    public static HashMap<Integer, Station> secondList = new HashMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        for (int i = 0; i < N; i++) {
            secondList.put(i + 1, new Station());
        }
        for (int i = 0; i < N; i++) {
            String[] s = br.readLine().split(" ");
            int a = Integer.parseInt(s[0]);
            int b = Integer.parseInt(s[1]);
            secondList.get(a).add(b);
            secondList.get(b).add(a);
        }

        for (int i = 0; i < N; i++) {
            Station station = secondList.get(i + 1);
            station.checkRotate(i + 1);
            System.out.println(i + 1 + ":" + station.isRotate());
        }

    }

    public static class Station {

        int distance;
        List<Integer> list;
        boolean rotate;

        public Station() {
            list = new ArrayList<>();
        }

        public void add(int stationNum) {
            list.add(stationNum);
        }

        public void checkRotate(int index) {
            LinkedList<int[]> queue = new LinkedList<>();
            boolean[] visited = new boolean[N];
            visited[index - 1] = true;
            for (Integer adj : list) {
                queue.add(new int[]{0, adj});
                visited[adj - 1] = true;
            }
            while (!queue.isEmpty()) {
                int[] pop = queue.pop();
                for (Integer adj : secondList.get(pop[1]).list) {
                    if (adj != pop[0] && visited[adj - 1]) {
                        rotate = true;
                        return;
                    } else if (!visited[adj - 1]) {
                        queue.add(new int[]{pop[1], adj});
                        visited[pop[1] - 1] = true;
                    }
                }
            }
            rotate = false;
        }

        public boolean isRotate() {
            return rotate;
        }
    }
}
