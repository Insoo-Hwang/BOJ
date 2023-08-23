import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        int [][] A = new int[R][C];
        int [] air = new int[2];
        int tempB = 0;
        for(int i = 0; i < R; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < C; j++){
                int temp = Integer.parseInt(st.nextToken());
                A[i][j] = temp;
                if(temp == -1){
                    air[tempB] = i;
                    tempB++;
                }
            }
        }

        while(T-->0){
            int [][] B = new int[R][C];
            for(int i = 0; i < R; i++){
                for(int j = 0; j < C; j++){
                    B[i][j] = A[i][j];
                }
            }
            for(int i = 0; i < R; i++){
                for(int j = 0; j < C; j++){
                    if(A[i][j] < 1) continue;
                    if(i != 0 && A[i-1][j] != -1){
                        int temp = A[i][j]/5;
                        B[i-1][j] += temp;
                        B[i][j] -= temp;
                    }
                    if(i != R-1 && A[i+1][j] != -1){
                        int temp = A[i][j]/5;
                        B[i+1][j] += temp;
                        B[i][j] -= temp;
                    }
                    if(j != 0 && A[i][j-1] != -1){
                        int temp = A[i][j]/5;
                        B[i][j-1] += temp;
                        B[i][j] -= temp;
                    }
                    if(j != C-1 && A[i][j+1] != -1){
                        int temp = A[i][j]/5;
                        B[i][j+1] += temp;
                        B[i][j] -= temp;
                    }
                }
            }
            for(int i = air[0]-1; i > 0; i--){
                B[i][0] = B[i-1][0];
            }
            for(int i = 0; i < C-1; i++){
                B[0][i] = B[0][i+1];
            }
            for(int i = 0; i < air[0]; i++){
                B[i][C-1] = B[i+1][C-1];
            }
            for(int i = C-1; i > 1; i--){
                B[air[0]][i] = B[air[0]][i-1];
            }
            B[air[0]][1] = 0;
            for(int i = air[1]+1; i < R-1; i++){
                B[i][0] = B[i+1][0];
            }
            for(int i = 0; i < C-1; i++){
                B[R-1][i] = B[R-1][i+1];
            }
            for(int i = R-1; i > air[1]; i--){
                B[i][C-1] = B[i-1][C-1];
            }
            for(int i = C-1; i > 1; i--){
                B[air[1]][i] = B[air[1]][i-1];
            }
            B[air[1]][1] = 0;
            A = B;
        }
        int cnt = 0;
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                if(A[i][j] == -1) continue;
                cnt += A[i][j];
            }
        }
        System.out.println(cnt);
    }
}