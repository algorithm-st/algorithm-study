package week9_binarysearch;

public class Enzo {
    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] times = {7, 10};
        long answer = solution.solution(6, times);
        System.out.println(answer);
    }

    static class Solution {
        public long solution(int n, int[] times) {
            long answer = Long.MAX_VALUE;

            long max = 1000000000L * 1000000000L;
            long min = 0L;
            long mid;


            while (min <= max) {
                mid = (max+min) / 2;

                if (isPossible(n, times, mid)) {
                    answer = Math.min(answer, mid);
                    max = mid - 1;
                }else{
                    min = (mid + 1);
                }
            }


            return answer;
        }

        private boolean isPossible(int n, int[] times, long testTime){
            long cnt = 0;

            for (int time : times) {
                cnt += (testTime / time);
            }

            if(cnt >= n){
                return true;
            }else{
                return false;
            }
        }

    }
}
