package june.프로그래머스.베스트앨범;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Main {

    class Solution {
        public int[] solution(String[] genres, int[] plays) {
            HashMap<String, Integer> map = new HashMap();
            for(int i =0;i<genres.length;i++){
                map.put(genres[i], map.getOrDefault(genres[i], 0) + plays[i]);
            }
            ArrayList<String> gen = new ArrayList();
            ArrayList<Integer> answer = new ArrayList();
            for(String ge : map.keySet()){
                gen.add(ge);
            }
            Collections.sort(gen, (o1, o2) -> map.get(o2) - map.get(o1));

            for(int i =0;i<gen.size(); i++){

                int firstIndex = -1;
                int max = -1;
                String genr = gen.get(i);
                for(int j = 0; j<genres.length; j++){
                    if(genr.equals(genres[j]) && max < plays[j]){
                        max = plays[j];
                        firstIndex = j;
                    }
                }
                System.out.println("firstIndex = " + firstIndex + ", max = " + max);
                answer.add(firstIndex);

                max = -1;
                int secondIndex = -1;
                for(int j = 0; j<genres.length; j++){
                    if(j!=firstIndex && genr.equals(genres[j]) && max < plays[j]){
                        max = plays[j];
                        secondIndex = j;
                    }
                }
                System.out.println("secondIndex = " + secondIndex + ", max = " + max);

                if(secondIndex != -1){
                    answer.add(secondIndex);
                }
            }
            return answer.stream().mapToInt(i->i).toArray();
        }
    }
}
