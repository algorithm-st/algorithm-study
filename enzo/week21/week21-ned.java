import java.util.*;
public class Ned {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        int [] check = new int[words.length+1];
        Arrays.fill(check, -1);

        Map<String, Integer> checkIndexMap = new HashMap<>();
        checkIndexMap.put(begin,0);
        for(int i=0; i<words.length;i++){
            checkIndexMap.put(words[i],i+1);
        }
        Queue<String> queue = new LinkedList<>();
        check[checkIndexMap.get(begin)] = 0;
        queue.add(begin);
        while(!queue.isEmpty()){
            String curWord = queue.poll();
            int curCheckIndex = checkIndexMap.get(curWord);
            if(curWord.equals(target)) return check[curCheckIndex];
            for(String s : words){
                if(check[checkIndexMap.get(s)] != -1) continue;
                if(isDiffOne(curWord,s)){
                    check[checkIndexMap.get(s)] = check[curCheckIndex] +1;
                    queue.add(s);
                }
            }
        }
        return 0;
    }
    public boolean isDiffOne(String target, String next){
        int count = 0;
        for(int i=0;i<target.length();i++){
            if(target.charAt(i) != next.charAt(i)) count++;
        }
        return count == 1;
    }
}