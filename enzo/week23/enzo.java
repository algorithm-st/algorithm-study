class Solution {
    public int solution(int[] a) {
        int answer = 0;
        Map<Integer,List<Integer>> numMap = new HashMap<>();
        for(int i=0;i<a.length;i++){
            if(!numMap.containsKey(a[i])){
                List<Integer> list = new ArrayList<>();
                numMap.put(a[i], list);
            }
            numMap.get(a[i]).add(i);
        }

        int maxCount = 0;
        for(int i : numMap.keySet()){
            List<Integer> indexs = numMap.get(i);
            if(indexs.size() < maxCount/2) continue;
            maxCount = Math.max(maxCount, getCount(a, i, indexs));
        }
        return maxCount;
    }
    private int getCount(int[]a, int val, List<Integer> array){
        int preCheckIndex = -1;
        int count = 0;
        for(int i=0;i<array.size();i++){
            int curIndex = array.get(i);
            int pre = curIndex-1;
            int next = curIndex+1;
            int checkCount = 0;
            if(pre >=0){
                if(a[pre] != val && preCheckIndex != pre){
                    count +=2;
                    continue;
                }
            }
            if(next<a.length){
                if(a[next] != val){
                    count +=2;
                    preCheckIndex = next;
                }
            }
        }
        return count;
    }
}