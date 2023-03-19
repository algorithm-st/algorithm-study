import java.util.*;
public class Han {
    public int solution(int n, int[][] edge) {
        int answer = 0;
        List<Integer> [] list = new List[n+1];
        for(int i=0;i<=n;i++){
            list[i] = new ArrayList<>();
        }
        for(int [] i : edge){
            list[i[0]].add(i[1]);
            list[i[1]].add(i[0]);
        }
        Queue<Integer> queue = new LinkedList<>();
        int[] check = new int[n+1];
        queue.add(1);
        check[1] = 1;
        while(!queue.isEmpty()){
            int index = queue.poll();
            for(int i : list[index]){
                if(check[i] == 0){
                    check[i] = check[index]+1;
                    queue.add(i);
                }
            }
        }
        int count = 0;
        int maxNum =1;
        for(int i=1;i<=n;i++){
            if(check[i] == maxNum){
                count++;
            }
            else if(check[i] > maxNum){
                count =1;
                maxNum =check[i];
            }
        }
        return count;
    }
}
