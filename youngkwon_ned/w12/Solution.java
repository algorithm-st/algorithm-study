package youngkwon_ned.w12;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <a href="https://school.programmers.co.kr/learn/courses/30/lessons/42890">후보키</a>
 */
public class Solution {
    public static void main(String[] args) {
        System.out.println();
        String[][] relation = {{"100","ryan","music","2"},{"200","apeach","math","2"},{"300","tube","computer","3"},{"400","con","computer","4"},{"500","muzi","music","3"},{"600","apeach","music","2"}};
        Solution s = new Solution();
        int solution = s.solution(relation);
        System.out.println("solution = " + solution);
    }
    private String[][] database;
    private int rowSize;
    private int colSize;
    private List<Integer> candidateSets;

    public int solution(String[][] relation) {
        rowSize = relation.length;
        colSize = relation[0].length;
        database = relation;
        candidateSets = new ArrayList<>();
        // 0000 0001 0010 0011
        // 모든 경우의 수 2^N (1을 << colSize 만큼 시프트)
        // 8개 이하 -> 1,2,3,4,5,6,7,8 -> 2^n-1
        for (int i = 0; i < (1 << colSize); i++) {
            if (!isUnique(i)) {
                continue;
            }

            if (!isMinimum(i)) {
                continue;
            }

            candidateSets.add(i);
        }

        return candidateSets.size();
    }

    private boolean isUnique(int set) {
        String[] keys = makeKeys(set);

        // 키 중복 검사
        Set<String> keySet = new HashSet<>();
        for (String key : keys) {
            if (keySet.contains(key)) {
                return false;
            }
            keySet.add(key);
        }

        return true;
    }

    private String[] makeKeys(int set) {
        List<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < colSize; i++) {
            // 1의 위치 찾기 1011 -> 0,1,3
            if (((set >> i) & 1) == 1) {
                indexes.add(i);
            }
        }

        String[] keys = new String[rowSize];
        for (int i = 0; i < rowSize; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < indexes.size(); j++) {
                // 모든 로우의 값을 키로 만들기
                stringBuilder.append(database[i][indexes.get(j)]);
            }
            keys[i] = stringBuilder.toString();
        }

        return keys;
    }

    private boolean isMinimum(int set) {
        for (int candidateSet : candidateSets) {
            // 1011 & 1001 -> 1011 이라서 이미 후보키에 포함됨, 1011 & 0111 => 0111 -> 포함되지 않음 최소성 만족
            if ((candidateSet & set) == candidateSet) {
                return false;
            }
        }

        return true;
    }
}
