package week3_dfs;

public class Han {
    int answer = 0;
    public int solution(int[] numbers, int target) {

        DFS(numbers, target, 0, 0);


        return answer;
    }
    void DFS(int[] numbers, int target, int cnt, int cur) {
        if(cnt==numbers.length) {
            if(cur==target) {
                answer++;
            }
            return ;
        }else {
            DFS(numbers, target, cnt+1, cur+numbers[cnt]);
            DFS(numbers, target, cnt+1, cur-numbers[cnt]);
        }
        return ;
    }
}
