package week25;


/**
 문제설명
 - 2진트리 모양 초원이 있다. 각 노드에 늑대 or 양
 - 루트 노드에서 출발하여 각 노드를 돌아다니며 양을 모은다.
 - 모은 양의 수보다 늑대의 수가 같거나 더 많이지면 바로 모든 양을 잡아먹는다.
 - 중간에 양이 늑대에게 잡아먹히지 않도록 하면서 최대한 많은 수의 양을 모아서 다시 루트 노드로 돌아오려 합니다.
 */
import java.util.*;
public class Henry {
    static class Solution {
        int N;
        Node[] nodeArray;
        boolean[] ch; // 방문 여부 체크 배열
        boolean[] avail; // 방문 가능 여부 체크 배열
        int maxSheepCnt = Integer.MIN_VALUE; // 최대 양의 개수
        int wolfCnt = 0; int sheepCnt = 0;
        public int solution(int[] info, int[][] edges) {
            int answer = 0;

            // 트리 구성
            N = info.length;
            nodeArray = new Node[info.length]; ch = new boolean[info.length];
            avail = new boolean[info.length];
            for(int i=0; i<info.length; i++){
                nodeArray[i] = new Node(info[i]);
            }
            for(int[] edge : edges){
                nodeArray[edge[0]].addChild(edge[1]);
            }

            // DFS 탐색
            ch[0] = true; avail[0] = true;
            DFS(0);

            return maxSheepCnt;
        }

        void DFS(int nodeNumber){
            // 양, 늑대의 개수 업데이트
            if(nodeArray[nodeNumber].value == 0){ // 양
                sheepCnt++;
                maxSheepCnt = Math.max(maxSheepCnt, sheepCnt);
            }else{ // 늑대
                wolfCnt++;
            }


            if(sheepCnt <= wolfCnt){
                // 함수 반환 전 양, 늑대의 개수 원복
                if(nodeArray[nodeNumber].value == 0){ // 양
                    sheepCnt--;
                }else{ // 늑대
                    wolfCnt--;
                }
                return;
            }

            // 자식 배열은 방문 가능하다
            for(int child : nodeArray[nodeNumber].child){
                avail[child] = true;
            }

            // 방문이 가능하고, 방문을 하지 않았던 노드 탐색
            for(int i=0; i<N; i++){
                if(avail[i] && !ch[i]){
                    ch[i] = true;
                    DFS(i);
                    ch[i] = false;
                }
            }

            // 자식 배열은 방문 가능 원복
            for(int child : nodeArray[nodeNumber].child){
                avail[child] = false;
            }

            // 함수 반환 전 양, 늑대의 개수 원복
            if(nodeArray[nodeNumber].value == 0){ // 양
                sheepCnt--;
            }else{ // 늑대
                wolfCnt--;
            }
        }


        static class Node{
            int value; List<Integer> child;
            Node(int value){
                this.value = value; this.child = new ArrayList<>();
            }
            void addChild(int childNumber){
                child.add(childNumber);
            }
        }
    }
}
