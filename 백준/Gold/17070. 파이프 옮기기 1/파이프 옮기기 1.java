import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int [][] A = new int[N+1][N+1];
        for(int i = 1; i < N+1; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1 ; j < N+1; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int [][][] D = new int[N+1][N+1][3];
        D[1][2][0] = 1;
        for(int i = 1; i < N+1; i++){
            for(int j = 1; j < N+1; j++){
                if(i == 1 && j == 2) continue;
                if(A[i][j] == 1) continue;
                D[i][j][0] = D[i][j-1][0] + D[i][j-1][1];
                if(A[i-1][j] != 1 && A[i][j-1] != 1){
                    D[i][j][1] = D[i-1][j-1][0] + D[i-1][j-1][1] + D[i-1][j-1][2];
                }
                D[i][j][2] = D[i-1][j][1] + D[i-1][j][2];
            }
        }
        System.out.println(D[N][N][0] + D[N][N][1] + D[N][N][2]);
    }
}