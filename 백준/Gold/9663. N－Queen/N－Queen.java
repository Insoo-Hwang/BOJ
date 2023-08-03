import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[][] visited;
    static int cnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        visited = new int[N][N];

        DFS(0);
        System.out.println(cnt);
    }

    static void DFS(int d){
        if(d == N){
            cnt++;
            return;
        }
        for(int i = 0; i < N; i++){
            if(visited[d][i] == 0){
                check(d, i, 1);
                DFS(d+1);
                check(d, i, -1);
            }
        }
    }

    static void check(int n, int m, int b){
        int x = n - m;
        for(int i = 0; i < N; i++){
            visited[i][m] += b;
            visited[n][i] += b;
            if(i-x < N && i-x >= 0)
                visited[i][i-x] += b;
            int y = n - i;
            if(n-y < N && n-y >= 0 && m+y < N && m+y >= 0)
                visited[n-y][m+y] += b;
        }
    }
}