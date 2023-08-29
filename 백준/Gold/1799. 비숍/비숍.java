import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static boolean [][] A;
    static int [][] black;
    static int [][] white;
    static int tempB = 0;
    static int tempW = 0;
    static int maxB = 0;
    static int maxW = 0;
    static int avaB = 0;
    static int avaW = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        A = new boolean[N][N];
        black = new int[N*N/2+1][2];
        white = new int[N*N/2+1][2];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                int temp = Integer.parseInt(st.nextToken());
                if(temp == 0){
                    A[i][j] = true;
                }
                else{
                    if((i+j)%2 == 0){
                        black[tempB][0] = i;
                        black[tempB][1] = j;
                        tempB++;
                        avaB++;
                    }
                    else {
                        white[tempW][0] = i;
                        white[tempW][1] = j;
                        tempW++;
                        avaW++;
                    }
                }
            }
        }

        boolean [][] copy = new boolean[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                copy[i][j] = A[i][j];
            }
        }
        DFSB(0, 0);
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                A[i][j] = copy[i][j];
            }
        }
        DFSW(0, 0);
        System.out.println(maxB + maxW);
    }

    static void DFSB(int s, int n){
        if(avaB == 0 || s == tempB){
            maxB = Math.max(maxB, n);
            return;
        }
        boolean [][] copy = new boolean[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                copy[i][j] = A[i][j];
            }
        }
        int copyAvailable = avaB;

        for(int i = s; i < tempB; i++){
            if(!A[black[i][0]][black[i][1]]){
                BishopB(black[i][0], black[i][1]);
                DFSB(i+1, n+1);
                for(int j = 0; j < N; j++){
                    for(int k = 0; k < N; k++){
                        A[j][k] = copy[j][k];
                    }
                }
                avaB = copyAvailable;
            }
        }
    }

    static void BishopB(int n, int m){
        A[n][m] = true;
        int temp = 1;
        for(int i = m-1; i > -1; i--){
            if(n-temp > -1){
                if(!A[n-temp][i]) {
                    A[n - temp][i] = true;
                    avaB--;
                }
            }
            if(n+temp < N){
                if(!A[n+temp][i]) {
                    A[n + temp][i] = true;
                    avaB--;
                }
            }
            temp++;
        }
        temp = 1;
        for(int i = m+1; i < N; i++){
            if(n-temp > -1){
                if(!A[n-temp][i]) {
                    A[n - temp][i] = true;
                    avaB--;
                }
            }
            if(n+temp < N){
                if(!A[n+temp][i]) {
                    A[n + temp][i] = true;
                    avaB--;
                }
            }
            temp++;
        }
    }
    static void DFSW(int s, int n){
        if(avaW == 0 || s == tempW){
            maxW = Math.max(maxW, n);
            return;
        }
        boolean [][] copy = new boolean[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                copy[i][j] = A[i][j];
            }
        }
        int copyAvailable = avaW;

        for(int i = s; i < tempW; i++){
            if(!A[white[i][0]][white[i][1]]){
                BishopW(white[i][0], white[i][1]);
                DFSW(i+1, n+1);
                for(int j = 0; j < N; j++){
                    for(int k = 0; k < N; k++){
                        A[j][k] = copy[j][k];
                    }
                }
                avaW = copyAvailable;
            }
        }
    }

    static void BishopW(int n, int m){
        A[n][m] = true;
        int temp = 1;
        for(int i = m-1; i > -1; i--){
            if(n-temp > -1){
                if(!A[n-temp][i]) {
                    A[n - temp][i] = true;
                    avaW--;
                }
            }
            if(n+temp < N){
                if(!A[n+temp][i]) {
                    A[n + temp][i] = true;
                    avaW--;
                }
            }
            temp++;
        }
        temp = 1;
        for(int i = m+1; i < N; i++){
            if(n-temp > -1){
                if(!A[n-temp][i]) {
                    A[n - temp][i] = true;
                    avaW--;
                }
            }
            if(n+temp < N){
                if(!A[n+temp][i]) {
                    A[n + temp][i] = true;
                    avaW--;
                }
            }
            temp++;
        }
    }
}