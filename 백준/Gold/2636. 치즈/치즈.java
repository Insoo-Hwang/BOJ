import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int [][] A;
    static boolean [][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int hour = 0;
        int cnt = 0;
        while (check() != 0) {
            cnt = check();
            visited = new boolean[N][M];
            outAir(0, 0);
            BFS();
            hour++;
        }
        System.out.println(hour);
        System.out.println(cnt);
    }
    static void BFS(){
        int [][] B = new int[N][M];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                B[i][j] = A[i][j];
            }
        }

        for(int i = 1; i < N-1; i++){
            for(int j = 1; j < M-1; j++){
                if(A[i][j] == 0) continue;
                if(A[i-1][j] == 2 || A[i+1][j] == 2 || A[i][j-1] == 2 || A[i][j+1] == 2){
                    B[i][j] = 0;
                }
            }
        }
        A = B.clone();
    }

    static int check(){
        int temp = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(A[i][j] == 1)
                   temp++;
            }
        }
        return temp;
    }

    static void outAir(int n, int m){
        if(visited[n][m] || A[n][m] == 1){
            return;
        }
        visited[n][m] = true;
        A[n][m] = 2;
        if(n-1 > -1 && !visited[n-1][m] && A[n-1][m] != 1)
            outAir(n-1, m);
        if(n+1 < N && !visited[n+1][m] && A[n+1][m] != 1)
            outAir(n+1, m);
        if(m-1 > -1 && !visited[n][m-1] && A[n][m-1] != 1)
            outAir(n, m-1);
        if(m+1 < M && !visited[n][m+1] && A[n][m+1] != 1)
            outAir(n, m+1);
    }
}