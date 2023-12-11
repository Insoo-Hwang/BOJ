import java.util.*;
import java.io.*;

public class Main {
    static int [] A;
    static int min = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        A = new int[101];
        for(int i = 0; i < N+M; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            A[s] = e;
        }
        BFS();
        System.out.println(min);
    }

    static void BFS(){
        Queue<int []> queue = new LinkedList<>();
        queue.add(new int [] {1, 0});
        boolean [] visited = new boolean[101];
        visited[1] = true;
        while(!queue.isEmpty()){
            int [] now = queue.poll();
            int nowloc = now[0];
            int cnt = now[1];
            if(nowloc == 100){
                min = cnt;
                return;
            }
            for(int i = 1; i < 7; i++){
                if(nowloc+i > 100) break;
                if(A[nowloc+i] != 0 && !visited[A[nowloc+i]]){
                    queue.add(new int[] {A[nowloc+i], cnt+1});
                    visited[A[nowloc+i]] = true;
                }
                else if(A[nowloc+i] == 0 && !visited[nowloc+i]){
                    queue.add(new int[] {nowloc+i, cnt+1});
                    visited[nowloc+i] = true;
                }
            }
        }
    }
}