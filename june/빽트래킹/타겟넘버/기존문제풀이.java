package june.빽트래킹.타겟넘버;

public class 기존문제풀이 {
    class Solution {
        public int solution(int[] numbers, int target) {
            BFS bfs = new BFS(target, numbers);
            bfs.calculate(0, 0);
            return bfs.getAnswer();
        }
    }
    class BFS {
        int answer = 0;
        int target;
        int[] numbers;

        public BFS(int target, int[] numbers) {
            this.target = target;
            this.numbers = numbers;
        }

        public int getAnswer() {
            return answer;
        }

        public void calculate(int level, int sum) {
            if (numbers.length == level) {
                if (sum == target) {
                    answer++;
                }
                return;
            }
            calculate(level + 1, sum + numbers[level]);
            calculate(level + 1, sum - numbers[level]);
        }
    }
}
