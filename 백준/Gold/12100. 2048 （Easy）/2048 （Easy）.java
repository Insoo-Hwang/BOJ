import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int max = 0;
    static int tempmax;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int [][] A = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, A[i][j]);
            }
        }
        DFS(A, 0, max, "");
        System.out.println(max);
    }

    static void DFS(int [][] A, int d, int m, String s){
        if(d == 5) return;
        tempmax = m;
        int [][] tempA = up(copy(A));
        if(Math.pow(2, 9-d)*tempmax >= max) DFS(tempA, d+1, tempmax, s+"u");
        tempmax = m;
        tempA = down(copy(A));
        if(Math.pow(2, 9-d)*tempmax >= max) DFS(tempA, d+1, tempmax, s+"d");
        tempmax = m;
        tempA = left(copy(A));
        if(Math.pow(2, 9-d)*tempmax >= max) DFS(tempA, d+1, tempmax, s+"l");
        tempmax = m;
        tempA = right(copy(A));
        if(Math.pow(2, 9-d)*tempmax >= max) DFS(tempA, d+1, tempmax, s+"r");
    }

    static int [][] up(int [][] A){
        boolean [][] visited = new boolean[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(A[j][i] == 0) continue;
                int temp = j-1;
                while(temp > -1){
                    if(A[temp][i] != 0) break;
                    A[temp][i] = A[temp+1][i];
                    A[temp+1][i] = 0;
                    temp--;
                }
                if(temp+1 > 0 && A[temp][i] == A[temp+1][i] && !visited[temp][i]){
                    A[temp][i]*=2;
                    A[temp+1][i] = 0;
                    visited[temp][i] = true;
                    max = Math.max(max, A[temp][i]);
                    tempmax = Math.max(tempmax, A[temp][i]);
                }
            }
        }
        return A;
    }

    static int [][] down(int [][] A){
        boolean [][] visited = new boolean[N][N];
        for(int i = 0; i < N; i++){
            for(int j = N-1; j > -1; j--){
                if(A[j][i] == 0) continue;
                int temp = j+1;
                while(temp < N){
                    if(A[temp][i] != 0) break;
                    A[temp][i] = A[temp-1][i];
                    A[temp-1][i] = 0;
                    temp++;
                }
                if(temp-1 < N-1 && A[temp][i] == A[temp-1][i] && !visited[temp][i]){
                    A[temp][i]*=2;
                    A[temp-1][i] = 0;
                    visited[temp][i] = true;
                    max = Math.max(max, A[temp][i]);
                    tempmax = Math.max(tempmax, A[temp][i]);
                }
            }
        }
        return A;
    }

    static int [][] left(int [][] A){
        boolean [][] visited = new boolean[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(A[i][j] == 0) continue;
                int temp = j-1;
                while(temp > -1){
                    if(A[i][temp] != 0) break;
                    A[i][temp] = A[i][temp+1];
                    A[i][temp+1] = 0;
                    temp--;
                }
                if(temp+1 > 0 && A[i][temp] == A[i][temp+1] && !visited[i][temp]){
                    A[i][temp]*=2;
                    A[i][temp+1] = 0;
                    visited[i][temp] = true;
                    max = Math.max(max, A[i][temp]);
                    tempmax = Math.max(tempmax, A[i][temp]);
                }
            }
        }
        return A;
    }

    static int [][] right(int [][] A){
        boolean [][] visited = new boolean[N][N];
        for(int i = 0; i < N; i++){
            for(int j = N-1; j > -1; j--){
                if(A[i][j] == 0) continue;
                int temp = j+1;
                while(temp < N){
                    if(A[i][temp] != 0) break;
                    A[i][temp] = A[i][temp-1];
                    A[i][temp-1] = 0;
                    temp++;
                }
                if(temp-1 < N-1 && A[i][temp] == A[i][temp-1] && !visited[i][temp]){
                    A[i][temp]*=2;
                    A[i][temp-1] = 0;
                    visited[i][temp] = true;
                    max = Math.max(max, A[i][temp]);
                    tempmax = Math.max(tempmax, A[i][temp]);
                }
            }
        }
        return A;
    }

    static int [][] copy(int [][] A){
        int [][] B = new int[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                B[i][j] = A[i][j];
            }
        }
        return B;
    }
}