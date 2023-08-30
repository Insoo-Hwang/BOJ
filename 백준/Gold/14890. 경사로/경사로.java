import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int [][] A = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;
        boolean [][] B = new boolean[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 1; j < N; j++){
                int prev = A[j-1][i];
                int now = A[j][i];
                if(prev == now){
                    if(j == N-1) cnt++;
                    continue;
                }
                else if(Math.abs(prev-now) > 1) break;
                boolean check = false;
                if(prev-now == -1){
                    for (int k = 0; k < L; k++) {
                        if (j-1-k < 0) break;
                        if (A[j-1-k][i] != prev) break;
                        if (B[j-1-k][i]) break;
                        if (k == L-1) {
                            for (int h = 0; h < L; h++) {
                                B[j-1-h][i] = true;
                            }
                            check = true;
                        }
                    }
                }
                else{
                    for (int k = 0; k < L; k++) {
                        if (j+k > N-1) break;
                        if (A[j+k][i] != now) break;
                        if (B[j+k][i]) break;
                        if (k == L-1) {
                            for (int h = 0; h < L; h++) {
                                B[j+h][i] = true;
                            }
                            check = true;
                        }
                    }
                }
                if(!check) break;
                if(j == N-1) cnt++;
            }
        }
        B = new boolean[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 1; j < N; j++){
                int prev = A[i][j-1];
                int now = A[i][j];
                if(prev == now){
                    if(j == N-1) cnt++;
                    continue;
                }
                else if(Math.abs(prev-now) > 1) break;
                boolean check = false;
                if(prev-now == -1){
                    for (int k = 0; k < L; k++) {
                        if (j-1-k < 0) break;
                        if (A[i][j-1-k] != prev) break;
                        if (B[i][j-1-k]) break;
                        if (k == L-1) {
                            for (int h = 0; h < L; h++) {
                                B[i][j-1-h] = true;
                            }
                            check = true;
                        }
                    }
                }
                else{
                    for (int k = 0; k < L; k++) {
                        if (j+k > N-1) break;
                        if (A[i][j+k] != now) break;
                        if (B[i][j+k]) break;
                        if (k == L-1) {
                            for (int h = 0; h < L; h++) {
                                B[i][j+h] = true;
                            }
                            check = true;
                        }
                    }
                }
                if(!check) break;
                if(j == N-1) cnt++;
            }
        }
        System.out.println(cnt);
    }
}