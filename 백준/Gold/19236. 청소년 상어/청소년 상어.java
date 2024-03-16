import java.io.*;
import java.util.*;

public class Main {
    static Fish [][] A = new Fish[4][4];
    static Fish [] B = new Fish[17];
    static int [] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int [] dx = {0, -1, -1, -1, 0, 1, 1, 1};
    static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int i = 0; i < 4; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 4; j++){
                int num = Integer.parseInt(st.nextToken());
                int direction = Integer.parseInt(st.nextToken())-1;
                A[i][j] = new Fish(num, direction, i, j);
                B[num] = new Fish(num, direction, i, j);
            }
        }
        DFS(0, 0, 0);
        System.out.println(max);
    }

    static void DFS(int n, int m, int cnt){
        Fish meal = A[n][m];
        A[n][m] = null;
        B[meal.num] = null;

        for(int i = 1; i < 17; i++){
            if(B[i] == null) continue;
            for (int j = 0; j < 8; j++){
                int y = B[i].n+dy[(B[i].d+j)%8];
                int x = B[i].m+dx[(B[i].d+j)%8];
                if(y > 3 || y < 0 || x > 3 || x < 0 || (y == n && x == m)) continue;
                Fish move = new Fish(B[i].num, B[i].d, B[i].n, B[i].m);
                move.d = (B[i].d+j)%8;
                if(A[y][x] == null){
                    move.n = y;
                    move.m = x;
                    A[y][x] = move;
                    A[B[i].n][B[i].m] = null;
                    B[move.num] = move;
                }
                else{
                    Fish change = new Fish(A[y][x].num, A[y][x].d, A[y][x].n, A[y][x].m);
                    change.n = move.n;
                    change.m = move.m;
                    A[change.n][change.m] = change;
                    B[change.num] = change;
                    move.n = y;
                    move.m = x;
                    A[y][x] = move;
                    B[move.num] = move;
                }
                break;
            }
        }

        Fish [][] tempA = new Fish[4][4];
        Fish [] tempB = new Fish[17];
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(A[i][j] == null) continue;
                Fish backup = new Fish(A[i][j].num, A[i][j].d, A[i][j].n, A[i][j].m);
                tempA[i][j] = backup;
            }
        }
        for(int i = 1; i < 17; i++){
            if(B[i] == null) continue;
            Fish backup = new Fish(B[i].num, B[i].d, B[i].n, B[i].m);
            tempB[i] = backup;
        }

        for(int i = 1; i < 4; i++){
            if(n+i*dy[meal.d] > 3 || n+i*dy[meal.d] < 0 || m+i*dx[meal.d] > 3 || m+i*dx[meal.d] < 0){
                max = Math.max(max, cnt+meal.num);
                break;
            }
            if(A[n+i*dy[meal.d]][m+i*dx[meal.d]] == null) continue;
            DFS(n+i*dy[meal.d], m+i*dx[meal.d], cnt+ meal.num);
            for(int j = 0; j < 4; j++){
                for(int k = 0; k < 4; k++){
                    Fish backup = tempA[j][k];
                    A[j][k] = backup;
                }
            }
            for(int j = 1; j < 17; j++){
                Fish backup = tempB[j];
                B[j] = backup;
            }
        }
    }

    static class Fish{
        int num;
        int d;
        int n;
        int m;

        public Fish(int num, int d, int n, int m){
            this.num = num;
            this.d = d;
            this.n = n;
            this.m = m;
        }
    }
}