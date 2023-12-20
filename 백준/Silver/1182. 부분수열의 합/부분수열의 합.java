import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int S;
    static int [] A;
    static int cnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        A = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }
        DFS(0, 0, 0);
        System.out.println(cnt);
    }

    static void DFS(int d, int sum, int c){
        if(d == N){
            if(sum == S && c > 0){
                cnt++;
            }
            return;
        }

        DFS(d+1, sum+A[d], c+1);
        DFS(d+1, sum, c);
    }
}