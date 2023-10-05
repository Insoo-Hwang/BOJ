import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static int n;
    static ArrayList<Edge> [] A;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());
        while(T-->0){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            A = new ArrayList[n+1];
            for(int i = 0; i < n+1; i++){
                A[i] = new ArrayList<>();
            }
            int [] B = new int[t];
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            int h = Integer.parseInt(st.nextToken());
            for(int i = 0; i < m; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = Integer.parseInt(st.nextToken());
                A[a].add(new Edge(b, d));
                A[b].add(new Edge(a, d));
            }
            for(int i = 0; i < t; i++){
                B[i] = Integer.parseInt(br.readLine());
            }
            Arrays.sort(B);

            int [] S = dijkstra(s);
            int [] G = dijkstra(g);
            int [] H = dijkstra(h);
            for(int i : B){
                if(S[g]+G[h]+H[i] == S[i] || S[h]+H[g]+G[i] == S[i]){
                    sb.append(i).append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static int [] dijkstra(int s){
        int [] B = new int[n+1];
        Arrays.fill(B, Integer.MAX_VALUE);
        boolean [] visited = new boolean[n+1];

        B[s] = 0;
        PriorityQueue<Edge> queue = new PriorityQueue<>();
        queue.add(new Edge(s, 0));
        while(!queue.isEmpty()){
            Edge now = queue.poll();
            if(visited[now.e]) continue;
            visited[now.e] = true;
            for(int i = 0; i < A[now.e].size(); i++){
                Edge temp = A[now.e].get(i);
                if(B[temp.e] > B[now.e] + temp.w){
                    B[temp.e] = B[now.e] + temp.w;
                    queue.add(new Edge(temp.e, B[temp.e]));
                }
            }
        }
        return B;
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