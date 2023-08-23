import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int [][] A;
    static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        A = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int [][] B = new int[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                B[i][j] = A[i][j];
            }
        }
        move(1, 0);
        A = B;
        move(2, 0);
        A = B;
        move(3, 0);
        A = B;
        move(4, 0);
        System.out.println(max);
    }
    static void move(int n, int d){
        if(d == 5){
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    max = Math.max(max, A[i][j]);
                }
            }
            return;
        }

        if(n == 1){ //위
            int [][] B = new int[N][N];
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    B[i][j] = A[i][j];
                }
            }
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    if(B[i][j] == 0) continue;
                    if(i != N-1){
                        for(int k = i+1; k < N; k++){
                            if(B[i][j] != B[k][j] && B[k][j] != 0){
                                break;
                            }
                            else if(B[i][j] == B[k][j]){
                                B[i][j]*=2;
                                B[k][j] = 0;
                                break;
                            }
                        }
                    }
                    int temp = i-1;
                    while(true){
                        if(temp < 0) break;
                        if(B[temp][j] != 0) break;
                        B[temp][j] = B[temp+1][j];
                        B[temp+1][j] = 0;
                        temp--;
                    }
                }
            }
            A = B;
            move(1, d+1);
            A = B;
            move(2, d+1);
            A = B;
            move(3, d+1);
            A = B;
            move(4, d+1);
        }
        else if(n == 2){ //아래
            int [][] B = new int[N][N];
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    B[i][j] = A[i][j];
                }
            }
            for(int i = N-1; i > -1; i--){
                for(int j = 0; j < N; j++){
                    if(B[i][j] == 0) continue;
                    if(i != 0){
                        for(int k = i-1; k > -1; k--){
                            if(B[i][j] != B[k][j] && B[k][j] != 0){
                                break;
                            }
                            else if(B[i][j] == B[k][j]){
                                B[i][j]*=2;
                                B[k][j] = 0;
                                break;
                            }
                        }
                    }
                    int temp = i+1;
                    while(true){
                        if(temp >= N) break;
                        if(B[temp][j] != 0) break;
                        B[temp][j] = B[temp-1][j];
                        B[temp-1][j] = 0;
                        temp++;
                    }
                }
            }
            A = B;
            move(1, d+1);
            A = B;
            move(2, d+1);
            A = B;
            move(3, d+1);
            A = B;
            move(4, d+1);
        }
        else if(n == 3){ //오른
            int [][] B = new int[N][N];
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    B[i][j] = A[i][j];
                }
            }
            for(int i = 0; i < N; i++){
                for(int j = N-1; j > -1; j--){
                    if(B[i][j] == 0) continue;
                    if(j != 0){
                        for(int k = j-1; k > -1; k--){
                            if(B[i][j] != B[i][k] && B[i][k] != 0){
                                break;
                            }
                            else if(B[i][j] == B[i][k]){
                                B[i][j]*=2;
                                B[i][k] = 0;
                                break;
                            }
                        }
                    }
                    int temp = j+1;
                    while(true){
                        if(temp >= N) break;
                        if(B[i][temp] != 0) break;
                        B[i][temp] = B[i][temp-1];
                        B[i][temp-1] = 0;
                        temp++;
                    }
                }
            }
            A = B;
            move(1, d+1);
            A = B;
            move(2, d+1);
            A = B;
            move(3, d+1);
            A = B;
            move(4, d+1);
        }
        else if(n == 4){ //왼
            int [][] B = new int[N][N];
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    B[i][j] = A[i][j];
                }
            }
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    if(B[i][j] == 0) continue;
                    if(j != N-1){
                        for(int k = j+1; k < N; k++){
                            if(B[i][j] != B[i][k] && B[i][k] != 0){
                                break;
                            }
                            else if(B[i][j] == B[i][k]){
                                B[i][j]*=2;
                                B[i][k] = 0;
                                break;
                            }
                        }
                    }
                    int temp = j-1;
                    while(true){
                        if(temp < 0) break;
                        if(B[i][temp] != 0) break;
                        B[i][temp] = B[i][temp+1];
                        B[i][temp+1] = 0;
                        temp--;
                    }
                }
            }
            A = B;
            move(1, d+1);
            A = B;
            move(2, d+1);
            A = B;
            move(3, d+1);
            A = B;
            move(4, d+1);
        }
    }
}