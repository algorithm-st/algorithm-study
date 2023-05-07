import java.util.*;

class Solution {
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = new int[2];
        List<int[]>[] list = new List[n+1];
        for(int i=0;i<=n;i++){
            list[i] = new ArrayList<>();
        }
        for(int [] p : paths){
            list[p[0]].add(new int[]{p[1],p[2]});
            list[p[1]].add(new int[]{p[0],p[2]});
        }
        int[][] intenseArray = new int[n+1][2];
        // nodeNum, intese, root
        Queue<int[]> queue = new PriorityQueue<>((o1,o2)->{
            if(o1[1] == o2[1]) return o1[2] - o2[2];
            return o1[1] - o2[1];
        });
        for(int i : summits){
            queue.add(new int[]{i,0,i});
        }
        while(!queue.isEmpty()){
            int [] curIndex = queue.poll();
            if(intenseArray[curIndex[0]][0] != 0) continue;
            intenseArray[curIndex[0]][0] = curIndex[2];
            intenseArray[curIndex[0]][1] = curIndex[1];
            for(int[] i : list[curIndex[0]]){
                if(intenseArray[i[0]][0]==0){
                    queue.add(new int[]{i[0],Math.max(curIndex[1],i[1]),curIndex[2]});
                }

            }
        }
        Arrays.fill(answer, Integer.MAX_VALUE);
        for(int i: gates){
            if(answer[1]>intenseArray[i][1]){
                answer[1] = intenseArray[i][1];
                answer[0] = intenseArray[i][0];
            }
            else if(answer[1] == intenseArray[i][1] && answer[0] > intenseArray[i][0]){
                answer[0] = intenseArray[i][0];
            }
        }
        return answer;
    }
}