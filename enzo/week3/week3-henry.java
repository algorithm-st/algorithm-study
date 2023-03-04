import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Henry {
    public static class Point {
        int x, y,index;
        public Point(int x, int y, int index){
            this.x = x;
            this.y = y;
            this.index = index;
        }
        public int getGap(Point other){
            return Math.abs(this.x - other.x) + Math.abs(this.y-other.y);
        }
    }
    public static void getAnswer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuffer sb = new StringBuffer();
        for(int i = 0;i<T;i++){
            int n = Integer.parseInt(br.readLine());
            // 0 start, length()-1 end
            Point [] vertex = new Point[n+2];
            for(int k=0;k<n+2;k++){
                int[] numArr = Arrays.stream(br.readLine().split(" ")).mapToInt(s -> Integer.parseInt(s)).toArray();
                vertex[k] = new Point(numArr[0],numArr[1],k);
            }
            String answer = bfs(n+2, vertex);
            sb.append(answer).append("\n");
        }
        System.out.println(sb.toString());
    }

    public static String bfs(int nodeCount, Point[] vertex){
        boolean [] check = new boolean[nodeCount];
        List<Point> [] edges  = new List[nodeCount];
        for(int i =0; i<nodeCount;i++){
            edges[i] = new ArrayList<>();
        }
        for(int i=0;i<vertex.length;i++){
            for(int j=i+1;j<vertex.length;j++){
                int gap = vertex[i].getGap(vertex[j]);
                if(gap <=1000){
                    edges[vertex[i].index].add(vertex[j]);
                    edges[vertex[j].index].add(vertex[i]);
                }
            }
        }
        check[0] = true;
        Queue<Point> queue = new LinkedList<>();
        queue.add(vertex[0]);

        while(!queue.isEmpty()){
            Point curNode = queue.poll();
            if(curNode.index == nodeCount-1) return "happy";

            for(Point p : edges[curNode.index]){
                if(!check[p.index]){
                    check[p.index] = true;
                    queue.add(p);
                }
            }
        }
        return "sad";
    }
    public static void main(String [] args) throws Exception{
        getAnswer();
    }
}
