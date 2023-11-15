import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int M;
    static char [][] A;
    static boolean [][] visited;
    static int sn = 0;
    static int sm = 0;
    static int [] dy = {-1, 1, 0, 0};
    static int [] dx = {0, 0, -1, 1};
    static boolean check = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new char[N][M];
        for(int i = 0; i < N; i++){
            String s = br.readLine();
            for(int j = 0; j < M; j++){
                A[i][j] = s.charAt(j);
            }
        }
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                visited = new boolean[N][M];
                sn = i;
                sm = j;
                DFS(i, j, 0, A[i][j]);
                if(check){
                    System.out.println("Yes");
                    return;
                }
            }
        }
        System.out.print("No");
    }

    static void DFS(int n, int m, int d, char c){
        if(sn == n && sm == m && d > 3){
            check = true;
            return;
        }
        for(int i = 0; i < 4; i++){
            int y = n+dy[i];
            int x = m+dx[i];
            if(y > N-1 || y < 0 || x > M-1 || x < 0 || visited[y][x] || A[y][x] != c || (y == sn && x == sm && d < 3)) continue;
            visited[y][x] = true;
            DFS(y, x, d+1, c);
        }
    }
}