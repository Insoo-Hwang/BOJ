import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[] A;
    static int [] first = {0, 0, 1, 1, 2, 2};
    static int [] second = {1, 2, 0, 2, 0, 1};
    static int [] third = {2, 1, 2, 0, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        A = new int[3];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }
        System.out.println(BFS());
    }

    static int BFS(){
        boolean [][][] visited = new boolean[61][61][61];
        visited[A[0]][A[1]][A[2]] = true;
        Queue<int []> queue = new LinkedList<>();
        queue.add(new int[] {A[0], A[1], A[2], 0});
        while(!queue.isEmpty()){
            int [] now = queue.poll();
            if(now[0] == 0 && now[1] == 0 && now[2] == 0) return now[3];
            for(int i = 0; i < 6; i++){
                int f = Math.max(0, now[first[i]]-9);
                int s = Math.max(0, now[second[i]]-3);
                int t = Math.max(0, now[third[i]]-1);
                if(visited[f][s][t]) continue;
                queue.add(new int[] {f, s, t, now[3]+1});
                visited[f][s][t] = true;
            }
        }
        return 0;
    }
}