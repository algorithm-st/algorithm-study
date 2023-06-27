package june.프로그래머스.단어변환;

import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        
        LinkedList<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();
        visited.add(begin);
        q.add(begin);
        
        int len = q.size();
        int count = 0;
        while(!q.isEmpty()){
            len = q.size();
            for(int i=0;i<len;i++){
                String now = q.poll();
                for(String word:words){
                    if (visited.contains(word)) {
                        continue;
                    }
                    if (!isOneDiff(now, word)) {
                        continue;
                    }

                    if (word.equals(target)) {
                        return count + 1;
                    }
                    
                    q.add(word);
                    visited.add(word);
                }
            }
            count++;
        }
        
        
        return 0;
    }
    
    public boolean isOneDiff(String now, String word){
        int count = 0;
        for(int i=0;i<now.length();i++){
            if(now.charAt(i) != word.charAt(i)){
                count++;
                if (count > 1) {
                    return false;
                }
            }
        }
        return count == 1;
    }
}