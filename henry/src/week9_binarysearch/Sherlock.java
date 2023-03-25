package week9_binarysearch;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Sherlock {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        double x = Double.valueOf(stringTokenizer.nextToken());
        double y = Double.valueOf(stringTokenizer.nextToken());
        double c = Double.valueOf(stringTokenizer.nextToken());


        double max = Math.max(x, y);
        double min = 0D;
        double middle = (max + min) /2D;


        while(true){
            middle = (max + min) /2D;

            double calculatedC = calculateC(x, y, middle);

            if (Math.abs(c - calculatedC) < 0.000001D) {
                System.out.println(middle);
                break;
            }

            if (c > calculatedC) {
                max = middle;
            }else if(c < calculatedC){
                min = middle;
            }else{
                max = middle;
            }
        }
    }

    private static double calculateC(double x, double y, double d){
        double leftHeight = Math.sqrt(Math.pow(x, 2) - Math.pow(d, 2));
        double rightHeight = Math.sqrt(Math.pow(y, 2) - Math.pow(d, 2));
        double c = (leftHeight * rightHeight) / (leftHeight + rightHeight);
        return c;
    }
}
