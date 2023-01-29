package june.String.단어정렬;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BestSolution {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        String[] strArr = new String[N];
        for (int i = 0; i < N; i++) {
            strArr[i] = sc.next();
        }

        Arrays.sort(strArr, (s1, s2) -> {
            if (s1.length() == s2.length()) {
                return s1.compareTo(s2);
            }
            return s1.length() - s2.length();
        });

        List<String> list = Arrays.stream(strArr)
                .distinct()
                .collect(Collectors.toList());
        for (String s : list) {
            System.out.println(s);
        }
    }

}
