import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int K;
    static int [][] A;
    static int [] dy = {-1, 1, 0, 0};
    static int [] dx = {0, 0, -1, 1};
    static int min = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = new int [N][M];
        for(int i = 0; i < N; i++){
            String s = br.readLine();
            for(int j = 0; j < M; j++){
                A[i][j] = s.charAt(j) - '0';
            }
        }

        BFS();
        System.out.println(min);
    }

    static void BFS(){
        Queue<NM> queue = new LinkedList<>();
        queue.add(new NM(0, 0, 0, 1, 0));
        boolean [][][][] visited = new boolean[N][M][K+1][2];
        visited[0][0][0][0] = true; //(N, M)칸에 K개의 벽을 부순 상태로 이동했을 때 시간(낮/밤)
        while(!queue.isEmpty()){
            NM now = queue.poll();
            if(now.n == N-1 && now.m == M-1){ //(N, M)에 도착하면
                min = now.c; //값 업데이트
                return;
            }
            for(int i = 0; i < 4; i++){
                int tempn = now.n + dy[i];
                int tempm = now.m + dx[i];
                if(tempn < 0 || tempn > N-1 || tempm < 0 || tempm > M-1) continue;
                if(A[tempn][tempm] == 1 && now.k < K && !visited[tempn][tempm][now.k+1][1] && now.t == 0){ //이동했는데 벽이 있는 경우 + 벽을 부술 수 있는 상황
                    queue.add(new NM(tempn, tempm, now.k+1, now.c+1, 1)); //벽을 부수고 이동하면 밤으로 바뀜
                    visited[tempn][tempm][now.k+1][1] = true; //이동한 곳에서는 밤
                }
                else if(A[tempn][tempm] == 1 && now.k < K && !visited[now.n][now.m][now.k][0] && now.t == 1){ //이동했는데 벽이 있는 경우 + 벽을 부술 수 없는 상황
                    queue.add(new NM(now.n, now.m, now.k, now.c+1, 0)); //현재 위치에서 낮으로 바뀌면 이동(현재 위치에서 밤)
                    visited[now.n][now.m][now.k][0] = true;
                }
                else if(A[tempn][tempm] == 0 && !visited[tempn][tempm][now.k][(now.t+1)%2]) { //벽이 없는 경우
                    queue.add(new NM(tempn, tempm, now.k, now.c+1, (now.t+1)%2)); //이동 후 낮과 밤만 바뀜
                    visited[tempn][tempm][now.k][(now.t+1)%2] = true;
                }
            }
        }
    }

    static class NM{
        int n; //N좌표
        int m; //M좌표
        int k; //부순 벽의 수
        int c; //이동한 칸의 수
        int t; //낮과 밤 여부(낮 : 0, 밤 : 1)
        public NM(int n, int m, int k, int c, int t){
            this.n = n;
            this.m = m;
            this.k = k;
            this.c = c;
            this.t = t;
        }
    }
}
