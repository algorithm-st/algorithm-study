package week3_dfs;

import java.util.*;

class Enzo {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];

        Map<String, Node> map = new HashMap<>();
        map.put("center", new Node("center", null));

        for(int i=0; i< enroll.length; i++){
            String employee = enroll[i];
            String ref = referral[i];

            if (ref.equals("-")) {
                map.put(employee, new Node(employee, map.get("center")));
            }else {
                map.put(employee, new Node(employee, map.get(ref)));
            }
        }

        for(int i=0; i< seller.length; i++){
            map.get(seller[i]).cal(amount[i] * 100);
        }

        for(int i=0; i<enroll.length; i++){
            answer[i] = map.get(enroll[i]).money;
        }
        return answer;
    }

    static class Node{
        String name;
        int money;
        Node parent;

        public Node(String name, Node parent) {
            this.name = name;
            this.parent = parent;
            this.money = 0;
        }

        public void cal(int money){
            if(money/10<1){
                this.money+=money;
            }else{
                if(this.parent==null){
                    this.money+=money;
                }else{
                    int divide = (int)money/10;
                    this.money += (money - divide);
                    this.parent.cal(divide);
                }
            }
        }
    }
}
