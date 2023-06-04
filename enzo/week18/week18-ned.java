import java.util.LinkedList;
import java.util.List;

public class Ned {
    public int solution(int cacheSize, String[] cities) {
        int answer = 0;
        List<String> queue = new LinkedList<>();
        for(int i = 0; i<cities.length;i++){
            cities[i] = cities[i].toUpperCase();
        }
        for(String city : cities){
            int cacheIndex = getCacheIndex(queue, city);
            if(cacheIndex == queue.size()){
                answer += doMiss(cacheSize, queue, city);
            }
            else{
                answer += doHit(cacheIndex, queue, city);
            }
        }
        return answer;
    }
    public int getCacheIndex(List<String> queue , String city){
        int count = 0;
        for(String c : queue){
            if(c.equals(city)) break;
            count++;
        }
        return count;
    }
    public int doMiss(int cacheSize, List<String> queue , String city){
        if(cacheSize == 0) return 5;
        if(queue.size()==cacheSize){
            queue.remove(0);
        }
        queue.add(city);
        return 5;
    }
    public int doHit(int index, List<String> queue, String city){
        queue.remove(index);
        queue.add(city);
        return 1;
    }
}
