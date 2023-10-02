import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int [][] A = new int[N][M];
        int [][] B = new int[N][M];
        boolean [][] visited = new boolean[N][M];
        for(int i = 0; i < N; i++){
            String s = br.readLine();
            for(int j = 0; j < M; j++){
                A[i][j] = Character.getNumericValue(s.charAt(j));
            }
        }
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                B[i][j] = Integer.MAX_VALUE;
            }
        }

        B[0][0] = 0;
        int [] dy = {1, -1, 0, 0};
        int [] dx = {0, 0, -1, 1};
        PriorityQueue<nm> queue = new PriorityQueue<>();
        queue.add(new nm(0, 0, 0));
        while(!queue.isEmpty()){
            nm now = queue.poll();
            if(visited[now.n][now.m]) continue;
            visited[now.n][now.m] = true;
            for(int i = 0; i < 4; i++){
                int y = now.n + dy[i];
                int x = now.m + dx[i];
                if(y > N-1 || y < 0 || x > M-1 || x < 0) continue;
                if(B[y][x] > A[y][x]+B[now.n][now.m]){
                    B[y][x] = A[y][x]+B[now.n][now.m];
                    queue.add(new nm(y, x, B[y][x]));
                }
            }
        }
        System.out.println(B[N-1][M-1]);
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