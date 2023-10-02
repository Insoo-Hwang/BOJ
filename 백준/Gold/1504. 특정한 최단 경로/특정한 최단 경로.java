import java.io.*;
import java.util.*;

public class Main {
    static final int INF = 200000000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<Edge> [] A = new ArrayList[N+1];
        for(int i = 0; i < N+1; i++){
            A[i] = new ArrayList<>();
        }
        int [] B = new int[N+1];
        for(int i = 0; i < N+1; i++){
            B[i] = INF;
        }
        boolean [] visited = new boolean[N+1];
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            A[a].add(new Edge(b, c));
            A[b].add(new Edge(a, c));
        }
        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        B[1] = 0;
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        queue.add(new Edge(1, 0));
        while(!queue.isEmpty()){
            Edge now = queue.poll();
            visited[now.e] = true;
            for(int i = 0; i < A[now.e].size(); i++){
                Edge temp = A[now.e].get(i);
                if(!visited[temp.e] && B[temp.e] > B[now.e]+temp.w){
                    B[temp.e] = B[now.e]+temp.w;
                    queue.add(new Edge(temp.e, B[temp.e]));
                }
            }
        }

        int [] C = new int[N+1];
        for(int i = 0; i < N+1; i++){
            C[i] = INF;
        }
        visited = new boolean[N+1];
        C[v1] = 0;
        queue.add(new Edge(v1, 0));
        while(!queue.isEmpty()){
            Edge now = queue.poll();
            visited[now.e] = true;
            for(int i = 0; i < A[now.e].size(); i++){
                Edge temp = A[now.e].get(i);
                if(!visited[temp.e] && C[temp.e] > C[now.e]+temp.w){
                    C[temp.e] = C[now.e]+temp.w;
                    queue.add(new Edge(temp.e, C[temp.e]));
                }
            }
        }

        int [] D = new int[N+1];
        for(int i = 0; i < N+1; i++){
            D[i] = INF;
        }
        visited = new boolean[N+1];
        D[v2] = 0;
        queue.add(new Edge(v2, 0));
        while(!queue.isEmpty()){
            Edge now = queue.poll();
            visited[now.e] = true;
            for(int i = 0; i < A[now.e].size(); i++){
                Edge temp = A[now.e].get(i);
                if(!visited[temp.e] && D[temp.e] > D[now.e]+temp.w){
                    D[temp.e] = D[now.e]+temp.w;
                    queue.add(new Edge(temp.e, D[temp.e]));
                }
            }
        }

        int s1 = B[v1] + C[v2] + D[N];
        int s2 = B[v2] + D[v1] + C[N];
        if(s1 >= INF && s2 >= INF) System.out.println(-1);
        else System.out.println(Math.min(s1, s2));
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