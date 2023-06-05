package week18;
import java.util.*;
public class Ned {
    /**
     도시 이름 - 대소문자 구분을 하지 않는다.
     */
    static class Solution {

        public int solution(int cacheSize, String[] cities) {
            int answer = 0;
            Cache cache = new Cache(cacheSize);
            for(String city : cities){
                answer += cache.find(city.toLowerCase());
            }
            return answer;
        }

        static class Cache{
            int cacheSize;
            Queue<String> cache;

            Cache(int cacheSize){
                this.cacheSize = cacheSize;
                this.cache = new LinkedList<>();
            }

            int find(String city){
                if(cacheSize==0){ // 캐시 사이즈가 0일 겨웅 예외 처리
                    return 5;
                }

                int queueSize = cache.size();

                boolean flag = false;
                for(int i=0; i<queueSize; i++){
                    String front = cache.poll();
                    if(front.equals(city)){
                        flag = true; // cache hit
                        continue;
                    }else{
                        cache.offer(front);
                    }
                }
                if(flag){
                    cache.offer(city);
                    return 1;
                }

                // cache miss
                if(queueSize == cacheSize){
                    cache.poll();
                }
                cache.offer(city);
                return 5;
            }
        }
    }
}
