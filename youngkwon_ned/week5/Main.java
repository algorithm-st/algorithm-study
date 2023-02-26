package youngkwon_ned.week5;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <a href="https://www.acmicpc.net/problem/5430">AC</a>
 */
// 시간 초과
public class Main {
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            String operation = br.readLine();
            int n = Integer.parseInt(br.readLine());
            String strArray = br.readLine();
            int[] array = parsingStringToArray(strArray);

            List<Integer> integers = Arrays.stream(array).boxed().collect(Collectors.toList());
            boolean isError = false;
            for (int j = 0; j < operation.length(); j++) {
                char c = operation.charAt(j);
                if (c == 'R') {
                    Collections.reverse(integers);
                }
                if (c == 'D') {
                    if (integers.size() > 0) {
                        integers.remove(0);
                    } else {
                        isError = true;
                        sb.append("error\n");
                    }
                }
            }
            if (isError) {
                continue;
            }
            int[] result = integers.stream().mapToInt(value -> value).toArray();
            sb.append(Arrays.toString(result)).append("\n");
        }

        System.out.println(sb);
    }
//    private static int[] reverse

    private static int[] parsingStringToArray(String strArray) {
        strArray = strArray.replace("[", "");
        strArray = strArray.replace("]", "");
        if (strArray.equals("")) {
            return new int[0];
        }
        String[] strings = strArray.split(",");
        return Arrays.stream(strings).mapToInt(Integer::parseInt).toArray();
    }
}
