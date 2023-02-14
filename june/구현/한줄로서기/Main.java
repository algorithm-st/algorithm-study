package june.구현.한줄로서기;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
//        7
//6 1 1 1 2 0 0
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        boolean[] visited = new boolean[N];
        int[] answer = new int[N];
        String[] s = br.readLine().split(" ");
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(s[i]);
        }

        for (int i = 0; i < N; i++) {
            sol(arr, visited, answer, i);
        }

        String collect = Arrays.stream(answer)
                .mapToObj(String::valueOf)
                .collect(Collectors.joining(" "));
        System.out.println(collect);

    }

    private static void sol(int[] arr, boolean[] visited, int[] answer, int index) {
        int go = arr[index];
        int a = 0;
        while (go > 0) {
            if (visited[a]) {
                a++;
                continue;
            }
            a++;
            go--;
        }
        while (visited[a]) {
            a++;
        }
        answer[a] = index + 1;
        visited[a] = true;
    }
    // arr[index]만큼 차감하면서 앞으로가
    // 근데 방문한 곳이면 차감하지마
    // 앞으로 왔다면 그곳을 채워

}
