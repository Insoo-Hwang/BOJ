import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int [] A;
    static boolean [] alive;
    static int [][] R;
    static int E;
    static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        A = new int[N];
        alive = new boolean[N];
        Arrays.fill(alive, true);
        R = new int[N][N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                R[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        E = Integer.parseInt(br.readLine());

        DFS(0, N);
        System.out.println(max);
    }

    static void DFS(int c, int p){
        if(!alive[E] || p == 1){
            max = Math.max(max, c);
            return;
        }

        if(p%2 == 0){
            for(int i = 0; i < N; i++){
                if(!alive[i] || i == E) continue;
                change(i, 1);
                alive[i] = false;
                DFS(c+1, p-1);
                alive[i] = true;
                change(i, -1);
            }
        }
        else{
            int max = 0;
            int idx = N-1;
            for(int i = 0; i < N; i++) {
                if(alive[i] && max < A[i]) {
                    max = A[i];
                    idx = i;
                }else if(alive[i] && max == A[i]) {
                    max = A[i];
                    idx = Math.min(i, idx);
                }
            }
            alive[idx] = false;
            DFS(c, p-1);
            alive[idx] = true;
        }
    }

    static void change(int d, int t){
        for(int i = 0; i < N; i++){
            if(!alive[i]) continue;
            A[i]+=R[d][i]*t;
        }
    }
}