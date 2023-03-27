import java.util.*;
public class June {
    public int[] solution(String[] genres, int[] plays) {
        Map<String,Integer> map = new HashMap<>();
        int count = 0;
        for(String genre : genres){
            if(map.containsKey(genre))continue;
            map.put(genre,count++);
        }
        Genre[] list = new Genre[map.size()];
        for(int i=0;i<map.size();i++){
            list[i] = new Genre();
        }
        for(int i=0;i<plays.length;i++){
            int curIndex = map.get(genres[i]);
            list[curIndex].total += plays[i];
            list[curIndex].queue.add(new int[]{i,plays[i]});
        }
        Arrays.sort(list,(o1,o2)->{
            return o2.total - o1.total;
        });
        List<Integer> answer = new ArrayList<>();
        for(int i=0;i<map.size();i++){
            count = 0;
            while(!list[i].queue.isEmpty()){
                if(count == 2) break;
                int [] cur = list[i].queue.poll();
                count++;
                answer.add(cur[0]);
            }
        }
        return answer.stream().mapToInt(i->i).toArray();
    }
    class Genre {
        int total;
        Queue<int[]> queue;
        public Genre(){
            this.total = 0;
            this.queue = new PriorityQueue<>((o1,o2)->{
                if(o1[1]  == o2[1]) return o1[0] - o2[0];
                return o2[1] - o1[1];
            });
        }
    }
}
