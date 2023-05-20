import java.util.*;

class Solution {
    public int[] dy = {0,1,0,-1};
    public int[] dx = {1,0,-1,0};
    public int[] solution(String[] grid) {

        int n = grid.length;
        int m = grid[0].length();
        boolean [][][] checkCircle = new boolean[n][m][4];
        List<Integer> list = new ArrayList<>();
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                for(int k=0;k<4;k++){
                    if(!checkCircle[i][j][k]) list.add(getCircle(n,m,grid,checkCircle,i,j,k));
                }
            }
        }
        return list.stream().sorted().mapToInt(i->i).toArray();
    }
    public int getCircle(int n, int m, String[] grid, boolean[][][] checkCircle, int y, int x, int direction){
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{y,x,direction});
        int count = 0;
        while(!queue.isEmpty()){
            int [] curNum = queue.poll();
            checkCircle[curNum[0]][curNum[1]][curNum[2]] = true;
            count++;
            int nextDir = getDirection(grid[curNum[0]].charAt(curNum[1]),curNum[2]);
            int nextY = (curNum[0] + dy[nextDir]+n)%n;
            int nextX = (curNum[1] + dx[nextDir]+m)%m;
            if(!checkCircle[nextY][nextX][nextDir])
                queue.add(new int[]{nextY,nextX, nextDir});
        }
        return count;
    }
    public int getDirection(char nodeVal, int curDirection){
        if(nodeVal == 'L'){
            return (curDirection-1+4)%4;
        }
        else if(nodeVal == 'R'){
            return (curDirection+1)%4;

        }
        else{
            return curDirection;
        }
    }
}