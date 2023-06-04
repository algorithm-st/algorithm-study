public class Henry {
    public int solution(int[] a) {
        int answer = 0;
        int leftMin = Integer.MAX_VALUE;
        int rightMin = Integer.MAX_VALUE;
        int [] countArr = new int[a.length];
        for(int i=0;i<a.length;i++){
            if(leftMin < a[i]) {
                countArr[i] +=1;
                continue;
            }
            leftMin = a[i];
        }
        for(int i=a.length-1;i>=0;i--){
            if(a[i] > rightMin) {
                countArr[i] +=1;
                continue;
            }
            rightMin = a[i];
        }
        for(int i=0;i<a.length;i++){
            if(countArr[i] <2) answer++;
        }
        return answer;
    }
}
