import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class Ned {

    public static int[] dy = {1,0,-1,0};
    public static int[] dx = {0,1,0,-1};
    public static void getAnswer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int row = 12;
        int col = 6;
        char [][] table = new char[row][col];
        for(int i=0;i<row;i++){
            table[i] = br.readLine().toCharArray();
        }
        int count = 0;
        while(true){
            boolean [][] checkTable =  checkBoom(table);
            int checkCount = deleteCheckAndGetCount(checkTable, table);
            if(checkCount == 0){
                System.out.println(count);
                return;
            }
            count++;
        }
    }

    private static int deleteCheckAndGetCount(boolean[][] checkTable, char[][] table) {
        int count =0;
        int row = table.length;
        int col = table[0].length;
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                if(checkTable[i][j] == true){
                    table[i][j] = '.';
                    count++;
                }
            }
        }
        for(int i=0;i<col;i++){
            int step = row-1;
            for(int j=row-1;j>=0;j--){
                if(table[j][i] != '.'){
                    char temp  = table[j][i];
                    table[j][i] = '.';
                    table[step][i] = temp;
                    step--;
                }
            }
        }
        return count;
    }

    private static boolean[][] checkBoom(char [][] table) {
        int row = table.length;
        int col = table[0].length;
        int [][] check = new int[row][col];
        int step = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<row;i++){
            for(int j=0; j<col;j++){
                if(table[i][j] != '.' && check[i][j] == 0){
                    step++;
                    int size = 1;
                    char fig = table[i][j];
                    Queue<int[]> queue = new LinkedList<>();
                    check[i][j] = step;
                    queue.add(new int[]{i,j});
                    while(!queue.isEmpty()){
                        int[] curIndex = queue.poll();
                        for(int k=0;k<4;k++){
                            int tempy = curIndex[0] + dy[k];
                            int tempx = curIndex[1] + dx[k];
                            if(tempy>=0 && tempx>=0 && tempy<row && tempx<col && table[tempy][tempx] == fig && check[tempy][tempx] == 0){
                                size++;
                                check[tempy][tempx] = step;
                                queue.add(new int[]{tempy,tempx});
                            }
                        }
                    }
                    map.put(step,size);
                }
            }
        }
        boolean [][] remove = new boolean[row][col];
        for(int i=0; i<row; i++){
            for(int j=0; j<col; j++){
                if(check[i][j] !=0 && map.get(check[i][j])>=4){
                    remove[i][j] = true;
                }
            }
        }
        return remove;
    }

    public static void main(String[] args) throws IOException {
        getAnswer();
    }
}
