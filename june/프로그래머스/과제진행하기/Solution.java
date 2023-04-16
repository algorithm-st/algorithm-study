package june.프로그래머스.과제진행하기;
import java.util.*;

public class Solution {
    public String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];
        List<Plan> planList = new ArrayList<>();
        for(String[] planData : plans){
            String[] t = planData[1].split(":");
            planList.add(new Plan(
                    planData[0],
                    Integer.parseInt(t[0])*60 + Integer.parseInt(t[1]),
                    Integer.parseInt(planData[2])
            ));
        }
        Collections.sort(planList, (o1,o2) -> o1.start - o2.start);
        Cal cal = new Cal(planList);
        cal.run();
        int i = 0;
        for(String name : cal.ans){
            answer[i++] = name;
        }
        return answer;
    }
}
class Cal{
    List<Plan> planList;
    Stack<Plan> stack = new Stack();
    List<String> ans = new ArrayList();
    int now = 0;

    public Cal(List<Plan> planList){
        this.planList = planList;
    }
    public void run(){
        Plan start = planList.get(0);
        stack.push(start);
        now = start.start;
        for(int i = 1; i < planList.size(); i++){

            Plan plan = planList.get(i);
            // System.out.println(plan.name + " : " + plan.start + " : " + plan.duration);
            if(stack.isEmpty()) {
                stack.push(plan);
                continue;
            }

            // System.out.println("차이 : " + (plan.start - now));
            System.out.println(now);
            while(!stack.isEmpty() && plan.start - now >= stack.peek().duration){
                // 이전과제 끝
                ans.add(stack.peek().name);
                now += stack.peek().duration;
                System.out.println(now);
                stack.pop();
            }
            // 이전과제가 하다가 새로운게 들어옴


            if(!stack.isEmpty()) stack.peek().duration -= (plan.start - now);
            now += (plan.start - now);
            stack.push(plan);
        }
        while(!stack.isEmpty()){
            Plan plan = stack.pop();
            ans.add(plan.name);
        }
    }
}
class Plan{
    String name;
    int start;
    int duration;

    public Plan(String name, int start, int duration){
        this.name = name;
        this.start = start;
        this.duration = duration;
    }
}
