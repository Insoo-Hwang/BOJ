import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int [] sight = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            sight[i] = Integer.parseInt(st.nextToken());
        }
        sight[N-1] = 0;
        ArrayList<BackDoor> [] A = new ArrayList[N];
        for(int i = 0; i < N; i++){
            A[i] = new ArrayList<>();
        }
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            A[s].add(new BackDoor(e, w));
            A[e].add(new BackDoor(s, w));
        }
        long [] shortA = new long[N];
        Arrays.fill(shortA, Long.MAX_VALUE);
        shortA[0] = 0;
        boolean [] visited = new boolean[N];
        PriorityQueue<BackDoor> queue = new PriorityQueue<>();
        queue.add(new BackDoor(0, 0));
        while(!queue.isEmpty()){
            BackDoor now = queue.poll();
            if(visited[now.e]) continue;
            visited[now.e] = true;
            for(int i = 0; i < A[now.e].size(); i++){
                BackDoor temp = A[now.e].get(i);
                if(sight[temp.e] == 1) continue;
                if(shortA[temp.e] > shortA[now.e]+temp.w){
                    shortA[temp.e] = shortA[now.e]+temp.w;
                    queue.add(new BackDoor(temp.e, shortA[temp.e]));
                }
            }
        }
        if(shortA[N-1] == Long.MAX_VALUE) System.out.println(-1);
        else System.out.println(shortA[N-1]);
    }

    static class BackDoor implements Comparable<BackDoor>{
        int e;
        long w;

        public BackDoor(int e, long w){
            this.e = e;
            this.w = w;
        }

        @Override
        public int compareTo(BackDoor o){
            return (int)(this.w-o.w);
        }
    }
}