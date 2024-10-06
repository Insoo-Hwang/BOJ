import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int [][] A;
    static boolean [][] visited;
    static int [] group;
    static int [] dy = {-1, 1, 0, 0};
    static int [] dx = {0, 0, -1, 1};
    static int score = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        while(true) {
            visited = new boolean[N][N];
            group = new int[4];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (A[i][j] == -1 || visited[i][j] || A[i][j] == 6 || A[i][j] == 0) continue;
                    int[] temp = find(A[i][j], i, j);
                    if (group[2] < temp[2]) group = temp;
                    else if (group[2] == temp[2]) {
                        if (group[3] <= temp[3]) group = temp;
                    }
                    initR();
                }
            }
            if(group[2] < 2) break;

            visited = new boolean[N][N];
            remove();
            score += group[2] * group[2];

            gravity();
            A = rotate();
            gravity();
        }

        System.out.println(score);
    }

    private static int [] find(int c, int n, int m){
        visited[n][m] = true;
        Queue<int []> queue = new LinkedList<>();
        queue.add(new int [] {n, m});
        int cnt = 0;
        int r = 0;
        while(!queue.isEmpty()){
            int [] now = queue.poll();
            cnt++;
            if(A[now[0]][now[1]] == 0) r++;
            for(int i = 0; i < 4; i++){
                int y = now[0]+dy[i];
                int x = now[1]+dx[i];
                if(y > N-1 || y < 0 || x > N-1 || x < 0 || visited[y][x] || A[y][x] == -1 || (A[y][x] != c && A[y][x] != 0) || A[y][x] > M) continue;
                visited[y][x] = true;
                queue.add(new int [] {y, x});
            }
        }
        return new int [] {n, m, cnt, r};
    }

    private static void initR(){
         for(int i = 0; i < N; i++){
             for(int j = 0; j < N; j++){
                 if(A[i][j] != 0) continue;
                 visited[i][j] = false;
             }
         }
    }

    private static void remove(){
        int c = A[group[0]][group[1]];
        visited[group[0]][group[1]] = true;
        A[group[0]][group[1]] = 6;
        Queue<int []> queue = new LinkedList<>();
        queue.add(new int[] {group[0], group[1]});
        while(!queue.isEmpty()){
            int [] now = queue.poll();
            for(int i = 0; i < 4; i++){
                int y = now[0]+dy[i];
                int x = now[1]+dx[i];
                if(y > N-1 || y < 0 || x > N-1 || x < 0 || visited[y][x] || A[y][x] == -1 || (A[y][x] != c && A[y][x] != 0) || A[y][x] > M) continue;
                visited[y][x] = true;
                A[y][x] = 6;
                queue.add(new int[] {y, x});
            }
        }
    }

    private static void gravity(){
        for(int j = 0; j < N; j++) {
            for(int i = N-1; i > -1; i--){
                if(A[i][j] == -1 || A[i][j] == 6) continue;
                int y = i+1;
                while(true){
                    if(y == N || A[y][j] == -1) break;
                    if(A[y][j] == 6){
                        A[y][j] = A[y-1][j];
                        A[y-1][j] = 6;
                        y++;
                    }
                    else break;
                }
            }
        }
    }

    private static int [][] rotate(){
        int sum = N-1;
        int [][] B = new int[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                B[sum-j][i] = A[i][j];
            }
        }
        return B;
    }
}