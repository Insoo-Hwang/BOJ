import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int [][] A;
    static int rockN;
    static int rockM;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        while(T-->0){
            N = Integer.parseInt(br.readLine());
            A = new int[N+1][2];
            for(int i = 0; i < N+1; i++){
                st = new StringTokenizer(br.readLine());
                A[i][0] = Integer.parseInt(st.nextToken());
                A[i][1] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            rockN = Integer.parseInt(st.nextToken());
            rockM = Integer.parseInt(st.nextToken());
            if(BFS()) System.out.println("happy");
            else System.out.println("sad");
        }
    }

    static boolean BFS(){
        boolean [] visited = new boolean[N+1];
        visited[0] = true;
        Queue<Beer> queue = new LinkedList<>();
        queue.add(new Beer(A[0][0], A[0][1]));
        while(!queue.isEmpty()){
            Beer now = queue.poll();
            if(Math.abs(now.n-rockN)+Math.abs(now.m-rockM) <= 1000) return true;
            for(int i = 1; i < N+1; i++){
                if(visited[i] || Math.abs(now.n-A[i][0])+Math.abs(now.m-A[i][1]) > 1000) continue;
                visited[i] = true;
                queue.add(new Beer(A[i][0], A[i][1]));
            }
        }
        return false;
    }
    static class Beer{
        int n;
        int m;
        public Beer(int n, int m){
            this.n = n;
            this.m = m;
        }
    }
}