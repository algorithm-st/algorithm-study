package june.빽트래킹.타겟넘버;

class Solution {
    static int[] op;
    static boolean[] visited;
    static int tar;
    static int count = 0;

    public static void main(String[] args) {
        int[] num = {1, 1, 1, 1, 1};
        int solution = solution(num, 3);
        System.out.println(solution);

    }
    public static int solution(int[] numbers, int target) {
        op = new int[numbers.length];
        visited = new boolean[numbers.length];
        tar = target;


        dfs(0,numbers.length, numbers);
        return count;
    }

    public static void dfs(int index, int len, int[] numbers){
        if(index == len){
            int total = 0;
            for(int i = 0; i<len; i++){

                if(op[i] == 0){
                    total += numbers[i];
                }else{
                    total -= numbers[i];
                }
            }

            if(total == tar){
                count++;
            }
            return;
        }
        for(int i = 0; i < len; i++){
            if(!visited[i]){
                visited[i] = true;
                op[i] = 1;
                dfs(index +1, len, numbers);

                visited[i] = false;
                op[i] = 0;
            }
        }
    }
}