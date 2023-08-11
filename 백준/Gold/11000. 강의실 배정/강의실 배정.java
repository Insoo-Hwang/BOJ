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
        Collections.sort(list);

        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        for(C c : list){
            int end = c.e;
            int start = c.s;
            if(!queue.isEmpty() && queue.peek() >= end){
                queue.remove();
            }
            queue.add(start);
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