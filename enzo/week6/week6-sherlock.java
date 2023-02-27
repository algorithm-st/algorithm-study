import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Sherlock {

    public static void getAnswer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int length = 4*(N-1)+1;
        int leftTopY = 0;
        int leftTopX = 0;
        int rightBottomY = length-1;
        int rightBottomX = length-1;
        char [][] table = new char[length][length];
        for(int i=0;i<length;i++){
            for(int j=0;j<length;j++){
                table[i][j] = ' ';
            }
        }
        for(int i=0;i<N;i++){
            drawSquare(leftTopY+2*i,leftTopX+2*i,rightBottomY-2*i,rightBottomX-2*i,table);
        }
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<table.length;i++){
            sb.append(new String(table[i])).append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void drawSquare(int leftTopY, int leftTopX, int rightBottomY, int rightBottomX, char[][] table) {
        for(int i= leftTopX; i<=rightBottomX;i++){
            table[leftTopY][i] = '*';
        }
        for(int i= leftTopX; i<=rightBottomX;i++){
            table[rightBottomY][i] = '*';
        }
        for(int i = leftTopY; i<=rightBottomY;i++){
            table[i][leftTopX] = '*';
        }
        for(int i = leftTopY; i<=rightBottomY;i++){
            table[i][rightBottomX] = '*';
        }
    }

    public static void main(String [] args) throws IOException {
        getAnswer();
    }
}
