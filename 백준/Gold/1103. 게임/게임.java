import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static String [][] A;
    static boolean [][] visited;
    static int [][] D;
    static int [] dy = {-1, 1, 0, 0};
    static int [] dx = {0, 0, -1, 1};
    static int max = 0;
    static boolean check = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new String[N][M];
        visited = new boolean[N][M];
        D = new int[N][M];
        for(int i = 0; i < N; i++){
            String [] s = br.readLine().split("");
            for(int j = 0; j < M; j++){
                A[i][j] = s[j];
            }
        }
        visited[0][0] = true;
        DFS(0, 0, 0);
        if(check) System.out.println(-1);
        else System.out.println(max);
    }

    static void DFS(int d, int n, int m){
        D[n][m] = d;
        for(int i = 0; i < 4; i++){
            int y = n+Integer.parseInt(A[n][m])*dy[i];
            int x = m+Integer.parseInt(A[n][m])*dx[i];
            if(y > N-1 || y < 0 || x > M-1 || x < 0 || A[y][x].equals("H") || D[y][x] > d) {
                max = Math.max(max, d+1);
                continue;
            }
            if(visited[y][x]){
                check = true;
                return;
            }
            visited[y][x] = true;
            DFS(d+1, y, x);
            visited[y][x] = false;
        }
    }
}