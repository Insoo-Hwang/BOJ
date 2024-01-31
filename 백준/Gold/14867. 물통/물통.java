import java.io.*;
import java.util.*;

public class Main {
    static int a;
    static int b;
    static int c;
    static int d;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        if(!BFS()) System.out.println(-1);
    }

    static boolean BFS(){
        boolean [][] visited = new boolean[a+1][b+1];
        visited[0][0] = true;
        Queue<int []> queue = new LinkedList<>();
        queue.add(new int [] {0, 0, 0});
        while(!queue.isEmpty()){
            int [] now = queue.poll();
            if(now[0] == c && now[1] == d){
                System.out.println(now[2]);
                return true;
            }
            if(!visited[a][now[1]]){
                visited[a][now[1]] = true;
                queue.add(new int [] {a, now[1], now[2]+1});
            }
            if(!visited[0][now[1]]){
                visited[0][now[1]] = true;
                queue.add(new int [] {0, now[1], now[2]+1});
            }
            int temp1 = Math.max(0, now[0]+now[1]-b);
            int temp2 = Math.min(now[0]+now[1], b);
            if(!visited[temp1][temp2]){
                visited[temp1][temp2] = true;
                queue.add(new int [] {temp1, temp2, now[2]+1});
            }
            if(!visited[now[0]][b]){
                visited[now[0]][b] = true;
                queue.add(new int [] {now[0], b, now[2]+1});
            }
            if(!visited[now[0]][0]){
                visited[now[0]][0] = true;
                queue.add(new int [] {now[0], 0, now[2]+1});
            }
            temp1 = Math.min(now[0]+now[1], a);
            temp2 = Math.max(0, now[0]+now[1]-a);
            if(!visited[temp1][temp2]){
                visited[temp1][temp2] = true;
                queue.add(new int [] {temp1, temp2, now[2]+1});
            }
        }
        return false;
    }
}