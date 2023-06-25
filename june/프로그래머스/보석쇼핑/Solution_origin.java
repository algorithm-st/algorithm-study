package june.프로그래머스.보석쇼핑;

import java.util.*;

public class Solution_origin {
    public int[] solution(String[] gems) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        HashSet<String> hashSet = new HashSet<>(Arrays.asList(gems));
    
        Queue<String> interval = new LinkedList<>();
        int length = Integer.MAX_VALUE;
        int startIndex = 0;
        int answer = 0;
        for(int i = 0; i<gems.length; i++){
            String gemName = gems[i];
            
            hashMap.put(gemName, hashMap.getOrDefault(gemName, 0) + 1);
            interval.offer(gemName);
            while(true){
                String first = interval.peek();
                int firstGemCount = hashMap.get(first);
                if(firstGemCount>1){
                    hashMap.put(first, firstGemCount-1);
                    interval.poll();
                    
                    startIndex++;
                }else{
                    break;
                }
            }
            
            if(hashMap.size() == hashSet.size() && length > interval.size()){
                length = interval.size();
                answer = startIndex;
            }
        }
        
        return new int[]{answer+1, answer + length};
    }
}
// 1. 아이디어 
// 보석들을 모두