import java.util.*;
import java.io.*;

public class Main {
    static int M;
    static int N;
    static int [][] A;
    static boolean [][] visited;
    static int [] dy = {-1, 1, 0, 0, -1, -1, 1, 1};
    static int [] dx = {0, 0, -1, 1, 1, -1, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N][M];
        visited = new boolean[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int cnt = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(A[i][j] == 0 || visited[i][j]) continue;
                BFS(i, j);
                cnt++;
            }
        }
        System.out.println(cnt);
    }

    static void BFS(int n, int m){
        Queue<int []> queue = new LinkedList<>();
        queue.add(new int [] {n, m});
        visited[n][m] = true;
        while(!queue.isEmpty()) {
            int [] now = queue.poll();
            for (int i = 0; i < 8; i++) {
                int y = now[0] + dy[i];
                int x = now[1] + dx[i];
                if (y > N-1 || y < 0 || x > M-1 || x < 0 || visited[y][x] || A[y][x] == 0) continue;
                visited[y][x] = true;
                queue.add(new int[] {y, x});
            }
        }
    }
}