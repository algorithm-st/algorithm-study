import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Enzo {
    public int solution(int[][] scores) {

        int [][] sortedArray = doSort(scores);

        // 0 score, 1 index
        Queue<int[]> queue = new PriorityQueue<>((o1, o2)->{
            if(o1[0] == o2[0])
                return o1[1] - o2[1];
            return o2[0] - o1[0];
        });
        int maxNum = -1;
        for(int i [] : sortedArray){
            if(i[1] >= maxNum){
                maxNum = i[1];
                queue.add(new int[]{i[1]+i[0],i[2]});
            }
        }
        int count = 1;
        while(!queue.isEmpty()){
            int [] curIndex = queue.poll();
            if(curIndex[1] == 0){
                return count;
            }
            count++;
        }
        return -1;
    }
    private int[][] doSort(int[][] scores){
        int[][] array = new int[scores.length][3];
        for(int i=0;i<scores.length;i++){
            array[i][0] = scores[i][0];
            array[i][1] = scores[i][1];
            array[i][2] = i;
        }
        Arrays.sort(array,(o1, o2)->{
            if(o1[0] == o2[0]){
                if(o1[1] == o2[1]){
                    return o1[0] - o2[0];
                }
                return o1[1] - o2[1];
            }
            return o2[0] - o1[0];
        });
        return array;
    }
}
