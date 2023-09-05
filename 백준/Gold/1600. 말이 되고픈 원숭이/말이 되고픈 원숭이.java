import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int K;
    static int W;
    static int H;
    static int [][] A;
    static int [] ky = {2, 2, 1, 1, -1, -1, -2, -2};
    static int [] kx = {1, -1, 2, -2, 2, -2, 1, -1};
    static int [] dy = {-1, 1, 0, 0};
    static int [] dx = {0, 0, -1, 1};
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        W = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        A = new int[H][W];
        for(int i = 0; i < H; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < W; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        BFS();
        if(min == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(min);
    }

    static void BFS(){
        boolean [][][] visited = new boolean[H][W][K+1];
        visited[0][0][0] = true;
        Queue<Monkey> queue = new LinkedList<>();
        queue.add(new Monkey(0, 0, 0, 0));

        while(!queue.isEmpty()){
            Monkey now = queue.poll();
            if(now.n == H-1 && now.m == W-1){
                min = Math.min(min, now.c);
                return;
            }
            if(now.k < K){
                for(int i = 0; i < 8; i++){
                    int y = now.n + ky[i];
                    int x = now.m + kx[i];
                    if(y < 0 || y > H-1 || x < 0 || x > W-1 || A[y][x] == 1 || visited[y][x][now.k+1]) continue;
                    visited[y][x][now.k+1] = true;
                    queue.add(new Monkey(y, x, now.c+1, now.k+1));
                }
            }
            for(int i = 0; i < 4; i++){
                int y = now.n + dy[i];
                int x = now.m + dx[i];
                if(y < 0 || y > H-1 || x < 0 || x > W-1 || A[y][x] == 1 || visited[y][x][now.k]) continue;
                visited[y][x][now.k] = true;
                queue.add(new Monkey(y, x, now.c+1, now.k));
            }
        }
    }

    static class Monkey{
        int n;
        int m;
        int c;
        int k;
        public Monkey(int n, int m, int c, int k){
            this.n = n;
            this.m = m;
            this.c = c;
            this.k = k;
        }
    }
}