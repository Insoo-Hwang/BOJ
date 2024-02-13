import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        List<Rail> list = new ArrayList<>();
        for(int i = 0; i < n; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int s = Math.min(a, b);
            int e = Math.max(a, b);
            list.add(new Rail(s, e));
        }
        Collections.sort(list);
        int d = Integer.parseInt(br.readLine());
        int max = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for(int i = 0; i < list.size(); i++){
            Rail temp = list.get(i);
            queue.add(temp.s);
            while(!queue.isEmpty() && queue.peek() < temp.e-d) queue.poll();
            max = Math.max(max, queue.size());
        }
        System.out.print(max);
    }

    static class Rail implements Comparable<Rail>{
        int s;
        int e;

        public Rail(int s, int e){
            this.s = s;
            this.e = e;
        }

        @Override
        public int compareTo(Rail o){
            if(this.e == o.e) return this.s-o.s;
            else return this.e-o.e;
        }
    }
}