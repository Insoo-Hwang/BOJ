import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        List<C> list = new LinkedList<>();
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            list.add(new C(s, e));
        }
        Collections.sort(list); //종료 시간이 늦은것 부터 같다면 시작 시간이 이른것 부터

        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder()); //역순으로 정렬해야 시작 시간이 늦은 것부터 배치됨
        for(C c : list){
            int end = c.e;
            int start = c.s;
            if(!queue.isEmpty() && queue.peek() >= end){ //빈 강의실 중 시작 시간이 늦은 것보다 끝나는 시간이 빠르면 
                queue.remove(); //이미 들어 있는 항목 제거
            }
            queue.add(start); //새로운 강의실 or 기존 강의실의 시작 시간 갱신
        }
        System.out.println(queue.size());
    }
    static class C implements Comparable<C>{
        int s;
        int e;
        public C(int s, int e){
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(C o) {
            if(this.e == o.e){
                return this.s-o.s;
            }
            return o.e-this.e;
        }
    }
}
