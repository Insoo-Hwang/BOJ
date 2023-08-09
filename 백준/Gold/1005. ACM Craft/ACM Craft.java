import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        while(T-->0){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int [] A = new int[N+1];
            int [] D = new int[N+1];
            for(int i = 1; i < N+1; i++){
                D[i] = -1;
            }
            ArrayList<Integer> [] B = new ArrayList[N+1];
            boolean [] C = new boolean[N+1];
            for(int i = 1; i < N+1; i++){
                B[i] = new ArrayList<>();
            }
            st = new StringTokenizer(br.readLine());
            for(int i = 1; i < N+1; i++){
                A[i] = Integer.parseInt(st.nextToken());
            }
            for(int i = 0; i < K; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                B[b].add(a);
            }
            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());

            int count = 0;
            while(count < N){
                for(int i = 1; i < N+1; i++){
                    if(D[i] != -1){
                        continue;
                    }
                    boolean check = true;
                    int max = 0;
                    for(int j : B[i]){
                        if(!C[j]){
                            check = false;
                            break;
                        }
                        max = Math.max(max, D[j]);
                    }
                    if(check){
                        D[i] = max + A[i];
                        C[i] = true;
                        count++;
                    }
                }
            }
            System.out.println(D[W]);
        }
    }
}