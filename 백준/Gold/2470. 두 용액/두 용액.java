import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        Liquid [] A = new Liquid[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            A[i] = new Liquid(Integer.parseInt(st.nextToken()));
        }
        Arrays.sort(A);

        long min = Integer.MAX_VALUE;
        int [] B = new int[2];
        for(int i = 0; i < N-1; i++){
            if(min > Math.abs(A[i].v + A[i+1].v)){
                min = Math.abs(A[i].v + A[i+1].v);
                B[0] = A[i].v;
                B[1] = A[i+1].v;
            }
        }
        Arrays.sort(B);
        for(int i : B){
            System.out.print(i + " ");
        }
    }
    static class Liquid implements Comparable<Liquid>{
        int v;
        int tv;
        public Liquid(int v){
            this.v = v;
            tv = Math.abs(v);
        }
        @Override
        public int compareTo(Liquid o) {
            if(this.tv == o.tv){
                return this.v - o.v;
            }
            return this.tv - o.tv;
        }
    }
}