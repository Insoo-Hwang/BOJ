import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int [][] A = new int[N][N];
        for(int i = 0; i < N; i++){
            String s = br.readLine();
            for(int j = 0; j < N; j++){
                if(Character.getNumericValue(s.charAt(j)) == 1) A[i][j] = 0;
                else A[i][j] = 1;
            }
        }
        int [][] B = new int[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                B[i][j] = Integer.MAX_VALUE;
            }
        }

        B[0][0] = 0;
        PriorityQueue<nm> queue = new PriorityQueue<>();
        queue.add(new nm(0, 0, 0));
        int [] dy = {-1, 1, 0, 0};
        int [] dx = {0, 0, -1, 1};
        while(!queue.isEmpty()){
            nm now = queue.poll();
            for(int i = 0; i < 4; i++){
                int y = now.n + dy[i];
                int x = now.m + dx[i];
                if(y > N-1 || y < 0 || x > N-1 || x < 0) continue;
                if(B[y][x] > B[now.n][now.m]+A[y][x]){
                    B[y][x] = B[now.n][now.m]+A[y][x];
                    queue.add(new nm(y, x, B[y][x]));
                }
            }
        }
        System.out.println(B[N-1][N-1]);
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