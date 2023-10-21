import java.util.*;
class Solution {
    public int solution(int[] scoville, int K) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(int i : scoville){
            queue.add(i);
        }
        int answer = 0;
        while(queue.peek() < K){
            if(queue.size() == 1){
                answer = -1;
                break;
            }
            queue.add(queue.poll()+queue.poll()*2);
            answer++;
        }
        return answer;
    }
}