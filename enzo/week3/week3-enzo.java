import java.util.*;
class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int [] heap = new int[enroll.length+1];
        int [] earnMoney = new int[enroll.length+1];
        Map<String,Integer> enrollMap = getEnrollMap(enroll);
        for(int i=0;i<referral.length;i++){
            int referralIndex = 0;
            if(referral[i].equals("-")==false){
                referralIndex = enrollMap.get(referral[i]);
            }
            heap[i+1] = referralIndex;
        }

        for(int i=0;i<seller.length;i++){
            int money = 100*amount[i];
            int currentIndex = enrollMap.get(seller[i]);
            while(currentIndex!=0){
                earnMoney[currentIndex] += money - (money/10);
                money = money/10;
                currentIndex = heap[currentIndex];
            }
        }

        int [] answer = new int[enroll.length];
        for(int i=0;i<answer.length;i++){
            answer[i] = earnMoney[i+1];
        }
        return answer;
    }
    public Map<String,Integer> getEnrollMap(String[] enroll){
        Map<String,Integer> map = new HashMap<>();
        for(int i = 0;i<enroll.length;i++){
            map.put(enroll[i],i+1);
        }
        return map;
    }
}