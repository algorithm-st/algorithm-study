import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Enzo {
    // 팀장 골랐을 때, 팀장을 고르지 않았을 때
    public Map<Integer, int[]> mapDp = new HashMap<>();
    public int N;
    public List<Integer>[] edges;
    public int[] sales_g;
    public void dfs(int pre, int curIndex){
        if(curIndex != 1 &&  edges[curIndex].size() == 1){
            int[] currentDpIndex = mapDp.get(curIndex);
            currentDpIndex[0] = sales_g[curIndex];
            currentDpIndex[1] = 0;
            return;
        }
        for(int i : edges[curIndex]){
            if(i == pre) continue;
            dfs(curIndex,i);
        }
        int miniNumTotal = 0;
        int oneParentTotal = Integer.MAX_VALUE/2;
        for(int i : edges[curIndex]){
            if(pre == i) continue;
            miniNumTotal += Math.min(mapDp.get(i)[0], mapDp.get(i)[1]);
        }
        for(int i : edges[curIndex]){
            if(pre == i) continue;
            int curNodeParentTotal = miniNumTotal - Math.min(mapDp.get(i)[1],mapDp.get(i)[0]) + mapDp.get(i)[0];
            oneParentTotal = Math.min(oneParentTotal,curNodeParentTotal);
        }
        int [] currentDpVal = mapDp.get(curIndex);
        currentDpVal[0] = miniNumTotal + sales_g[curIndex];
        currentDpVal[1] = oneParentTotal;

    }
    public int solution(int[] sales, int[][] links) {
        int answer = 0;
        N = sales.length;
        sales_g = new int[N+1];
        edges = new List[N+1];
        for(int i=0;i<=N;i++){
            if(i == 0){
                sales_g[0] = 0;
            }
            else{
                sales_g[i] = sales[i-1];
            }
            edges[i] = new ArrayList<>();
            mapDp.put(i,new int[]{0,0});
        }
        for(int [] link : links){
            edges[link[0]].add(link[1]);
            edges[link[1]].add(link[0]);
        }
        dfs(0,1);
        answer = Math.min(mapDp.get(1)[1],mapDp.get(1)[0]);
        return answer;
    }
}
