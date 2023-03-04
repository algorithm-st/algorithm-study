import java.util.*;
class Solution {
    public int [] dy = {0,1,0,-1};
    public int [] dx = {1,0,-1,0};
    public int solution(int[][] maps) {
        int answer = 0;
        Queue<int[]> queue = new LinkedList<>();
        int n = maps.length;
        int m = maps[0].length;
        int [][] checkMaps = new int[n][m];
        queue.add(new int[]{0,0});
        checkMaps[0][0] = 1;
        while(!queue.isEmpty()){
            int[] curCord = queue.poll();
            if(curCord[0] == n-1 && curCord[1] == m-1) return checkMaps[n-1][m-1];
            for(int i=0;i<4;i++){
                int tempy = curCord[0] + dy[i];
                int tempx = curCord[1] + dx[i];
                if(tempy>=0 && tempx>=0 && tempy<n && tempx<m && checkMaps[tempy][tempx] ==0 && maps[tempy][tempx] == 1){
                    checkMaps[tempy][tempx] = checkMaps[curCord[0]][curCord[1]]+1;
                    queue.add(new int[]{tempy,tempx});
                }
            }
        }
        return -1;
    }
}