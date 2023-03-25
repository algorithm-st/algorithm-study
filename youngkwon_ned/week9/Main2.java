package youngkwon_ned.week9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42579">베스트앨범</a>
 */
public class Main2 {
    private static Map<String, Integer> COUNT = new HashMap<>();

    private static List<Integer> result = new ArrayList<>();
    public static void main(String[] args) {
        Main2 main2 = new Main2();
        int[] plays = {500, 600, 150, 800, 2500};
        String[] genres = {"classic", "pop", "classic", "classic", "pop"};
        int[] solution = main2.solution(genres, plays);
        System.out.println("solution = " + Arrays.toString(solution));
    }

    public int[] solution(String[] genres, int[] plays) {
        for (int i = 0; i < plays.length; i++) {
            COUNT.put(genres[i], plays[i] + COUNT.getOrDefault(genres[i], 0));
        }

        List<Song> list = new ArrayList<>();
        for (int i = 0; i < genres.length; i++) {
            String genre = genres[i];
            int play = plays[i];
            Song song = new Song(genre, play, i);
            list.add(song);
        }

        list.sort((o1, o2) -> o2.getCount() - o1.getCount());

        Set<Item> set = new TreeSet<>((o1, o2) -> o2.getPlay() - o1.getPlay());
        Set<String> keySet = COUNT.keySet();
        for (String key : keySet) {
            Integer integer = COUNT.get(key);
            set.add(new Item(key, integer));
        }

        for (Item item : set) {
            for (int i = 0; i < 2; i++) {
                list.stream()
                        .filter(song -> song.getGenres().equals(item.getGenres()) && song.getCount() != -1)
                        .findFirst()
                        .ifPresent(song -> {
                            result.add(song.getNumber());
                            song.count = -1;
                        });
            }
        }

        return result.stream().mapToInt(Integer::intValue).toArray();
    }

    private static class Item {
        private final String genres;
        private final Integer play;

        public Item(String genres, Integer play) {
            this.genres = genres;
            this.play = play;
        }

        public String getGenres() {
            return genres;
        }

        public Integer getPlay() {
            return play;
        }
    }

    private static class Song {
        public Song(String genres, int count, int number) {
            this.genres = genres;
            this.count = count;
            this.number = number;
        }

        private final String genres;
        private int count;
        private final int number;

        public int getNumber() {
            return number;
        }

        public String getGenres() {
            return genres;
        }

        public int getCount() {
            return count;
        }
    }
}
