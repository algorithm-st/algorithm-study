import java.util.*;

class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        int[][] array = new int[rows][columns];
        int count = 1;
        for(int i=0;i<rows;i++){
            for(int j=0;j<columns;j++){
                array[i][j] = count++;
            }
        }
        for(int i=0; i<queries.length;i++){
            int minNumber = rotate(array, queries[i]);
            answer[i] = minNumber;
        }
        return answer;
    }
    public int rotate(int[][] array, int[] querie){
        int minX = querie[0]-1;
        int minY = querie[1]-1;
        int maxX = querie[2]-1;
        int maxY = querie[3]-1;
        int minChangeNum = array[minX][minY];
        int firstNum = array[minX][minY];
        for(int i = minX;i<maxX;i++){
            array[i][minY] = array[i+1][minY];
            minChangeNum = Math.min(minChangeNum, array[i][minY]);
        }
        for(int i = minY;i<maxY;i++){
            array[maxX][i] = array[maxX][i+1];
            minChangeNum = Math.min(minChangeNum, array[maxX][i]);

        }
        for(int i= maxX;i>minX;i--){
            array[i][maxY] = array[i-1][maxY];
            minChangeNum = Math.min(minChangeNum, array[i][maxY]);
        }
        for(int i= maxY;i>minY;i--){
            array[minX][i] = array[minX][i-1];
            minChangeNum = Math.min(minChangeNum, array[minX][i]);
        }
        array[minX][minY+1] = firstNum;
        return minChangeNum;
    }
}