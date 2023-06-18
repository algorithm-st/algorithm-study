public class Enzo {
    public long solution(int[] sequence) {
        long plusMaxSequenceTotal = 0;
        long plusCurrentTotal = 0;
        long minusMaxSequenceTotal = 0;
        long minusCurrentTotal = 0;
        int sign = 1;
        for(int i : sequence){
            long currentVal = plusCurrentTotal + (sign*i);
            long currentRVal = minusCurrentTotal + (-sign*i);
            if(currentVal > 0){
                plusCurrentTotal = currentVal;
                plusMaxSequenceTotal = Math.max(plusMaxSequenceTotal, plusCurrentTotal);
            }
            else{
                plusCurrentTotal = 0;
            }

            if(currentRVal > 0){
                minusCurrentTotal = currentRVal;
                minusMaxSequenceTotal = Math.max(minusMaxSequenceTotal, minusCurrentTotal);
            }
            else{
                minusCurrentTotal = 0;
            }

            sign = -sign;
        }
        return Math.max(plusMaxSequenceTotal,minusMaxSequenceTotal);
    }
}
