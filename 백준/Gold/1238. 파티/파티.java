import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        ArrayList<Edge> [] A = new ArrayList[N+1];
        for(int i = 0; i < N+1; i++){
            A[i] = new ArrayList<>();
        }
        ArrayList<Edge> [] C = new ArrayList[N+1];
        for(int i = 0; i < N+1; i++){
            C[i] = new ArrayList<>();
        }
        int [] B = new int[N+1];
        Arrays.fill(B, Integer.MAX_VALUE);
        int [] D = new int[N+1];
        Arrays.fill(D, Integer.MAX_VALUE);
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            A[a].add(new Edge(b, c));
            C[b].add(new Edge(a, c));
        }

        boolean [] visited1 = new boolean[N+1];
        B[X] = 0;
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        queue.add(new Edge(X, 0));
        while(!queue.isEmpty()){
            Edge now = queue.poll();
            if(visited1[now.e]) continue;
            visited1[now.e] = true;
            for(int i = 0; i < A[now.e].size(); i++){
                Edge temp = A[now.e].get(i);
                if(B[temp.e] > B[now.e]+temp.w){
                    B[temp.e] = B[now.e]+temp.w;
                    queue.add(new Edge(temp.e, B[temp.e]));
                }
            }
        }

        boolean [] visited2 = new boolean[N+1];
        D[X] = 0;
        queue.add(new Edge(X, 0));
        while(!queue.isEmpty()){
            Edge now = queue.poll();
            if(visited2[now.e]) continue;
            visited2[now.e] = true;
            for(int i = 0; i < C[now.e].size(); i++){
                Edge temp = C[now.e].get(i);
                if(D[temp.e] > D[now.e]+temp.w){
                    D[temp.e] = D[now.e]+temp.w;
                    queue.add(new Edge(temp.e, D[temp.e]));
                }
            }
        }

        int max = 0;
        for(int i = 0; i < N+1; i++){
            max = Math.max(max, B[i]+D[i]);
        }
        System.out.println(max);
    }

    static class Edge implements Comparable<Edge>{
        int e;
        int w;

        public Edge(int e, int w){
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return this.w - o.w;
        }
    }
}