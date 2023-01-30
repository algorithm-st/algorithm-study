public class Main {

    public static void getAnswer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int answer = 0;
        for(int i=0;i<=N;i++){
            int curNum = i+getSplitSum(i);
            if(N == curNum){
                answer = i;
                break;
            }
        }
        System.out.println(answer);
    }

    private static int getSplitSum(int i) {
        int num = 0;
        while(i!=0){
            num += i%10;
            i = i/10;
        }
        return num;
    }
}