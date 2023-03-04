import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 풀이 유형  bfs, 메모제이션
 * 풀이
 * - start, end index 지정
 * - start index 시작으로 bfs
 * 걸린 시간 12
 * 시간복잡도 n+e
 */
public class Ned {
    public static final int STAND_NUM = 10;
    public static void getAnswer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        List<Integer> [] array = new List[n+1];
        for(int i=0;i<=n;i++){
            array[i] = new ArrayList<>();
        }

        int k = Integer.parseInt(br.readLine());
        for(int i=0;i<k;i++){
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int p = Integer.parseInt(st.nextToken());
            array[c].add(p);
            array[p].add(c);
        }

        int[] check = new int[n+1];
        check[start] = STAND_NUM;
        Queue<Integer> queue = new LinkedList<>();
        queue.add(start);
        while(!queue.isEmpty()){
            int curIndex = queue.poll();
            if(curIndex == end){
                System.out.println(check[curIndex]-STAND_NUM);
                return;
            }
            for(int i : array[curIndex]){
                if(check[i] == 0){
                    check[i] = check[curIndex]+1;
                    queue.add(i);
                }
            }
        }
        System.out.println(-1);
    }
    public static void main(String args[]) throws Exception{
        getAnswer();
    }
}
