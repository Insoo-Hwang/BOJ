import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int [][] visited;
    static int [] dy = {-1, 1, 0, 0};
    static int [] dx = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        visited = new int[trans(N)+1][trans(N)+1];
        for(int i = 0; i < R; i++){
            st = new StringTokenizer(br.readLine());
            double r1 = Integer.parseInt(st.nextToken());
            double c1 = Integer.parseInt(st.nextToken());
            double r2 = Integer.parseInt(st.nextToken());
            double c2 = Integer.parseInt(st.nextToken());
            int r = trans((r1+r2)/2);
            int c = trans((c1+c2)/2);
            visited[r][c] = 101;
        }
        int cnt = 1;
        int [] A = new int[K];
        int [] B = new int[101];
        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int r = trans(Integer.parseInt(st.nextToken()));
            int c = trans(Integer.parseInt(st.nextToken()));
            if(visited[r][c] != 0){
                A[i] = visited[r][c];
                B[visited[r][c]]++;
                continue;
            }
            BFS(r, c, cnt);
            A[i] = cnt;
            B[cnt]++;
            cnt++;
        }
        int ans = 0;
        for(int i = 0; i < K; i++){
            ans+=(K-B[A[i]]);
        }
        System.out.println(ans/2);
    }

    static void BFS(int n, int m, int num){
        visited[n][m] = num;
        Queue<int []> queue = new LinkedList<>();
        queue.add(new int[] {n, m});
        while(!queue.isEmpty()){
            int [] now = queue.poll();
            for(int i = 0; i < 4; i++){
                int y = now[0]+2*dy[i];
                int x = now[1]+2*dx[i];
                int cy = now[0]+dy[i];
                int cx = now[1]+dx[i];
                if(y > trans(N) || y < 0 || x > trans(N) || x < 0 || visited[y][x] != 0 || visited[cy][cx] != 0) continue;
                visited[y][x] = num;
                queue.add(new int[] {y, x});
            }
        }
    }

    public static int trans(double x){
        return (int)(2*x-2);
    }
}