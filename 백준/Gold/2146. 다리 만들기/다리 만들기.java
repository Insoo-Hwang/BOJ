import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int [][] A;
    static boolean [][] visited;
    static int [] dy = {-1, 1, 0, 0};
    static int [] dx = {0, 0, -1, 1};
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        A = new int[N][N];
        visited = new boolean[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int num = 1;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(A[i][j] != 1 || visited[i][j]) continue;
                BFS1(i, j, num);
                num++;
            }
        }
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(A[i][j] == 0) continue;
                for(int k = 0; k < 4; k++){
                    int n = A[i][j];
                    int y = i+dy[k];
                    int x = j+dx[k];
                    if(y > N-1 || y < 0 || x > N-1 || x < 0 || A[y][x] == n) continue;
                    BFS2(y, x, n);
                }
            }
        }
        System.out.println(min);
    }

    static void BFS1(int n, int m, int num){
        Queue <int []> queue = new LinkedList<>();
        queue.add(new int [] {n, m});
        visited[n][m] = true;
        A[n][m] = num;
        while(!queue.isEmpty()){
            int [] now = queue.poll();
            for(int i = 0; i < 4; i++){
                int y = now[0]+dy[i];
                int x = now[1]+dx[i];
                if(y > N-1 || y < 0 || x > N-1 || x < 0 || visited[y][x] || A[y][x] == 0) continue;
                visited[y][x] = true;
                A[y][x] = num;
                queue.add(new int [] {y, x});
            }
        }
    }

    static void BFS2(int n, int m, int num){
        Queue <int []> queue = new LinkedList<>();
        queue.add(new int [] {n, m, 0});
        visited = new boolean[N][N];
        visited[n][m] = true;
        while(!queue.isEmpty()){
            int [] now = queue.poll();
            if(A[now[0]][now[1]] != num && A[now[0]][now[1]] > 0){
                min = Math.min(min, now[2]);
                break;
            }
            if(now[2] > min) break;
            for(int i = 0; i < 4; i++){
                int y = now[0]+dy[i];
                int x = now[1]+dx[i];
                if(x > N-1 || x < 0 || y > N-1 || y < 0 || A[y][x] == num || visited[y][x]) continue;
                queue.add(new int [] {y, x, now[2]+1});
                visited[y][x] = true;
            }
        }
    }
}