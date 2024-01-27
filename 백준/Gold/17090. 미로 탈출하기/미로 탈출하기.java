import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int [][] A;
    static int [][] visited;
    static List<Boolean> list = new ArrayList<>();
    static int cnt;
    static int [] dy = {-1, 0, 1, 0};
    static int [] dx = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N+2][M+2];
        visited = new int[N+2][M+2];
        for(int i = 1; i < N+1; i++){
            String [] s = br.readLine().split("");
            for(int j = 1; j < M+1; j++){
                if(s[j-1].equals("U")) A[i][j] = 0;
                else if(s[j-1].equals("R")) A[i][j] = 1;
                else if(s[j-1].equals("D")) A[i][j] = 2;
                else A[i][j] = 3;
            }
        }
        for(int i = 0; i < N+2; i++){
            for(int j = 0; j < M+2; j++){
                if(i == 0 || i == N+1 || j == 0 || j == M+1) visited[i][j] = -1;
            }
        }
        int ans = 0;
        int num = 1;
        for(int i = 1; i < N+1; i++){
            for(int j = 1; j < M+1; j++){
                if(visited[i][j] != 0) continue;
                cnt = 0;
                list.add(DFS(i, j, num));
                if(list.get(num-1))ans+=cnt;
                num++;
            }
        }
        System.out.println(ans);
    }

    static boolean DFS(int n, int m, int num){
        if(visited[n][m] != 0){
            if(visited[n][m] == num) return false;
            else if(visited[n][m] == -1) return true;
            else return list.get(visited[n][m]-1);
        }
        visited[n][m] = num;
        int y = n+dy[A[n][m]];
        int x = m+dx[A[n][m]];
        cnt++;
        return DFS(y, x, num);
    }
}