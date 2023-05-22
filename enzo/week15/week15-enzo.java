import java.util.*;

class Solution {
    public String [] sArr = {"","A","E","I","O","U"};
    public Set<String> set = new TreeSet<>();
    public void dfs(int step,int maxStep, String text){
        if(step == maxStep){
            set.add(text);
            return;
        }
        for(String s : sArr){
            dfs(step+1,maxStep, text+s);
        }
    }
    public int solution(String word) {
        int answer = 0;
        dfs(0,5,"");
        int count =0;
        for(String s : set){
            if(s.equals(word)) return count;
            count++;
        }
        return answer;
    }
}