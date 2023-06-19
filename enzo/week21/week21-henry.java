import java.util.*;
public class Henry {
    public int[] solution(String[] gems) {
        int [] answer = new int[2];
        Map<String, Integer> map = new HashMap<>();
        int mapIndexCount = 0;
        for(String gem : gems){
            if(!map.containsKey(gem)){
                map.put(gem,mapIndexCount++);
            }
        }
        int [] keyLatestIndex = new int[map.size()];
        Arrays.fill(keyLatestIndex, -1);
        int start = 0;
        int end = 0;
        int gemTrueCount = 0;
        int gap = Integer.MAX_VALUE;
        while(end<gems.length){
            while(gemTrueCount < map.size() && end < gems.length){
                if(keyLatestIndex[map.get(gems[end])] == -1) gemTrueCount++;
                keyLatestIndex[map.get(gems[end])] = end++;
            }
            while(gemTrueCount == map.size() && start != keyLatestIndex[map.get(gems[start])]){
                start++;
            }
            int curGap = Math.abs(end - start);
            if(gap>curGap){
                answer[0] = start+1;
                answer[1] = end;
                gap = answer[1] - answer[0];
            }
            keyLatestIndex[map.get(gems[start])] = -1;
            gemTrueCount--;
            start++;
        }
        return answer;
    }
}