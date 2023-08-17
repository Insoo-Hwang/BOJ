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

        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        queue.add(A[0].s);
        int max = 1;
        for(int i = 1; i < N; i++){
            if(!queue.isEmpty() && A[i].e <= queue.peek()){
                max = Math.max(max, queue.size());
                while(!queue.isEmpty() && A[i].e <= queue.peek()){
                    queue.remove();
                }
            }
            queue.add(A[i].s);
        }
        max = Math.max(max, queue.size());
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
        public int compareTo(Line o) {
            if(this.e == o.e){
                return this.s - o.s;
            }
            return o.e - this.e;
        }
    }
}