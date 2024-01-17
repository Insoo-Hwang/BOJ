import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        if(N == K){
            System.out.println(0);
            System.out.println(N);
            return;
        }
        else if(N > K){
            System.out.println(N-K);
            for(int i = N; i >= K; i--) System.out.print(i+" ");
            return;
        }
        BFS(N, K);
    }

    static void BFS(int N, int K){
        boolean [] visited = new boolean[100001];
        visited[N] = true;
        Queue<Soobin> queue = new LinkedList<>();
        queue.add(new Soobin(N, 0, N+" "));
        while(!queue.isEmpty()){
            Soobin now = queue.poll();
            if(now.l == K){
                System.out.println(now.n);
                System.out.println(now.s);
                return;
            }
            if(now.l+1 < 100001 && !visited[now.l+1]){
                visited[now.l+1] = true;
                int nowL = now.l+1;
                queue.add(new Soobin(nowL, now.n+1, now.s+nowL+" "));
            }
            if(now.l*2 < 100001 && !visited[now.l*2]){
                visited[now.l*2] = true;
                int nowL = now.l*2;
                queue.add(new Soobin(nowL, now.n+1, now.s+nowL+" "));
            }
            if(now.l-1 > -1 && !visited[now.l-1]){
                visited[now.l-1] = true;
                int nowL = now.l-1;
                queue.add(new Soobin(nowL, now.n+1, now.s+nowL+" "));
            }
        }
    }

    static class Soobin{
        int l;
        int n;
        String s;
        public Soobin(int l, int n, String s){
            this.l = l;
            this.n = n;
            this.s = s;
        }
    }
}