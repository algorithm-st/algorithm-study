package week13;

import java.util.*;
public class Han {
    static Map<Integer, Set<Integer>> tree = new HashMap<>();
    static Set<Integer> ch = new HashSet<>();
    static int nodeCnt;
    static int min;

    public int solution(int n, int[][] wires) {
        int answer = -1;

        // init
        nodeCnt = n;
        min = n;
        for(int i=0; i<n-1; i++){
            int a = wires[i][0];
            int b = wires[i][1];
            tree.putIfAbsent(a, new HashSet<>());
            tree.putIfAbsent(b, new HashSet<>());
            tree.get(a).add(b);
            tree.get(b).add(a);
        }

        getChildCnt(wires[0][0]);
        answer = min;

        return answer;
    }

    int getChildCnt(int node){
        ch.add(node);
        int cnt = 1;

        for(int child : tree.get(node)){
            if(ch.contains(child)){
                continue;
            }

            cnt += getChildCnt(child);
        }

        int restNodeCnt= nodeCnt - cnt;
        int diff = Math.abs(restNodeCnt - cnt);

        if(diff<min){
            min = diff;
        }
        return cnt;
    }
}
