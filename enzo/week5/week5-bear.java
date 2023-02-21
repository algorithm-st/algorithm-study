
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Bear {

    static class Pair{
        String name;
        int level;

        public Pair(String name, int level) {
            this.name = name;
            this.level = level;
        }
    }
    public static void getAnswer() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int p = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        List<List<Pair>> list = new ArrayList<>();
        for(int i=0;i<p;i++){
            st = new StringTokenizer(br.readLine());
            int curLevel = Integer.parseInt(st.nextToken());
            String curNickname = st.nextToken();
            boolean flag = false;
            for(List<Pair> tempPair : list){
                if(tempPair.size()>=m) continue;
                if(Math.abs(tempPair.get(0).level-curLevel)<=10){
                    tempPair.add(new Pair(curNickname,curLevel));
                    flag=  true;
                    break;
                }
            }
            if(!flag){
                List<Pair> pair = new ArrayList<>();
                pair.add(new Pair(curNickname,curLevel));
                list.add(pair);
            }
        }
        StringBuffer sb = new StringBuffer();
        for(List<Pair> curP : list){
            if(curP.size() == m) sb.append("Started!");
            else sb.append("Waiting!");
            sb.append("\n");
            Collections.sort(curP,(o1,o2)->{
                return o1.name.compareTo(o2.name);
            });
            for(Pair tp : curP){
                sb.append(tp.level).append(" ").append(tp.name).append("\n");
            }
        }
        System.out.print(sb.toString());
    }
    public static void main(String args[]) throws Exception{
        getAnswer();
    }
}