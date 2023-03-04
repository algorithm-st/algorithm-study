import java.util.*;
class Solution {
    public int maxAnswer = 0;
    public boolean [] checkDungeons;
    public void dfs(int k,int count, int[][] dungeons){
        for(int i=0;i<checkDungeons.length;i++){
            if(checkDungeons[i]) continue;
            if(dungeons[i][0]<=k){
                checkDungeons[i] = true;
                dfs(k-dungeons[i][1],count+1, dungeons);
                checkDungeons[i] = false;
            }
        }
        maxAnswer = Math.max(maxAnswer,count);
    }
    public int solution(int k, int[][] dungeons) {
        checkDungeons = new boolean[dungeons.length];
        dfs(k,0,dungeons);
        int answer = maxAnswer;
        return answer;
    }
}