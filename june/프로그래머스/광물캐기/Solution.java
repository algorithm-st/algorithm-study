package june.프로그래머스.광물캐기;

import java.util.ArrayList;
import java.util.List;

public class Solution {

    public static void main(String[] args) {
        int solution = solution(new int[]{1, 3, 2},
                new String[]{"diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"});
        int solution1 = solution(new int[]{0, 1, 1},
                new String[]{"diamond", "diamond", "diamond", "diamond", "diamond", "iron", "iron", "iron", "iron", "iron",
                        "diamond"});
        System.out.println(solution);
        System.out.println(solution1);
    }

    public static int solution(int[] picks, String[] minerals) {
        int answer = 0;
        int count = 0;
        for (int pick : picks) {
            count += pick;
        }
        int[][] piro = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                piro[i][j] = 1;
            }
        }
        piro[1][0] = 5;
        piro[2][1] = 5;
        piro[2][0] = 25;
        List<Integer[]> list = new ArrayList<>();
        int index = (int) Math.ceil((double) minerals.length / 5);
        for (int i = 0; i < index; i++) {
            Integer[] temp = new Integer[]{0, 0, 0};
            for (int j = i * 5; j < Math.min((i+1)*5, minerals.length); j++) {
                switch (minerals[j]) {
                    case "diamond":
                        temp[0]++;
                        break;
                    case "iron":
                        temp[1]++;
                        break;
                    case "stone":
                        temp[2]++;
                        break;
                }
            }
            list.add(temp);
        }

        if (list.size() > count) {
            list = list.subList(0, count);
        }
        list.sort((o1, o2) -> {
            if(o1[0] == o2[0]) {
                return Integer.compare(o2[1], o1[1]);
            }
            else {
                return Integer.compare(o2[0], o1[0]);
            }
        });
        for (Integer[] kok : list) {
            if (picks[0] > 0) {
                picks[0]--;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < kok[i]; j++) {
                        answer += piro[0][i];
                    }
                }

            } else if (picks[1] > 0) {
                picks[1]--;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < kok[i]; j++) {
                        answer += piro[1][i];
                    }
                }
            } else if (picks[2] > 0) {
                picks[2]--;
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < kok[i]; j++) {
                        answer += piro[2][i];
                    }
                }
            }
        }


        return answer;
    }
}