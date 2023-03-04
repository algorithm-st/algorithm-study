import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 풀이 유형  dfs, bfs, dp
 * 풀이
 * - circle이 가능성있는 것들 for문 돌기
 *     - dfs로 circle 찾는다. circle에 포함된 것을 기록
 * - circle을 node를 queue에다 넣어서 bfs 돌림
 * 걸린 시간 1
 * 시간복잡도 예상 dp이용했으므로 n+e * for문 n
 */
public class Enzo {

    public static int[] check;
    public static List<Integer>[] array;
    public static boolean[] isCircle;

    public static int dfs(int step,int preNode, int curNode, int targetNode){
        if(step>=2 && curNode == targetNode) {
            check[curNode] = 2;
            return check[curNode];
        }
        if(check[curNode]!=0) return check[curNode];
        check[curNode] = 1;
        for(int i : array[curNode]){
            if(i==preNode) continue;
            check[curNode] = Math.max(check[curNode],dfs(step+1,curNode,i,targetNode));
        }
        return check[curNode];
    }
    public static void bfs(){
        boolean [] checkBFS = new boolean[check.length];
        Queue<Integer> queue = new LinkedList<>();
        for(int i=1;i<isCircle.length;i++){
            if(isCircle[i]){
                queue.add(i);
                checkBFS[i] = true;
            }
        }
        while(!queue.isEmpty()){
            int curIndex = queue.poll();
            for(int i : array[curIndex]){
                if(checkBFS[i]) continue;
                check[i] = check[curIndex]+1;
                checkBFS[i] = true;
                queue.add(i);
            }
        }
    }

    public static void getAnswer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        check = new int[N+1];
        array = new List[N+1];
        isCircle = new boolean[N+1];
        for(int i=0;i<=N;i++){
            array[i] = new ArrayList<>();
        }
        for(int i=0;i<N;i++){
            StringTokenizer st=  new StringTokenizer(br.readLine());
            int firstNode = Integer.parseInt(st.nextToken());
            int secondNode = Integer.parseInt(st.nextToken());
            array[firstNode].add(secondNode);
            array[secondNode].add(firstNode);
        }
        for(int i=1;i<=N;i++){
            if(isCircle[i]) continue;
            dfs(0,0,i,i);
            initTable();
        }
        bfs();
        StringBuffer sb = new StringBuffer();
        for(int i=1;i<=N;i++){
            sb.append(check[i]).append(" ");
        }
        System.out.println(sb.toString());
    }

    private static void initTable() {
        for(int i=0;i<check.length;i++){
            if(check[i] == 2){
                isCircle[i] = true;
            }
            check[i] = 0;
        }
    }

    public static void main(String args[]) throws Exception{
        getAnswer();
    }
}
