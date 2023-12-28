import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int [][] A;
    static int [][] ans;
    static int [] start = new int[2];
    static int [] dy = {-1, 1, 0, 0};
    static int [] dx = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N][M];
        ans = new int[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
                if(A[i][j] == 2){
                    start[0] = i;
                    start[1] = j;
                }
                if(A[i][j] == 0 || A[i][j] == 2) ans[i][j] = 0;
                else ans[i][j] = -1;
            }
        }
        BFS();
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                sb.append(ans[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void BFS(){
        Queue<int []> queue = new LinkedList<>();
        queue.add(new int [] {start[0], start[1], 0});
        while(!queue.isEmpty()){
            int [] now = queue.poll();
            for(int i = 0; i < 4; i++){
                int y = now[0]+dy[i];
                int x = now[1]+dx[i];
                if(y > N-1 || y < 0 || x > M-1 || x < 0 || ans[y][x] != -1) continue;
                queue.add(new int [] {y, x, now[2]+1});
                ans[y][x] = now[2]+1;
            }
        }
    }
}