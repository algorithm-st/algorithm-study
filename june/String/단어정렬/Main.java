package june.String.단어정렬;

import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int total = sc.nextInt();
        PriorityQueue<Word> words = new PriorityQueue<>();
        for (int i = 0; i < total; i++) {
            Word word = new Word(sc.next());
            if (!words.contains(word)) {
                words.add(word);
            }
        }
        for (; 0 < words.size();) {
            System.out.println(words.poll());
        }
    }

}

class Word implements Comparable<Word> {

    String value;

    public Word(String value) {
        this.value = value;
    }

    @Override
    public int compareTo(Word o) {
        if (value.equals(o.value)) {
            return 0;
        }
        if (value.length() == o.getLength()) {
            return value.compareTo(o.value);
        } else {
            return value.length() < o.getLength() ? -1 : 1;
        }
    }

    public int getLength() {
        return value.length();
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object obj) {
        Word w = (Word) obj;
        return w.getValue().equals(value);
    }
}
// 요구사항
// 단어 짧은게 앞으로
// 길이 같으면 사전 순으로
// 중복 제거
