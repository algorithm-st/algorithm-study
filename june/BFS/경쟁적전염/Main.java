package june.BFS.경쟁적전염;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;

//문제 푼 데 걸린 시간은?
// 25분

//시간 복잡도는 ? (잘 모르겠다면 예상)
// O(K)...?

//문제 풀이 설명
// 바이러스를 큐에 담아서 큐의 크기만큼 BFS 돌리면 되는 문제
// 근데 그전에 낮은 숫자부터 진행하니 정렬을 추가로 진행해줘야함
public class Main {

    public static int[][] arr;
    public static LinkedList<Virus> viri = new LinkedList<>();
    public static int N;
    public static int K;
    public static int S;
    public static int X;
    public static int Y;
    public static int[] dx = {-1, 0, 1, 0};
    public static int[] dy = {0, 1, 0, -1};
//3 3
//1 0 2
//0 0 0
//3 0 0
//2 3 2
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        N = Integer.parseInt(s[0]);
        K = Integer.parseInt(s[1]);
        arr = new int[N][N];
        for (int i = 0; i < N; i++) {
            String[] s1 = br.readLine().split(" ");
            for (int j = 0; j < N; j++) {
                int virusValue = Integer.parseInt(s1[j]);
                if (virusValue != 0) {
                    arr[i][j] = virusValue;
                    viri.add(new Virus(i, j, virusValue));
                }
            }
        }

        String[] s2 = br.readLine().split(" ");
        S = Integer.parseInt(s2[0]);
        X = Integer.parseInt(s2[1]);
        Y = Integer.parseInt(s2[2]);

        Collections.sort(viri);

        bfs(S);
        System.out.println(arr[X - 1][Y - 1]);
    }

    private static void bfs(int s) {
        for (int i = 0; i < s; i++) {
            int size = viri.size();
            for (int j = 0; j < size; j++) {
                Virus virus = viri.pop();
                for (int k = 0; k < 4; k++) {
                    int nx = virus.x + dx[k];
                    int ny = virus.y + dy[k];

                    if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
                        continue;
                    }

                    if (arr[nx][ny] != 0) {
                        continue;
                    }

                    arr[nx][ny] = virus.value;
                    viri.add(new Virus(nx, ny, virus.value));
                }
            }
        }
    }

}

class Virus implements Comparable<Virus> {

    int x;
    int y;
    int value;

    public Virus(int x, int y, int value) {
        this.x = x;
        this.y = y;
        this.value = value;
    }

    @Override
    public int compareTo(Virus o) {
        if (value == o.value) {
            return 0;
        }
        return value < o.value ? -1 : 1;
    }
}
