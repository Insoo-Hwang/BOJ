import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        System.out.println(BFS(N, K));
    }

    static int BFS(int N, int K){
        int [] visited = new int[100001];
        Arrays.fill(visited, Integer.MAX_VALUE);
        visited[N] = 0;
        Queue<int []> queue = new LinkedList<>();
        queue.add(new int[] {N, 0});
        int min = Integer.MAX_VALUE;
        while(!queue.isEmpty()){
            int [] now = queue.poll();
            if(now[0] == K) {
                min = Math.min(min, now[1]);
                continue;
            }
            if(now[0] < 100000 && visited[now[0]+1] > now[1]+1){
                visited[now[0]+1] = now[1]+1;
                queue.add(new int[]{now[0]+1, now[1]+1});
            }
            if(now[0]*2 < 100001 && visited[now[0]*2] > now[1]){
                visited[now[0]*2] = now[1];
                queue.add(new int[] {now[0]*2, now[1]});
            }
            if(now[0]-1 >= 0 && visited[now[0]-1] > now[1]+1){
                visited[now[0]-1] = now[1]+1;
                queue.add(new int[] {now[0]-1, now[1]+1});
            }
        }
        return min;
    }
}