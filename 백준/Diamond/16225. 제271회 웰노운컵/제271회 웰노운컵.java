import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int [] A = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken());
        PriorityQueue<Problem> queue = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) queue.add(new Problem(A[i], Integer.parseInt(st.nextToken())));
        long score = queue.poll().A;
        PriorityQueue<Integer> select = new PriorityQueue<>(Collections.reverseOrder());
        for(int i = 1; i < N/2; i++){
            select.add(queue.poll().A);
            select.add(queue.poll().A);
            score+=select.poll();
        }
        System.out.println(score);
    }

    static class Problem implements Comparable<Problem>{
        int A;
        int B;

        public Problem(int A, int B){
            this.A = A;
            this.B = B;
        }

        @Override
        public int compareTo(Problem o){
            return this.B-o.B;
        }
    }
}