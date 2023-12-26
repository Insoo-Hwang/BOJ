import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int M;
    static int [] A;
    static boolean [] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N];
        visited = new boolean[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(A);
        DFS(0, "");
    }

    static void DFS(int d, String s){
        if(d == M){
            System.out.println(s);
            return;
        }

        for(int i = 0; i < N; i++){
            if(visited[i]) continue;
            visited[i] = true;
            DFS(d+1, s+A[i]+" ");
            visited[i] = false;
        }
    }
}