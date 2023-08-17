import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        Line [] A = new Line[N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            A[i] = new Line(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(A);

        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder()); //내림차순 우선순위 큐
        queue.add(A[0].s); //가장 첫 선분의 s좌표 입력
        int max = 1;
        for(int i = 1; i < N; i++){
            if(!queue.isEmpty() && A[i].e <= queue.peek()){ //현재 선분의 e좌표가 queue의 값보다 작다는 것은 겹치지 않는다는 것
                max = Math.max(max, queue.size()); //현재 queue에 있는 모든 값(이전에 그었던 선분 중 겹치는 것들)을 업데이트
                while(!queue.isEmpty() && A[i].e <= queue.peek()){ //현재 선분과 겹치지 않는 선분들 제거(queue에 있는 값이 e좌표보다 작다는 건 겹친다는 뜻)
                    queue.remove();
                }
            }
            queue.add(A[i].s); //현재 선분의 s좌표를 queue 삽입
        }
        max = Math.max(max, queue.size()); //마지막으로 queue에 남아있는 값들 업데이트
        System.out.println(max);
    }
    static class Line implements Comparable<Line>{
        int s;
        int e;
        public Line(int s, int e){
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Line o) { //e좌표 기준으로 내림차순, s좌표 기준으로 오름차준
            if(this.e == o.e){
                return this.s - o.s;
            }
            return o.e - this.e;
        }
    }
}
