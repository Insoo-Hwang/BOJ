import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int M;
    static boolean [][] A;
    static int [] dy = {-1, 1, 0, 0};
    static int [] dx = {0, 0, -1, 1};
    static int max = 0;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new boolean[N][M];
        for(int i = 0; i < N; i++){
            String s = br.readLine();
            for(int j = 0; j < M; j++){
                if(s.charAt(j) == 'L') A[i][j] = true;
            }
        }
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(A[i][j]) BFS(i, j);
            }
        }
        System.out.print(max);
    }

    static void BFS(int n, int m){
        boolean [][] visited = new boolean[N][M];
        visited[n][m] = true;
        Queue<int []> queue = new LinkedList<>();
        queue.add(new int[] {n, m, 0});
        while(!queue.isEmpty()){
            int [] now = queue.poll();
            max = Math.max(max, now[2]);
            for(int i = 0; i < 4; i++){
                int y = now[0]+dy[i];
                int x = now[1]+dx[i];
                if(y > N-1 || y < 0 || x > M-1 || x < 0 || visited[y][x] || !A[y][x]) continue;
                visited[y][x] = true;
                queue.add(new int[] {y, x, now[2]+1});
            }
        }
    }
}