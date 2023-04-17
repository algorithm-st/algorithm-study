package june.프로그래머스.후보키;

import java.util.*;

public class Solution {
    public int height;
    public int width;
    public int[] arr;
    public String[][] re;
    public List<List<Integer>> answer = new ArrayList<>();

    public int solution(String[][] relation) {
        height = relation.length;
        width = relation[0].length;
        arr = new int[width];
        re = relation;
        for(int i = 1; i<Math.pow(2, width); i++){
            check(String.format("%0" + width + "d", Integer.parseInt(Integer.toBinaryString(i))));
        }
        return answer.size();
    }

    public void check(String combi){
        List<Integer> indexs = new ArrayList<>();
        for(int i = 0; i < width; i++){
            char c = combi.charAt(i);
            if(c == '1'){
                indexs.add(i);
            }
        }
        Set<String> set = new HashSet();
        for(int i =0; i<height; i++){
            String temp = "";
            for(int j = 0; j<indexs.size(); j++){
                temp += re[i][indexs.get(j)];
            }
            set.add(temp);
        }
        if(set.size() == height){
            for(List<Integer> ans : answer){
                if(indexs.containsAll(ans)){
                    return;
                }
            }
            answer.add(indexs);
        }
    }
}