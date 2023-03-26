import java.util.*;

public class Ned {
    public int end;
    public Map<String,List<Node>> map;
    public boolean flag = false;
    public String [] answer;
    class Node{
        boolean check;
        String s;
        public Node(String s){
            this.check = false;
            this.s = s;
        }
    }
    public void dfs(String cur, int step, Deque<String> stack){
        if(flag) return;
        if(step == end){
            answer = stack.toArray(new String[0]);
            flag = true;
            return;
        }
        if(!map.containsKey(cur)) return;
        for(Node n : map.get(cur)){
            if(n.check) continue;
            n.check = true;
            stack.add(n.s);
            dfs(n.s,step+1,stack);
            stack.pollLast();
            n.check = false;
        }
    }
    public String[] solution(String[][] tickets) {
        end = tickets.length;
        map = new HashMap<>();
        for(int i=0;i<end;i++){
            if(map.containsKey(tickets[i][0])){
                map.get(tickets[i][0]).add(new Node(tickets[i][1]));
            }
            else{
                List<Node> arr = new ArrayList<>();
                map.put(tickets[i][0],arr);
                map.get(tickets[i][0]).add(new Node(tickets[i][1]));
            }
        }
        for(String s : map.keySet()){
            Collections.sort(map.get(s),(o1,o2)->{
                return o1.s.compareTo(o2.s);
            });
        }
        Deque<String> stack = new ArrayDeque<>();
        stack.add("ICN");
        dfs("ICN",0,stack);
        return answer;
    }
}
