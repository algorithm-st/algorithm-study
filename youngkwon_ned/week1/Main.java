package youngkwon_ned.week1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int i = Integer.parseInt(br.readLine());
        TreeSet<String> words = new TreeSet<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length() != 0 ? o1.length() - o2.length() : o1.compareTo(o2);
            }
        });
        for (int j = 0; j < i; j++) {
            words.add(br.readLine());
        }
        for (String word : words) {
            System.out.println(word);
        }
    }

}
