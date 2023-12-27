import java.util.*;
import java.io.*;

public class Main {
    static int F;
    static int S;
    static int G;
    static int U;
    static int D;
    static int min = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        BFS();
        if(min == -1) System.out.println("use the stairs");
        else System.out.println(min);
    }

    static void BFS(){
        boolean [] visited = new boolean[F+1];
        visited[S] = true;
        Queue<int []> queue = new LinkedList<>();
        queue.add(new int [] {S, 0});
        while(!queue.isEmpty()){
            int [] now = queue.poll();
            if(now[0] == G){
                min = now[1];
                return;
            }
            if(now[0]-D > 0 && !visited[now[0]-D]){
                queue.add(new int [] {now[0]-D, now[1]+1});
                visited[now[0]-D] = true;
            }
            if(now[0]+U < F+1 && !visited[now[0]+U]){
                queue.add(new int [] {now[0]+U, now[1]+1});
                visited[now[0]+U] = true;
            }
        }
    }
}