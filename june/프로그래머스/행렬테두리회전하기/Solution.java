package june.프로그래머스.행렬테두리회전하기;


public class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int len = queries.length;
        int[] answer = new int[len];
        int[][] arr = new int[rows][columns];
        int num = 1;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < columns; j++){
                arr[i][j] = num++;
            }
        }
        // print(rows, columns, arr);
        for(int i = 0; i<len; i++){
            rotate(queries[i], arr, answer, i);
        }
        return answer;
    }

    public void rotate(int[] query, int[][] arr, int[] answer, int index){
        int x1 = query[0]-1;
        int y1 = query[1]-1;
        int x2 = query[2]-1;
        int y2 = query[3]-1;

        int start = arr[x1][y1];
        int min = start;
        // 위로 올리기 -> x2 부터 x1까지 , y1
        for(int i = x1; i < x2; i++){
            arr[i][y1] = arr[i+1][y1];
            min = Math.min(min, arr[i+1][y1]);
        }

        // 왼쪽으로 -> y2 부터 y1 까지, x2
        for(int i = y1; i < y2; i++){
            arr[x2][i] = arr[x2][i+1];
            min = Math.min(min, arr[x2][i+1]);
        }

        // 아래로 -> x1 부터 x2 까지, y2
        for(int i = x2; i > x1; i--){
            arr[i][y2] = arr[i-1][y2];
            min = Math.min(min, arr[i-1][y2]);
        }

        // 오른쪽 -> y1 부터 y2까지, x1
        for(int i =y2; i>y1; i--){
            arr[x1][i] = arr[x1][i-1];
            min = Math.min(min, arr[x1][i-1]);
        }
        arr[x1][y1 + 1] = start;
        answer[index] = min;
    }

    public void print(int rows,int columns, int[][] arr){
        for(int i = 0 ; i < rows; i++){
            for(int j = 0; j<columns; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

}
