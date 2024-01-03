import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int M;
    static int [] A;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(A);
        for(int i = 0; i < N; i++){
            DFS(0, i, ""+A[i]);
        }
    }

    static void DFS(int d, int n, String s){
        if(d == M-1){
            System.out.println(s);
            return;
        }

        for(int i = n; i < N; i++){
            DFS(d+1, i, s+" "+A[i]);
        }
    }
}