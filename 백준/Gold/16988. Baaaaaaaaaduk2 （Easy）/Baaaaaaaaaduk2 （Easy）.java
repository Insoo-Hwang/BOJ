import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int [][] A;
    static int [][] myturn = new int[2][2];
    static int [] dy = {-1, 1, 0, 0};
    static int [] dx = {0, 0, -1, 1};
    static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        DFS(0, 0);
        System.out.println(max);
    }

    static void DFS(int s, int d){
        if(d == 2){
            max = Math.max(max, BFS());
            return;
        }
        for(int i = s; i < N*M; i++){
            int n = i/M;
            int m = i%M;
            if(A[n][m] != 0) continue;
            A[n][m] = 1;
            myturn[d][0] = n;
            myturn[d][1] = m;
            DFS(s+1, d+1);
            A[n][m] = 0;
        }
    }

    static int BFS(){
        boolean [][] visited = new boolean[N][M];
        int cnt = 0;
        for(int i = 0; i < 2; i++){
            for(int j = 0; j < 4; j++){
                int y = myturn[i][0]+dy[j];
                int x = myturn[i][1]+dx[j];
                if(y > N-1 || y < 0 || x > M-1 || x < 0 || visited[y][x] || A[y][x] != 2) continue;
                visited[y][x]  = true;
                Queue<int []> queue = new LinkedList<>();
                queue.add(new int [] {y, x});
                boolean check = true;
                int checknum = 0;
                while(!queue.isEmpty()){
                    int [] now = queue.poll();
                    checknum++;
                    for(int k = 0; k < 4; k++){
                        int yy = now[0]+dy[k];
                        int xx = now[1]+dx[k];
                        if(yy > N-1 || yy < 0 || xx > M-1 || xx < 0 || visited[yy][xx] || A[yy][xx] == 1) continue;
                        if(A[yy][xx] == 0){
                            check = false;
                            continue;
                        }
                        visited[yy][xx] = true;
                        queue.add(new int [] {yy, xx});
                    }
                }
                if(check) cnt+=checknum;
            }
        }
        return cnt;
    }
}