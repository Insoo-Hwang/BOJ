import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int M;
    static int [][] A;
    static boolean [][] visited;
    static int [] dy = {-1, 1, 0, 0};
    static int [] dx = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        A = new int[N][M];
        visited = new boolean[N][M];
        for(int i = 0; i < N; i++){
            char [] s = br.readLine().toCharArray();
            for(int j = 0; j < M; j++){
                if(s[j] == 'W') A[i][j] = 0;
                else A[i][j] = 1;
            }
        }

        int w = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(visited[i][j] || A[i][j] == 1) continue;
                int temp = BFS(i, j, 0);
                w += temp*temp;
            }
        }
        int b = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(visited[i][j] || A[i][j] == 0) continue;
                int temp = BFS(i, j, 1);
                b += temp*temp;
            }
        }
        System.out.println(w + " " + b);
    }

    static int BFS(int n, int m, int c){
        Queue<int []> queue = new LinkedList<>();
        queue.add(new int [] {n, m});
        visited[n][m] = true;
        int cnt = 1;
        while(!queue.isEmpty()){
            int [] now = queue.poll();
            for(int i = 0; i < 4; i++){
                int y = now[0]+dy[i];
                int x = now[1]+dx[i];
                if(y > N-1 || y < 0 || x > M-1 || x < 0 || visited[y][x] || A[y][x] != c) continue;
                visited[y][x] = true;
                cnt++;
                queue.add(new int[] {y, x});
            }
        }
        return cnt;
    }
}