import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int [][] W = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                W[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int [][] D = new int[N][1<<N];
        for(int i = 0; i < N; i++)  Arrays.fill(D[i], Integer.MAX_VALUE);
        D[0][1] = 0;
        PriorityQueue<SalesMan> queue = new PriorityQueue<>();
        queue.add(new SalesMan(0, 0, 1));
        while(!queue.isEmpty()){
            SalesMan now = queue.poll();
            if(now.v == (1<<N)-1) {
                if(W[now.l][0] != 0){
                    D[0][(1<<N)-1] = Math.min(D[0][(1<<N)-1], D[now.l][(1<<N)-1]+W[now.l][0]);
                }
                continue;
            }
            for(int i = 1; i < N; i++){
                if((now.v & (1<<i)) > 0 || W[now.l][i] == 0) continue;
                if(D[i][now.v | (1<<i)] > D[now.l][now.v]+W[now.l][i]){
                    D[i][now.v | (1<<i)] = D[now.l][now.v]+W[now.l][i];
                    queue.add(new SalesMan(i, D[i][now.v | (1<<i)], now.v | (1<<i)));
                }
            }
        }
        System.out.println(D[0][(1<<N)-1]);
    }

    static class SalesMan implements Comparable<SalesMan>{
        int l;
        int c;
        int v;

        public SalesMan(int l, int c, int v){
            this.l = l;
            this.c = c;
            this.v = v;
        }

        @Override
        public int compareTo(SalesMan o){
            return this.c-o.c;
        }
    }
}