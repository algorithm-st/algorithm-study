public class June {

    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer = new int[queries.length];
        int [][] table = new int[rows+1][columns+1];
        int count =1;
        for(int i=1;i<=rows;i++){
            for(int j=1;j<=columns;j++){
                table[i][j] = count++;
            }
        }
        int index = 0;
        for(int [] query : queries){
            int minAnswer = rotate(table, query);
            answer[index++] = minAnswer;
        }
        return answer;
    }
    public int rotate(int[][] table, int[] query){
        int n = 2*(query[2] - query[0]) + 2*(query[3]-query[1]);
        int [] arr = new int[n];
        int count =0;
        int minAnswer = Integer.MAX_VALUE;
        // x
        for(int i=query[1];i<query[3];i++){
            arr[count++] = table[query[0]][i];
            minAnswer = Math.min(arr[count-1],minAnswer);
        }
        for(int i=query[0];i<query[2];i++){
            arr[count++] = table[i][query[3]];
            minAnswer = Math.min(arr[count-1],minAnswer);

        }
        for(int i=query[3];i>query[1];i--){
            arr[count++] = table[query[2]][i];
            minAnswer = Math.min(arr[count-1],minAnswer);
        }
        for(int i=query[2];i>query[0];i--){
            arr[count++] = table[i][query[1]];
            minAnswer = Math.min(arr[count-1],minAnswer);
        }

        count = n-1;

        for(int i=query[1];i<query[3];i++){
            table[query[0]][i] = arr[count%n];
            count++;
        }
        for(int i=query[0];i<query[2];i++){
            table[i][query[3]] =  arr[count%n];
            count++;
        }
        for(int i=query[3];i>query[1];i--){
            table[query[2]][i] = arr[count%n];
            count++;
        }
        for(int i=query[2];i>query[0];i--){
            table[i][query[1]] =  arr[count%n];
            count++;
        }
        return minAnswer;
    }
    public static void main(String args[]){
        June june = new June();
        june.solution(6,6,	new int[][]{{2, 2, 5, 4}, {3, 3, 6, 6}, {5, 1, 6, 3}});
    }
}
