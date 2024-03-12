import java.io.*;
import java.util.*;

public class Main {
    static int K;
    static int N;
    static int F;
    static boolean [][] relation;
    static int [] travel;
    static boolean check = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        K = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        F = Integer.parseInt(st.nextToken());
        relation = new boolean[N+1][N+1];
        travel = new int[K];
        for(int i = 0; i < F; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            relation[a][b] = true;
            relation[b][a] = true;
        }
        DFS(0, 1);
        if(!check){
            System.out.println(-1);
            return;
        }
        for(int i = 0; i < K; i++){
            sb.append(travel[i]).append("\n");
        }
        System.out.println(sb);
    }

    static void DFS(int d, int start){
        if(d == K){
            for(int i = 0; i < K-1; i++){
                for(int j = i+1; j < K; j++){
                    if(!relation[travel[i]][travel[j]]) return;
                }
            }
            check = true;
            return;
        }

        for(int i = start; i < N+1; i++){
            travel[d] = i;
            if(d > 0 && !relation[travel[d-1]][travel[d]]) continue;
            DFS(d+1, i+1);
            if(check) return;
        }
    }
}