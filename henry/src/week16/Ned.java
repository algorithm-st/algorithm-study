package week16;

public class Ned {
    static class Solution {

        int cnt = 0;
        String[] strs = {"A", "E", "I", "O", "U"};
        String target = "";
        int answer = -1;

        public int solution(String word) {
            target = word;
            DFS("", 0);
            return answer;
        }

        void DFS(String curStr, int index){
            if(answer != -1){
                return;
            }
            if(curStr.length() == 6){
                return;
            }

            if(curStr.equals(target)){
                answer = cnt;
                return;
            }

            cnt++;
            for(int i=0; i<5; i++){
                DFS(curStr+strs[i], i);
            }
        }
    }
}
