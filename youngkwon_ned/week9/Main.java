package youngkwon_ned.week9;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/43164">여행경로</a>
 */
public class Main {
    private static final List<String> result = new ArrayList<>();
    private boolean[] visited;

    public static void main(String[] args) {
        Main main = new Main();
        String[][] ticket = {{"ICN", "JFK"}, {"HND", "IAD"}, {"JFK", "HND"}};

        String[] solution = main.solution(ticket);
        System.out.println("solution = " + Arrays.toString(solution));
    }

    public String[] solution(String[][] tickets) {
        visited = new boolean[tickets.length];
        dfs(0, "ICN", "ICN", tickets);
        Collections.sort(result);
        return result.get(0).split(" ");
    }

    private void dfs(int count, String start, String route, String[][] tickets) {
        if (count == tickets.length) {
            result.add(route);
            return;
        }

        for (int i = 0; i < tickets.length; i++) {
            if (!visited[i] && start.equals(tickets[i][0])) {
                visited[i] = true;
                dfs(count+1, tickets[i][1], route + " " + tickets[i][1], tickets);
                visited[i] = false;
            }
        }
    }
}
