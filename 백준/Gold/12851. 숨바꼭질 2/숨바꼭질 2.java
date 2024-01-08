import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int K;
    static int time = Integer.MAX_VALUE;
    static int cnt = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        BFS();
        System.out.println(time);
        System.out.println(cnt);
    }

    static void BFS(){
        boolean [] visited = new boolean[100001];
        Queue<int []> queue = new LinkedList<>();
        queue.add(new int [] {N, 0});
        while(!queue.isEmpty()){
            int [] now = queue.poll();
            visited[now[0]] = true;
            if(now[0] == K){
                if(time > now[1]) time = now[1];
                else if(time == now[1]) cnt++;
                continue;
            }
            int [] m = new int[3];
            m[0] = now[0]+1;
            m[1] = now[0]-1;
            m[2] = 2*now[0];
            for(int i = 0; i < 3; i++){
                if(m[i] > 100000 || m[i] < 0 || visited[m[i]]) continue;
                queue.add(new int [] {m[i], now[1]+1});
            }
        }
    }
}