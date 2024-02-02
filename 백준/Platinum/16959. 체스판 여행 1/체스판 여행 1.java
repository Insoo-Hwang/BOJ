import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int [][] A;
    static int [] start = new int[2];
    static int [] oy = {-1, -2, -1, -2, 1, 2, 2, 1};
    static int [] ox = {-2, -1, 2, 1, 2, 1, -1, -2};
    static int [] ty = {-1, -1, 1, 1, -1, 0, 1, 0};
    static int [] tx = {-1, 1, -1, 1, 0, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        A = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
                if(A[i][j] == 1){
                    start[0] = i;
                    start[1] = j;
                }
            }
        }
        System.out.println(BFS());
    }

    static int BFS(){
        Queue<Chess> queue = new LinkedList<>();
        queue.add(new Chess(start[0], start[1], 0, 0, 2));
        queue.add(new Chess(start[0], start[1], 1, 0, 2));
        queue.add(new Chess(start[0], start[1], 2, 0, 2));
        boolean [][][] visited = new boolean[3][N][N];
        visited[0][start[0]][start[1]] = true;
        visited[1][start[0]][start[1]] = true;
        visited[2][start[0]][start[1]] = true;
        int target = 2;
        while(!queue.isEmpty()){
            Chess now = queue.poll();
            if(now.target != target){
                if(A[now.n][now.m] == target-1 && !visited[now.mode][now.n][now.m]){
                    visited[now.mode][now.n][now.m] = true;
                    queue.add(new Chess(now.n, now.m, now.mode, now.cnt, target));
                }
                continue;
            }
            else if(A[now.n][now.m] == target){
                if(target == N*N) return now.cnt;
                visited = new boolean[3][N][N];
                visited[now.mode][now.n][now.m] = true;
                target++;
                queue.add(new Chess(now.n, now.m, now.mode, now.cnt, target));
                continue;
            }
            if(now.mode == 0){
                for(int i = 0; i < 8; i++){
                    int y = now.n+oy[i];
                    int x = now.m+ox[i];
                    if(y > N-1 || y < 0 || x > N-1 || x < 0 || visited[now.mode][y][x]) continue;
                    queue.add(new Chess(y, x, now.mode, now.cnt+1, target));
                    visited[now.mode][y][x] = true;
                }
                if(!visited[1][now.n][now.m]){
                    queue.add(new Chess(now.n, now.m, 1, now.cnt+1, target));
                    visited[1][now.n][now.m] = true;
                }
                if(!visited[2][now.n][now.m]){
                    queue.add(new Chess(now.n, now.m, 2, now.cnt+1, target));
                    visited[2][now.n][now.m] = true;
                }
            }
            else if(now.mode == 1){
                for(int i = 0; i < 4; i++){
                    for(int j = 1; j < N; j++){
                        int y = now.n+ty[i]*j;
                        int x = now.m+tx[i]*j;
                        if(y > N-1 || y < 0 || x > N-1 || x < 0 || visited[now.mode][y][x]) continue;
                        queue.add(new Chess(y, x, now.mode, now.cnt+1, target));
                        visited[now.mode][y][x] = true;
                    }
                }
                if(!visited[0][now.n][now.m]){
                    queue.add(new Chess(now.n, now.m, 0, now.cnt+1, target));
                    visited[0][now.n][now.m] = true;
                }
                if(!visited[2][now.n][now.m]){
                    queue.add(new Chess(now.n, now.m, 2, now.cnt+1, target));
                    visited[2][now.n][now.m] = true;
                }
            }
            else{
                for(int i = 4; i < 8; i++){
                    for(int j = 1; j < N; j++){
                        int y = now.n+ty[i]*j;
                        int x = now.m+tx[i]*j;
                        if(y > N-1 || y < 0 || x > N-1 || x < 0 || visited[now.mode][y][x]) continue;
                        queue.add(new Chess(y, x, now.mode, now.cnt+1, target));
                        visited[now.mode][y][x] = true;
                    }
                }
                if(!visited[0][now.n][now.m]){
                    queue.add(new Chess(now.n, now.m, 0, now.cnt+1, target));
                    visited[0][now.n][now.m] = true;
                }
                if(!visited[1][now.n][now.m]){
                    queue.add(new Chess(now.n, now.m, 1, now.cnt+1, target));
                    visited[1][now.n][now.m] = true;
                }
            }
        }
        return -1;
    }

    static class Chess{
        int n;
        int m;
        int mode;
        int cnt;
        int target;

        public Chess(int n, int m, int mode, int cnt, int target){
            this.n = n;
            this.m = m;
            this.mode = mode;
            this.cnt = cnt;
            this.target = target;
        }
    }
}