package week3_dfs;
import java.util.*;



public class Ned {
    static int n;
    static int cnt = 0; //단지의 수
    static ArrayList<Integer> eachCount = new ArrayList<>(); //단지내 집의 수

    static boolean[][] ch;

    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        n = scanner.nextInt();
        ch = new boolean[n][n];

        //집이 있는 좌표의 ch배열 true로 변경
        for(int i=0; i<n; i++){
            String inputLine = scanner.next();
            for(int j=0; j<n; j++){
                char c = inputLine.charAt(j);
                int number = Integer.parseInt(c+"");
                if(number==1) ch[i][j] = true;
            }
        }

        //ch배열을 탐색하며 방문하지 않은 곳이 있다면, BFS로 해당 단지들 false로 변경
        for(int i=0; i<n; i++){
            for(int j=0; j<n; j++){
                if(ch[i][j]==true){
                    ch[i][j]=false;
                    cnt++;
                    int houseCount = BFS(i, j); //단지내 집의 수 반환
                    eachCount.add(houseCount);
                }
            }
        }

        Collections.sort(eachCount);
        System.out.println(cnt);
        for (Integer i : eachCount) {
            System.out.println(i);
        }
    }

    //단지내 집의 수 반환
    static int BFS(int x, int y){
        //상하좌우
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};

        int houseCount = 0;
        Queue<Point> queue = new LinkedList<>();
        queue.offer(new Point(x, y));

        while (!queue.isEmpty()) {
            Point cur = queue.poll();
            houseCount++;
            for(int i=0; i<4; i++){
                int next_r = cur.r + dr[i];
                int next_c = cur.c + dc[i];

                //map 벗어나는 경우
                if(next_r<0 || next_r>=n || next_c<0 || next_c>=n) continue;
                //방문하면 안되는 경우
                if(ch[next_r][next_c]==false) continue;

                ch[next_r][next_c]= false;
                queue.offer(new Point(next_r, next_c));
            }
        }

        return houseCount;
    }

    static class Point{
        int r;
        int c;

        public Point(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }
}
