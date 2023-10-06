import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    static int n;
    static ArrayList<Edge> [] A;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        A = new ArrayList[n+1];
        for(int i = 1; i < n+1; i++){
            A[i] = new ArrayList<>();
        }
        int [] item = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i < n+1; i++){
            item[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 0; i < r; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int l = Integer.parseInt(st.nextToken());
            A[a].add(new Edge(b, l));
            A[b].add(new Edge(a, l));
        }

        int max = 0;
        int [] B = new int[n+1];
        for(int i = 1; i < n+1; i++){
            B = dijkstra(i);
            int cnt = 0;
            for(int j = 0; j < n+1; j++){
                if(B[j] <= m){
                    cnt+=item[j];
                }
            }
            max = Math.max(max, cnt);
        }
        System.out.println(max);
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