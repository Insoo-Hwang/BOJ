import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int [] dy = {1, -1, 0, 0};
        int [] dx = {0, 0, -1, 1};
        int n = 1;
        while(true){
            int N = Integer.parseInt(br.readLine());
            if(N == 0) break;
            int [][] A = new int [N][N];
            for(int i = 0; i < N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++){
                    A[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int [][] B = new int [N][N];
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    B[i][j] = Integer.MAX_VALUE;
                }
            }
            boolean [][] visited = new boolean[N][N];
            B[0][0] = A[0][0];
            PriorityQueue<nm> queue = new PriorityQueue<>();
            queue.add(new nm(0, 0, A[0][0]));
            while(!queue.isEmpty()){
                nm now = queue.poll();
                if(visited[now.n][now.m]) continue;
                visited[now.n][now.m] = true;
                for(int i = 0; i < 4; i++){
                    int y = now.n + dy[i];
                    int x = now.m + dx[i];
                    if(y < 0 || y > N-1 || x < 0 || x > N-1) continue;
                    if(B[y][x] > B[now.n][now.m]+A[y][x]){
                        B[y][x] = B[now.n][now.m]+A[y][x];
                        queue.add(new nm(y, x, B[y][x]));
                    }
                }
            }
            sb.append("Problem ").append(n).append(": ").append(B[N-1][N-1]).append("\n");
            n++;
        }
        System.out.println(sb);
    }

    static class nm implements Comparable<nm>{
        int n;
        int m;
        int w;
        public nm(int n, int m, int w){
            this.n = n;
            this.m = m;
            this.w = w;
        }


        @Override
        public int compareTo(nm o) {
            return this.w - o.w;
        }
    }
}