package week21;

public class Ned {
    static class Solution {
        int answer = 100;
        public int solution(String begin, String target, String[] words) {
            int[] ch = new int[words.length];

            DFS(begin, target, words, 0,begin, ch);
            if(answer>words.length) {
                return 0;
            }
            return answer;
        }
        void DFS(String begin, String target, String[] words, int cnt, String cur,int[] ch) {
            if(cur.equals(target)) {
                answer=Math.min(answer, cnt);
            }else {
                for(int i=0; i<words.length; i++) {
                    if(words[i].equals(cur)) {
                        continue;
                    }
                    if(isDiffOneChar(cur, words[i]) && ch[i]==0) {
                        ch[i]=1;
                        DFS(begin, target, words, cnt+1, words[i], ch);
                        ch[i]=0;
                    }
                }
            }
        }

        boolean isDiffOneChar(String a, String b) {
            int cnt = 0;

            for(int i=0; i< a.length(); i++) {
                if(a.charAt(i)!=b.charAt(i)) {
                    cnt++;
                }
            }
            if(cnt!=1) return false;
            return true;
        }
    }
}
