import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int [][] A;
    static boolean [][] visited;
    static int max = 0;
    static int cnt = 0;

    static int [] dy = {-1, 1, 0, 0};
    static int [] dx = {0, 0, -1 ,1};
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

        visited = new boolean[N][M];
        int pic = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(!visited[i][j] && A[i][j] == 1){
                    cnt = 0;
                    pic++;
                    DFS(i, j);
                }
            }
        }
        System.out.println(pic);
        System.out.println(max);
    }

    static void DFS(int n, int m){
        visited[n][m] = true;
        cnt++;
        max = Math.max(max, cnt);

        for(int i = 0; i < 4; i++){
            int y = n + dy[i];
            int x = m + dx[i];
            if(y > N-1 || y < 0 || x > M-1 || x < 0 || visited[y][x] || A[y][x] == 0) continue;
            DFS(y, x);
        }
    }
}