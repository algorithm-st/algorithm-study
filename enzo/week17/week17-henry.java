public class Henry {

    public String solution(String play_time, String adv_time, String[] logs) {
        String answer = "";
        int playTime = timeToInt(play_time);
        int advTime = timeToInt(adv_time);
        int [] playTimeRangeArr = new int[playTime+1];
        long [] prefixSum = new long[playTime+1];
        for(String log : logs){
            String [] sArr = log.split("-");
            playTimeRangeArr[timeToInt(sArr[0])] += 1;
            playTimeRangeArr[timeToInt(sArr[1])] -= 1;
        }

        for(int i=1;i<=playTime;i++){
            playTimeRangeArr[i] += playTimeRangeArr[i-1];
            prefixSum[i] += prefixSum[i-1] + playTimeRangeArr[i];
        }
        int maxIndex = 0;
        long maxTotal = 0;
        for(int i=0;i<=(playTime-advTime)+1;i++){
            int pre = i-1;
            int cur = i+advTime-1;
            long preSum = (pre == -1) ? 0 : prefixSum[pre];
            long curSum = prefixSum[cur];
            if(maxTotal < (curSum-preSum)){
                maxTotal = curSum - preSum;
                maxIndex = i;
            }
        }
        return timeToString(maxIndex);
    }
    public int timeToInt(String time){
        String [] splitArr = time.split(":");
        int hour = Integer.parseInt(splitArr[0]);
        int min = Integer.parseInt(splitArr[1]);
        int sec = Integer.parseInt(splitArr[2]);
        return hour * 60 * 60 + min * 60 + sec;
    }
    public String timeToString(int time){
        return String.format("%02d:%02d:%02d",time/3600, (time%3600)/60, time%60);
    }
}