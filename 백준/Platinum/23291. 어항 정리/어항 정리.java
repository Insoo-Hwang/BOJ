import java.io.*;
import java.util.*;

public class Main {
    static int [][] A;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = new int[10][100];
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            A[9][i] = Integer.parseInt(st.nextToken());
        }
        int cnt = 0;
        while(true){
            int max = 0;
            int min = Integer.MAX_VALUE;
            for(int i = 0; i < N; i++){
                max = Math.max(max, A[9][i]);
                min = Math.min(min, A[9][i]);
            }
            if(max-min <= K) break;
            cnt++;

            for(int i = 0; i < N; i++){
                if(A[9][i] == min) A[9][i]++;
            }

            A[8][1] = A[9][0];
            A[9][0] = 0;
            int t = (int) Math.sqrt(N);
            if(N >= (t+1)*t) t = (t-1)*2;
            else t = (t-1)*2-1;
            int start = 2;
            for(int i = 1; i < t+1; i++){
                int w = i/2+1;
                int h = (i+3)/2;
                for(int j = 1; j < w+1; j++){
                    for(int k = 1; k < h+1; k++){
                        A[9-j][start+k-1] = A[10-k][start-j];
                        A[10-k][start-j] = 0;
                    }
                }
                start+=h;
            }

            fish();

            int [] B = new int[100];
            int btemp = 0;
            for(int i = 0; i < 100; i++){
                for(int j = 9; j > -1; j--){
                    if(A[j][i] == 0) continue;
                    B[btemp] = A[j][i];
                    btemp++;
                }
            }
            A = new int[10][100];
            for(int i = 0; i < N; i++){
                A[9][i] = B[i];
            }

            start = N/2;
            for(int i = 1; i < N/2+1; i++){
                A[8][start-1+i] = A[9][start-i];
                A[9][start-i] = 0;
            }
            for(int i = 0; i < 2; i++){
                for(int j = 0; j < N/4; j++){
                    A[7-i][N-1-j] = A[8+i][start+j];
                    A[8+i][start+j] = 0;
                }
            }

            fish();

            B = new int[100];
            btemp = 0;
            for(int i = 0; i < 100; i++){
                for(int j = 9; j > -1; j--){
                    if(A[j][i] == 0) continue;
                    B[btemp] = A[j][i];
                    btemp++;
                }
            }
            A = new int[10][100];
            for(int i = 0; i < N; i++){
                A[9][i] = B[i];
            }
        }
        System.out.println(cnt);
    }

    static void fish(){
        int [][] B = new int[10][100];
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 100; j++){
                B[i][j] = A[i][j];
            }
        }

        int [] dy = {1, 0};
        int [] dx = {0, 1};
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 100; j++){
                if(A[i][j] == 0) continue;
                for(int k = 0; k < 2; k++){
                    int y = i+dy[k];
                    int x = j+dx[k];
                    if(y > 9 || x > 99 || A[y][x] == 0) continue;
                    if(A[i][j] > A[y][x] && A[i][j]-A[y][x] >= 5){
                        int move = (A[i][j]-A[y][x])/5;
                        B[i][j]-=move;
                        B[y][x]+=move;
                    }
                    else if(A[i][j] < A[y][x] && A[y][x]-A[i][j] >= 5){
                        int move = (A[y][x]-A[i][j])/5;
                        B[y][x]-=move;
                        B[i][j]+=move;
                    }
                }
            }
        }

        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 100; j++){
                A[i][j] = B[i][j];
            }
        }
    }
}