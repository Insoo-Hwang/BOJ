import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int [][] A;
    static boolean [][] visited;
    static int [][] wall; //벽을 세울 수 있는 공간
    static int wallcnt = 0; //벽을 세울 수 있는 공간의 갯수
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
                if(temp == 0){ //빈 공간이면
                    wall[wallcnt][0] = i; //N값 저장
                    wall[wallcnt][1] = j; //M값 저장
                    wallcnt++;
                }
                A[i][j] = temp;
            }
        }
        DFS(0, 0);
        System.out.println(ans);
    }

    static void DFS(int index, int cnt){ //cnt는 이미 세워진 벽의 갯수
        if(cnt == 3){ //세워진 벽의 갯수가 3개이면
            BFS(); //바이러스 시작
            return;
        }
        if(index > wallcnt-1) return; //이미 세울 수 있는 공간을 초과 하면 종료

        A[wall[index][0]][wall[index][1]] = 1; //빈 공간에 벽을 세우는 경우
        DFS(index+1, cnt+1);
        A[wall[index][0]][wall[index][1]] = 0; //빈 공간에 벽을 세우지 않는 경우
        DFS(index+1, cnt);
    }
    static void BFS(){
        Queue<nm> queue = new LinkedList<>();
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                visited[i][j] = false;
                if(A[i][j] == 2){
                    visited[i][j] = true; //visited배열 초기화
                    queue.add(new nm(i, j));
                }
            }
        }
        while(!queue.isEmpty()){
            nm now = queue.poll();
            if(now.m < M-1){
                if(!visited[now.n][now.m+1] && A[now.n][now.m+1] == 0){
                    visited[now.n][now.m+1] = true; //방문시 바이러스에 감염
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
                    cnt++; //안전지대 갯수
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
