package june.프로그래머스.광고삽입;

public class Solution2 {
    public String solution(String play_time, String adv_time, String[] logs) {
        int playTime = convert(play_time);
        int advTime = convert(adv_time);
        int[] playSum = new int[100*60*60];
        for(String log : logs){
            String[] se = log.split("-");
            int start = convert(se[0]);
            int end = convert(se[1]);
            for(int i = start; i<end; i++){
                playSum[i]++;
            }
        }

        long sum = 0;
        for(int i =0; i<advTime; i++){
            sum += playSum[i];
        }
        long max = sum;
        int index = 0;
        for(int i = advTime; i < playTime; i++){
            sum = sum - playSum[i - advTime] + playSum[i];
            if(max < sum){
                max = sum;
                index = i - advTime + 1;
            }
        }

        return String.format("%02d:%02d:%02d", index/3600, (index/60)%60, index%60);
    }

    public int convert(String time){
        String[] hms = time.split(":");

        return Integer.parseInt(hms[0])*60*60 + Integer.parseInt(hms[1])*60 + Integer.parseInt(hms[2]);
    }
}