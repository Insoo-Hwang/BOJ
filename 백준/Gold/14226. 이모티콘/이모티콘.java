import java.io.*;
import java.util.*;

public class Main {
    static int S;
    static int [][] D;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        S = Integer.parseInt(st.nextToken());
        D = new int[2*S][2*S];
        for(int i = 0; i < 2*S; i++) Arrays.fill(D[i], Integer.MAX_VALUE);
        System.out.println(BFS());
    }

    static int BFS(){
        Queue<int []> queue = new LinkedList<>();
        queue.add(new int[] {1, 0, 0});
        D[1][0] = 0;
        while(!queue.isEmpty()){
            int [] now = queue.poll();
            if(now[0] == S) return now[2];
            if(D[now[0]][now[0]] == Integer.MAX_VALUE){
                D[now[0]][now[0]] = now[2]+1;
                queue.add(new int[] {now[0], now[0], now[2]+1});
            }
            if(now[0]+now[1] < 2*S && D[now[0]+now[1]][now[1]] == Integer.MAX_VALUE){
                D[now[0]+now[1]][now[1]] = now[2]+1;
                queue.add(new int[] {now[0]+now[1], now[1], now[2]+1});
            }
            if(now[0] > 0 && D[now[0]-1][now[1]] == Integer.MAX_VALUE){
                D[now[0]-1][now[1]] = now[2]+1;
                queue.add(new int[] {now[0]-1, now[1], now[2]+1});
            }
        }
        return -1;
    }
}