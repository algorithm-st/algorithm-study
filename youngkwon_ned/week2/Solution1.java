package youngkwon_ned.week2;

/**
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/87946?language=java">피로도</a>
 */
public class Solution1 {

    public static void main(String[] args) {
        Solution1 s1 = new Solution1();
        int[][] arr2 = {{80, 20}, {50, 40}, {30, 10}};
        System.out.println(s1.solution(80, arr2));
    }

    private boolean[] visit;
    private int answer = 0;

    public int solution(int k, int[][] dungeons) {
        visit = new boolean[dungeons.length];
        dfs(0, k, dungeons);
        return answer;
    }

    private void dfs(int depth, int k, int[][] dungeons) {
        for (int i = 0; i < dungeons.length; i++) {
            if (!visit[i] && dungeons[i][0] <= k) {
                visit[i] = true;
                dfs(depth + 1, k - dungeons[i][1], dungeons);
                visit[i] = false;
            }

            answer = Math.max(answer, depth);
        }
    }

}
