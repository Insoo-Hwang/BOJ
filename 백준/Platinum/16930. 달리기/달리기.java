import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int K;
    static int [][] A;
    static int x1;
    static int y1;
    static int x2;
    static int y2;
    static int [] dy = {-1, 1, 0, 0};
    static int [] dx = {0, 0, -1, 1};
    static int min = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = new int[N][M];
        for(int i = 0; i < N; i++){
            char [] s = br.readLine().toCharArray();
            for(int j = 0; j < M; j++){
                if(s[j] == '.') A[i][j] = 0;
                else A[i][j] = 1;
            }
        }
        st = new StringTokenizer(br.readLine());
        x1 = Integer.parseInt(st.nextToken())-1;
        y1 = Integer.parseInt(st.nextToken())-1;
        x2 = Integer.parseInt(st.nextToken())-1;
        y2 = Integer.parseInt(st.nextToken())-1;
        BFS();
        System.out.println(min);
    }

    static void BFS(){
        int [][] visited = new int[N][M];
        for(int i = 0; i < N; i++){
            Arrays.fill(visited[i], Integer.MAX_VALUE);
        }
        visited[x1][y1] = 0;
        Queue<Race> queue = new LinkedList<>();
        queue.add(new Race(x1, y1, 0));
        while(!queue.isEmpty()){
            Race now = queue.poll();
            for(int i = 0; i < 4; i++){
                for(int j = 1; j < K+1; j++){
                    int y = now.n+dy[i]*j;
                    int x = now.m+dx[i]*j;
                    if(y > N-1 || y < 0 || x > M-1 || x < 0 || A[y][x] == 1 || visited[y][x] < now.c+1) break;
                    if(visited[y][x] == now.c+1) continue;
                    if(y == x2 && x == y2){
                        min = now.c+1;
                        return;
                    }
                    visited[y][x] = now.c+1;
                    queue.add(new Race(y, x, now.c+1));
                }
            }
        }
    }

    static class Race{
        int n;
        int m;
        int c;

        public Race(int n, int m, int c){
            this.n = n;
            this.m = m;
            this.c = c;
        }
    }
}