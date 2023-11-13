import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int [][] A;
    static int max = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        A = new int [N][2];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            A[i][0] = Integer.parseInt(st.nextToken());
            A[i][1] = Integer.parseInt(st.nextToken());
        }
        DFS(0);
        System.out.print(max);
    }

    static void DFS(int d){
        if(d == N){
            int n = 0;
            for(int i = 0; i < N; i++){
                if(A[i][0] < 1) n++;
            }
            max = Math.max(max, n);
            return;
        }
        if(A[d][0] < 1) DFS(d+1);
        boolean check = false;
        for(int i = 0; i < N; i++){
            if(i == d) continue;
            if(A[i][0] < 1 || A[d][0] < 1) continue;
            check = true;
            A[d][0]-=A[i][1];
            A[i][0]-=A[d][1];
            DFS(d+1);
            A[d][0]+=A[i][1];
            A[i][0]+=A[d][1];
        }
        if(!check){
            int n = 0;
            for(int i = 0; i < N; i++){
                if(A[i][0] < 1) n++;
            }
            max = Math.max(max, n);
        }
    }
}