import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int D = Integer.parseInt(st.nextToken());
        PriorityQueue<HighWay> queue = new PriorityQueue<>();
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            queue.add(new HighWay(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        int [] A = new int[D+1];
        for(int i = 0; i < D+1; i++) A[i] = i;
        for(int i = 0; i < N; i++){
            HighWay temp = queue.poll();
            if(temp.e > D) continue;
            if(A[temp.e] > A[temp.s]+temp.l){
                A[temp.e] = A[temp.s]+temp.l;
                for(int j = temp.e+1; j < D+1; j++){
                    A[j] = Math.min(A[j], A[temp.e]+j-temp.e);
                }
            }
        }
        System.out.println(A[D]);
    }

    public static class HighWay implements Comparable<HighWay>{
        int s;
        int e;
        int l;

        public HighWay(int s, int e, int l){
            this.s = s;
            this.e = e;
            this.l = l;
        }

        @Override
        public int compareTo(HighWay o){
            if(this.s == o.s) return this.e-o.e;
            return this.s-o.s;
        }
    }
}