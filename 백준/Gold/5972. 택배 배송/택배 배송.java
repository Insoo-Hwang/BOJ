import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<Cow> [] A = new ArrayList[N+1];
        for(int i = 1; i < N+1; i++){
            A[i] = new ArrayList<>();
        }
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            A[s].add(new Cow(e, w));
            A[e].add(new Cow(s, w));
        }
        int [] shortA = new int[N+1];
        Arrays.fill(shortA, Integer.MAX_VALUE);
        shortA[1] = 0;
        boolean [] visited = new boolean[N+1];
        PriorityQueue<Cow> queue = new PriorityQueue<>();
        queue.add(new Cow(1, 0));
        while(!queue.isEmpty()){
            Cow now = queue.poll();
            if(visited[now.e]) continue;
            visited[now.e] = true;
            for(int i = 0; i < A[now.e].size(); i++){
                Cow temp = A[now.e].get(i);
                if(shortA[temp.e] > shortA[now.e]+temp.w){
                    shortA[temp.e] = shortA[now.e]+temp.w;
                    queue.add(new Cow(temp.e, shortA[temp.e]));
                }
            }
        }
        System.out.println(shortA[N]);
    }

    static class Cow implements Comparable<Cow>{
        int e;
        int w;

        public Cow(int e, int w){
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(Cow o){
            return this.w-o.w;
        }
    }
}