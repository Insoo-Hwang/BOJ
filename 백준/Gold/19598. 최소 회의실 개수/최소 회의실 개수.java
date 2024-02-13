import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        List<Schedule> list = new ArrayList<>();
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            list.add(new Schedule(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        Collections.sort(list);
        int max = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(int i = 0; i < N; i++){
            Schedule now = list.get(i);
            int start = now.s;
            queue.add(now.e);
            int size = queue.size();
            for(int j = 0; j < size; j++){
                if(queue.peek() <= start) queue.poll();
                else break;
            }
            max = Math.max(max, queue.size());
        }
        System.out.print(max);
    }

    static class Schedule implements Comparable<Schedule>{
        int s;
        int e;

        public Schedule(int s, int e){
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Schedule o){
            if(this.s == o.s) return o.e-this.e;
            else return this.s-o.s;
        }
    }
}