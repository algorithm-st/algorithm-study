package week12;

import java.util.*;

public class June {
    List<Plan> planList;
    Queue<Plan> readyPlanQueue = new LinkedList<>();
    Stack<Plan> holdPlanStack = new Stack<>();
    List<String> answerList = new ArrayList<>();

    public String[] solution(String[][] plans) {
        String[] answer = {};
        planList = new ArrayList<>();

        for(String[] plan : plans){
            Integer startMin = Plan.getStartMin(plan[1]);
            planList.add(new Plan(plan[0], startMin, Integer.valueOf(plan[2])));
        }
        planList.sort((a, b)->{
            return a.startMin - b.startMin;
        });
        planList.stream().forEach(plan ->{
            readyPlanQueue.add(plan);
        });

        int time = 0;
        Plan nowPlan = null;

        while(nowPlan!=null || !readyPlanQueue.isEmpty() || !holdPlanStack.isEmpty()){
            if(!readyPlanQueue.isEmpty() && readyPlanQueue.peek().startMin == time){
                if(nowPlan!=null){
                    holdPlanStack.add(nowPlan);
                }

                nowPlan = readyPlanQueue.poll();
            }

            if(nowPlan!=null){
                nowPlan.remainMin--;

                if(nowPlan.remainMin==0){
                    answerList.add(nowPlan.name);
                    nowPlan = null;

                    if(!holdPlanStack.isEmpty()){
                        nowPlan = holdPlanStack.pop();
                    }
                }
            }
            time++;
        }


        answer =  answerList.toArray(String[]::new);
        return answer;
    }

    static class Plan{
        String name;
        Integer startMin;
        Integer remainMin;

        static Integer getStartMin(String str){
            Integer hour = Integer.valueOf(str.split(":")[0]);
            Integer minute = Integer.valueOf(str.split(":")[1]);

            return hour*60 + minute;
        }

        Plan(String name, Integer startMin, Integer remainMin){
            this.name = name;
            this.startMin = startMin;
            this.remainMin = remainMin;
        }
    }
}
