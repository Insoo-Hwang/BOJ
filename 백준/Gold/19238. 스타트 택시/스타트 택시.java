import jdk.dynalink.beans.StaticClass;

import java.beans.Introspector;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int tempM;
    static int F;
    static int startN;
    static int startM;
    static int [][] A;
    static int [][] C;
    static int [] dy = {-1, 1, 0, 0};
    static int [] dx = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        tempM = M;
        F = Integer.parseInt(st.nextToken());
        A = new int[N][N];
        C = new int[M+1][4];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                int temp = Integer.parseInt(st.nextToken());
                if(temp == 1){
                    temp-=2;
                }
                A[i][j] = temp;
            }
        }
        st = new StringTokenizer(br.readLine());
        startN = Integer.parseInt(st.nextToken())-1;
        startM = Integer.parseInt(st.nextToken())-1;
        for(int i = 1; i < M+1; i++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken())-1;
            int m = Integer.parseInt(st.nextToken())-1;
            A[n][m] = i;
            C[i][0] = n;
            C[i][1] = m;
            C[i][2] = Integer.parseInt(st.nextToken())-1;
            C[i][3] = Integer.parseInt(st.nextToken())-1;
        }

        for(int i = 0; i < M; i++){
            BFS();
            if(tempM+i+1 != M){
                F = -1;
            }
        }
        System.out.println(F);
    }

    static void BFS(){
        if(F == -1) return;
        boolean [][] visited = new boolean[N][N];
        visited[startN][startM] = true;
        Queue<Location> queue = new LinkedList<>();
        queue.add(new Location(startN, startM, 0, false));
        int min = Integer.MAX_VALUE;
        int minN = Integer.MAX_VALUE;
        int minM = Integer.MAX_VALUE;
        int r = Integer.MAX_VALUE;
        while(!queue.isEmpty()){
            Location now = queue.remove();
            if(A[now.n][now.m] > 0 && r == Integer.MAX_VALUE && !now.r){
                r = now.c;
                min = A[now.n][now.m];
                minN = now.n;
                minM = now.m;
            }
            else if(A[now.n][now.m] > 0 && r == now.c && !now.r){
                if(minN == now.n){
                    if(minM > now.m){
                        min = A[now.n][now.m];
                        minM = now.m;
                    }
                }
                else if(minN > now.n){
                    min = A[now.n][now.m];
                    minN = now.n;
                    minM = now.m;
                }
            }
            if((r < now.c || (queue.isEmpty() && min != Integer.MAX_VALUE)) &&!now.r){
                if(F < now.c){
                    F = -1;
                    return;
                }
                F-=r;
                A[minN][minM] = 0;
                queue.clear();
                visited = new boolean[N][N];
                visited[minN][minM] = true;
                queue.add(new Location(minN, minM, 0, true));
                continue;
            }
            if(now.r && now.n == C[min][2] && now.m == C[min][3]){
                if(F < now.c){
                    F = -1;
                    return;
                }
                F+=now.c;
                startN = C[min][2];
                startM = C[min][3];
                tempM--;
                return;
            }
            for(int i = 0; i < 4; i++){
                int y = now.n + dy[i];
                int x = now.m + dx[i];
                if(y > N-1 || y < 0 || x > N-1 || x < 0 || visited[y][x] || A[y][x] == -1) continue;
                visited[y][x] = true;
                queue.add(new Location(y, x, now.c+1, now.r));
            }
        }
    }
    static class Location{
        int n;
        int m;
        int c;
        boolean r;
        public Location(int n, int m, int c, boolean r){
            this.n = n;
            this.m = m;
            this.c = c;
            this.r = r;
        }
    }
}