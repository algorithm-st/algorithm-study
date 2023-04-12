package week12;

import java.util.*;
public class Ned {
    static boolean[] ch;
    static int answerCnt;
    static List<String> ansewrIndexStringList = new LinkedList<>();

    public int solution(String[][] relation) {
        int answer = 0;
        answerCnt = 0;
        ch = new boolean[relation[0].length];

        for(int i=1; i<=relation[0].length; i++){
            dfs(0, 0, i, relation);
        }

        answer = answerCnt;
        return answer;
    }

    void dfs(int idx, int cnt, int limit, String[][] relation){
        if(cnt == limit){
            Set<String> set = new HashSet<>();
            for(int i=0; i<relation.length; i++){
                String str = "";
                for(int j=0; j<relation[0].length; j++){
                    if(ch[j]){
                        str+=relation[i][j];
                    }
                }
                set.add(str);
            }

            // 유일성 체크
            if(set.size()==relation.length){
                String indexCombString = "";
                for(int i=0; i<relation[0].length; i++){
                    if(ch[i]){
                        indexCombString += i;
                    }
                }

                // 최소성 체크
                for(String answerIndexString : ansewrIndexStringList){
                    boolean flag = false;
                    int indexSize = answerIndexString.length();
                    for(int i=0; i<indexSize; i++){
                        if(!indexCombString.contains(answerIndexString.substring(i, i+1))){
                            flag = true;
                            break;
                        }
                    }
                    if(!flag){
                        return;
                    }
                }

                ansewrIndexStringList.add(indexCombString);
                answerCnt++;
            }
            return;
        }

        for(int i=idx; i<relation[0].length; i++){
            ch[i] = true;
            dfs(i+1, cnt+1, limit, relation);
            ch[i] = false;
        }
    }
}
