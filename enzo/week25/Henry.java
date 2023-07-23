import java.util.ArrayList;
import java.util.List;

public class Solution {
    public List<Integer>[] list;
    public boolean[] check;
    public int maxNum = 0;
    public int[] info_g;
    public void dfs(int wolf, int sheep){
        if(wolf >= sheep) return;
        maxNum = Math.max(maxNum, sheep);
        for(int i=0;i<check.length;i++){
            if(check[i]){
                check[i] = false;
                for(int s : list[i]){
                    check[s] = true;
                }
                if(info_g[i] == 0){
                    dfs(wolf, sheep+1);
                }
                else{
                    dfs(wolf+1, sheep);
                }
                check[i] = true;
                for(int s : list[i]){
                    check[s] = false;
                }
            }
        }
    }
    public int solution(int[] info, int[][] edges) {
        info_g = info;
        int answer = 0;
        list = new List[info.length];
        check = new boolean[info.length];
        for(int i=0;i<info.length;i++){
            list[i] = new ArrayList<Integer>();
        }
        for(int [] edge : edges){
            list[edge[0]].add(edge[1]);
        }
        for(int i : list[0]){
            check[i] = true;
        }
        dfs(0, 1);
        return maxNum;
    }

}
