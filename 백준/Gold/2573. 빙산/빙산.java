import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static boolean [][] visited;
    static int [][] A;
    static int N;
    static int M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N][M];
        A = new int[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                int temp = Integer.parseInt(st.nextToken());
                A[i][j] = temp;
            }
        }

        int cnt = 0;
        while(true){
            if(check()){
                cnt = 0;
                break;
            }
            visited = new boolean[N][M];
            int sep = 0;
            for(int i = 0; i < N; i++){
                for(int j = 0; j < M; j++){
                    if(!visited[i][j] && A[i][j] != 0){
                        DFS(i, j);
                        sep++;
                    }
                }
            }
            if(sep > 1) break;
            ice();
            cnt++;
        }
        System.out.println(cnt);
    }

    static void DFS(int n, int m){
        if(visited[n][m] || A[n][m] == 0)
            return;
        visited[n][m] = true;
        if(!visited[n-1][m] && A[n-1][m] != 0)
            DFS(n-1, m);
        if(!visited[n+1][m] && A[n+1][m] != 0)
            DFS(n+1, m);
        if(!visited[n][m-1] && A[n][m-1] != 0)
            DFS(n, m-1);
        if(!visited[n][m+1] && A[n][m+1] != 0)
            DFS(n, m+1);
    }

    static boolean check(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(A[i][j] > 0)
                    return false;
            }
        }
        return true;
    }

    static void ice(){
        int [][] B = new int[N][M];
        for(int i = 1; i < N-1; i++){
            for(int j = 1; j < M-1; j++){
                B[i][j] = A[i][j];
            }
        }
        for(int i = 1; i < N-1; i++){
            for(int j = 1; j < M-1; j++){
                if(A[i-1][j] == 0 && B[i][j] > 0)
                    B[i][j]--;
                if(A[i+1][j] == 0 && B[i][j] > 0)
                    B[i][j]--;
                if(A[i][j-1] == 0 && B[i][j] > 0)
                    B[i][j]--;
                if(A[i][j+1] == 0 && B[i][j] > 0)
                    B[i][j]--;
            }
        }
        A = B.clone();
    }
}
