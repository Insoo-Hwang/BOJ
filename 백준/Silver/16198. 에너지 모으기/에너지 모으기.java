import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int [] A;
    static boolean [] B;
    static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        A = new int[N];
        B = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }
        DFS(0, 0);
        System.out.println(max);
    }

    static void DFS(int d, int num){
        if(d == N-2){
            max = Math.max(max, num);
            return;
        }

        for(int i = 1; i < N-1; i++){
            if(B[i]) continue;
            B[i] = true;
            DFS(d+1, num+findL(i)*findR(i));
            B[i] = false;
        }
    }

    static int findL(int n){
        for(int i = n-1; i > -1; i--){
            if(!B[i]) return A[i];
        }
        return 0;
    }

    static int findR(int n){
        for(int i = n+1; i < N; i++){
            if(!B[i]) return A[i];
        }
        return 0;
    }
}