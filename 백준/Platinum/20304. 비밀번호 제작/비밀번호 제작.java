import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static boolean [] visited;
    static Queue<int []> queue = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(br.readLine());
        visited = new boolean[1000001];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < M; i++){
            int temp = Integer.parseInt(st.nextToken());
            visited[temp] = true;
            queue.add(new int[] {temp, 0});
        }
        System.out.println(BFS());
    }

    static int BFS(){
        int max = 0;
        while(!queue.isEmpty()){
            int [] now = queue.poll();
            for(int i = 0; i < 21; i++){
                int safeNum = now[0]^(1<<i);
                if(safeNum > N || visited[safeNum]) continue;
                visited[safeNum] = true;
                queue.add(new int[] {safeNum, now[1]+1});
                max = Math.max(max, now[1]+1);
            }
        }
        return max;
    }
}