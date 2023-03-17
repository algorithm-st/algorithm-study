package june.이분탐색.IF문좀대신써줘;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main2 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int N = Integer.parseInt(s[0]);
        int M = Integer.parseInt(s[1]);
        ArrayList<Cham> chams = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            String[] s1 = br.readLine().split(" ");
            String a = s1[0];
            int b = Integer.parseInt(s1[1]);
            chams.add(new Cham(a, b));
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            sb.append(binary(Integer.parseInt(br.readLine()), chams)).append("\n");
        }
        System.out.println(sb);
    }

    private static String binary(int target, ArrayList<Cham> chams) {
        int left = 0;
        int right = chams.size() - 1;
        int mid = 0;
        while (left <= right) {
            mid = (left + right) / 2;
            if (chams.get(mid).val < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return chams.get(right + 1).name;
    }

    static class Cham{

        String name;
        int val;

        public Cham(String name, int val) {
            this.name = name;
            this.val = val;
        }
    }

}
