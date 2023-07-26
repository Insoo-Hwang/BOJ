import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int [][] A;
    static boolean [][] visited;
    static int [][] wall;
    static int wallcnt = 0;
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N][M];
        visited = new boolean[N][M];
        wall = new int[N*M][2];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                int temp = Integer.parseInt(st.nextToken());
                if(temp == 0){
                    wall[wallcnt][0] = i;
                    wall[wallcnt][1] = j;
                    wallcnt++;
                }
                else if(temp == 2){
                    visited[i][j] = true;
                }
                A[i][j] = temp;
            }
        }
        DFS(0, 0);
        System.out.println(ans);
    }

    static void DFS(int index, int cnt){
        if(cnt == 3){
            BFS();
            return;
        }
        if(index > wallcnt-1) return;

        A[wall[index][0]][wall[index][1]] = 1;
        DFS(index+1, cnt+1);
        A[wall[index][0]][wall[index][1]] = 0;
        DFS(index+1, cnt);
    }
    static void BFS(){
        Queue<nm> queue = new LinkedList<>();
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                visited[i][j] = false;
                if(A[i][j] == 2){
                    visited[i][j] = true;
                    queue.add(new nm(i, j));
                }
            }
        }
        while(!queue.isEmpty()){
            nm now = queue.poll();
            if(now.m < M-1){
                if(!visited[now.n][now.m+1] && A[now.n][now.m+1] == 0){
                    visited[now.n][now.m+1] = true;
                    queue.add(new nm(now.n, now.m+1));
                }
            }
            if(now.m > 0){
                if(!visited[now.n][now.m-1] && A[now.n][now.m-1] == 0){
                    visited[now.n][now.m-1] = true;
                    queue.add(new nm(now.n, now.m-1));
                }
            }
            if(now.n < N-1){
                if(!visited[now.n+1][now.m] && A[now.n+1][now.m] == 0){
                    visited[now.n+1][now.m] = true;
                    queue.add(new nm(now.n+1, now.m));
                }
            }
            if(now.n > 0){
                if(!visited[now.n-1][now.m] && A[now.n-1][now.m] == 0){
                    visited[now.n-1][now.m] = true;
                    queue.add(new nm(now.n-1, now.m));
                }
            }
        }

        int cnt = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(A[i][j] == 0 && !visited[i][j]){
                    cnt++;
                }
            }
        }
        ans = Math.max(cnt, ans);
    }
}

class nm{
    int n;
    int m;
    public nm(int n, int m){
        super();
        this.n = n;
        this.m = m;
    }
}