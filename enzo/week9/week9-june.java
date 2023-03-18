public class June {

    public static void getAnswer() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int [] restArray = new int[N];
        if(N>0){
            restArray = Arrays.stream(br.readLine().split(" ")).mapToInt(s->Integer.parseInt(s)).toArray();
            Arrays.sort(restArray);
        }
        int start = 1;
        int end = L;

        while(start<end){
            int mid  = (start+end)/2;
            if(uppderRange(restArray,N,M,L,mid)){
                start = mid +1;
            }
            else end = mid;
        }
        System.out.println(start);
    }

    private static boolean uppderRange(int[] restArray, int n, int m, int l, int mid) {
        int start = 0;
        int count = 0;
        int gap = 0;
        // count가 많은 것은 mid가 작아 휴게소를 많이 지은것
        for(int i=0;i<n;i++){
            gap = restArray[i] - start-1;
            count += gap/mid;
            start = restArray[i];
        }
        gap = l - start-1;
        count += gap/mid;
        if(count>m) return true;
        return false;
    }

    public static void main(String [] args) throws IOException {
        getAnswer();
    }
}
