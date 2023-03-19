package sherlock.week3;

public class Han {
    public static void main(String[] args) {
        int[] numbers = {1, 1, 1, 1, 1};
        int target = 3;

        int answer = dfs(numbers, 0, 0, target);
        System.out.println(answer);
    }

    public static int dfs(int[] numbers, int depth, int sum, int target) {
        if (depth == numbers.length) {
            if (sum == target) {
                return 1;
            } else {
                return 0;
            }
        }
        return dfs(numbers, depth + 1, sum + numbers[depth], target) + dfs(numbers, depth + 1, sum - numbers[depth], target);
    }
}
