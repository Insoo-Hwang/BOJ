import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());
        while(T-->0){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            ArrayList<Edge> [] A = new ArrayList[n+1];
            for(int i = 1; i < n+1; i++){
                A[i] = new ArrayList<>();
            }
            int [] B = new int[n+1];
            Arrays.fill(B, Integer.MAX_VALUE);
            boolean [] visited = new boolean[n+1];
            for(int i = 0; i < d; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int s = Integer.parseInt(st.nextToken());
                A[b].add(new Edge(a, s));
            }

            B[c] = 0;
            PriorityQueue<Edge> queue = new PriorityQueue<>();
            queue.add(new Edge(c, 0));
            while(!queue.isEmpty()){
                Edge now = queue.poll();
                if(visited[now.e]) continue;
                visited[now.e] = true;
                for(int i = 0; i < A[now.e].size(); i++){
                    Edge temp = A[now.e].get(i);
                    if(B[temp.e] > B[now.e]+temp.w){
                        B[temp.e] = B[now.e]+temp.w;
                        queue.add(new Edge(temp.e, B[temp.e]));
                    }
                }
            }
            int cnt = 0;
            int max = 0;
            for(int i = 1; i < n+1; i++){
                if(B[i] != Integer.MAX_VALUE){
                    cnt++;
                    max = Math.max(max, B[i]);
                }
            }
            sb.append(cnt).append(" ").append(max).append("\n");
        }
        System.out.println(sb);
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