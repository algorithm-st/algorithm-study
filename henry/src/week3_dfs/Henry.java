package week3_dfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Henry {

    private static List<Point> storeList = new ArrayList<>();
    private static Point startPoint;
    private static Point endPoint;
    private static boolean isHappy;
    private static boolean[] ch;

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        Integer t = Integer.valueOf(bufferedReader.readLine());

        for (int i = 0; i < t; i++) {

            // 초기화
            Integer n = Integer.valueOf(bufferedReader.readLine());
            ch = new boolean[n];
            startPoint = readPoint(bufferedReader);
            storeList.clear();
            for (int j = 0; j < n; j++) {
                storeList.add(readPoint(bufferedReader));
            }
            endPoint = readPoint(bufferedReader);
            isHappy = false;

            // 탐색 - 목적지에 도달할 수 있는지?
            dfs(startPoint.x, startPoint.y);

            // 결과 출력
            if(isHappy){
                System.out.println("happy");
            }else{
                System.out.println("sad");
            }
        }
    }

    static void dfs(int x, int y) {
        if(isHappy){ // 이미 목적지에 도달했으면 탐색 종료
            return;
        }

        // 목적지인가?
        if ((Math.abs(x - endPoint.x) + Math.abs(y - endPoint.y)) <= 1000) {
            isHappy = true;
            return;
        }

        for(int i = 0; i< storeList.size(); i++){
            Point store = storeList.get(i);

            if (ch[i]) { // 이미 방문한 편의점이면 방문하지 않는다.
                continue;
            }

            if(Math.abs(x - store.x) + Math.abs(y - store.y) > 1000){ // 편의점에 도달할 수 있는지 체크
                continue;
            }

            ch[i] = true;
            dfs(store.x, store.y);
        }
    }

    static Point readPoint(BufferedReader bufferedReader) throws IOException {
        String readLine = bufferedReader.readLine();

        StringTokenizer stringTokenizer = new StringTokenizer(readLine, " ");
        Integer x = Integer.valueOf(stringTokenizer.nextToken()) + 32768;
        Integer y = Integer.valueOf(stringTokenizer.nextToken()) + 32768;

        return new Point(x, y);
    }

    static class Point{
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
