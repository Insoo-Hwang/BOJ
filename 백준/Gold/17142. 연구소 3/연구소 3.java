import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int [][] A;
    static int [][] virus;
    static int tempV;
    static int [] dy = {-1, 1, 0, 0};
    static int [] dx = {0, 0, -1, 1};
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N][N];
        virus = new int[N*N][2];
        tempV = 0;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                int temp = Integer.parseInt(st.nextToken());
                if(temp == 2){
                    virus[tempV][0] = i;
                    virus[tempV][1] = j;
                    tempV++;
                }
                A[i][j] = temp;
            }
        }

        DFS(0, 0);
        if(min == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(min);
    }
    static void DFS(int d, int s){
        if(d == M){
            BFS();
            return;
        }
        if(s == tempV) return;

        A[virus[s][0]][virus[s][1]] = 3;
        DFS(d+1, s+1);
        A[virus[s][0]][virus[s][1]] = 2;
        DFS(d, s+1);
    }

    static void BFS(){
        boolean [][] visited = new boolean[N][N];
        Queue<Virus> queue = new LinkedList<>();
        int check = N*N;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(A[i][j] == 3){
                    visited[i][j] = true;
                    queue.add(new Virus(i, j, 0));
                    check--;
                }
                else if(A[i][j] == 2){
                    check--;
                }
                else if(A[i][j] == 1){
                    visited[i][j] = true;
                    check--;
                }
            }
        }

        int max = 0;
        while(!queue.isEmpty()){
            Virus now = queue.poll();
            for(int i = 0; i < 4; i++){
                int y = now.n + dy[i];
                int x = now.m + dx[i];
                if(y < 0 || y > N-1 || x < 0 || x > N-1 || visited[y][x]) continue;
                visited[y][x] = true;
                queue.add(new Virus(y, x, now.t+1));
                if(A[y][x] != 2){
                    max = Math.max(max, now.t+1);
                    check--;
                }
            }
        }
        if(check == 0){
            min = Math.min(max, min);
        }
    }

    static class Virus{
        int n;
        int m;
        int t;
        public Virus(int n, int m, int t){
            this.n = n;
            this.m = m;
            this.t = t;
        }
    }
}