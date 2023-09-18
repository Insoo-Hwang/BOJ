import java.io.*;
import java.util.*;

public class Main {
    static int R;
    static int C;
    static int [][] A;
    static boolean [][] visited;
    static int [] dy = {-1, 0, 1};
    static int [] dx = {1, 1, 1};
    static int cnt = 0;
    static boolean check;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        A = new int[R][C];
        for(int i = 0; i < R; i++){
            String s = br.readLine();
            for(int j = 0; j < C; j++){
                if(s.charAt(j) == '.') {
                    A[i][j] = 0;
                }
                else{
                    A[i][j] = 1;
                }
            }
        }
        visited = new boolean[R][C];
        for(int i = 0; i < R; i++){
            check = false;
            DFS(i, 0);
        }
        System.out.println(cnt);
    }

    static void DFS(int n, int m){
        if(m == C-1){
            cnt++;
            check = true;
            return;
        }

        for(int i = 0; i < 3; i++){
            int y = n + dy[i];
            int x = m + dx[i];
            if(y < 0 || y > R-1 || x < 0 || x > C-1 || visited[y][x] || A[y][x] == 1) continue;
            visited[y][x] = true;
            DFS(y, x);
            if(check){
                break;
            }
        }
    }
}