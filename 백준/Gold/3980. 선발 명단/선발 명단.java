import java.util.*;
import java.io.*;

public class Main {
    static int [][] A = new int[11][11];
    static boolean [] visited;
    static int max;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        while(T-->0){
            for(int i = 0; i < 11; i++){
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < 11; j++){
                    A[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            visited = new boolean[11];
            max = 0;
            for(int i = 0; i < 11; i++){
                if(A[0][i] == 0) continue;
                visited[i] = true;
                DFS(1, A[0][i]);
                visited[i] = false;
            }
            System.out.println(max);
        }
    }

    static void DFS(int d, int sum){
        if(d == 11){
            max = Math.max(max, sum);
            return;
        }

        for(int i = 0; i < 11; i++){
            if(visited[i] || A[d][i] == 0) continue;
            visited[i] = true;
            DFS(d+1, sum+A[d][i]);
            visited[i] = false;
        }
    }
}