import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int [][] A;
    static boolean [] visited;
    static int sum = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        A = new int[N][N];
        visited = new boolean[N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        DFS(0, 0);
        System.out.println(sum);
    }

    static void DFS(int d, int s){
        if(d == N/2){
            min();
            return;
        }
        for(int i = s; i < N; i++){
            if(!visited[i]){
                visited[i] = true;
                DFS(d+1, i);
                visited[i] = false;
            }
        }
    }

    static void min(){
        int cho = 0;
        int opp = 0;
        for(int i = 0; i < N-1; i++){
            for(int j = i+1; j < N; j++){
                if(visited[i] && visited[j]){
                    cho += A[i][j];
                    cho += A[j][i];
                }
                else if(!visited[i] && !visited[j]){
                    opp += A[i][j];
                    opp += A[j][i];
                }
            }
        }
        sum = Math.min(sum, Math.abs(cho - opp));
    }
}