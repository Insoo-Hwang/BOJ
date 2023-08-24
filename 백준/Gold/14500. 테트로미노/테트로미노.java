import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int [][] A = new int[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int max = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M-3; j++){
                int temp = 0;
                temp += (A[i][j] + A[i][j+1] + A[i][j+2] + A[i][j+3]);
                max = Math.max(max, temp);
            }
        }
        for(int i = 0; i < N-3; i++){
            for(int j = 0; j < M; j++){
                int temp = 0;
                temp += (A[i][j] + A[i+1][j] + A[i+2][j] + A[i+3][j]);
                max = Math.max(max, temp);
            }
        }
        for(int i = 0; i < N-1; i++){
            for(int j = 0; j < M-1; j++){
                int temp = 0;
                temp += (A[i][j] + A[i+1][j] + A[i][j+1] + A[i+1][j+1]);
                max = Math.max(max, temp);
            }
        }
        for(int i = 0; i < N-2; i++){
            for(int j = 0; j < M-1; j++){
                int temp = 0;
                temp += (A[i][j] + A[i][j+1] + A[i+1][j+1] + A[i+2][j+1]);
                max = Math.max(max, temp);
                temp = 0;
                temp += (A[i][j] + A[i][j+1] + A[i+1][j] + A[i+2][j]);
                max = Math.max(max, temp);
                temp = 0;
                temp += (A[i][j+1] + A[i+1][j+1] + A[i+2][j] + A[i+2][j+1]);
                max = Math.max(max, temp);
                temp = 0;
                temp += (A[i][j] + A[i+1][j] + A[i+2][j] + A[i+2][j+1]);
                max = Math.max(max, temp);
                temp = 0;
                temp += (A[i][j] + A[i+1][j] + A[i+1][j+1] + A[i+2][j+1]);
                max = Math.max(max, temp);
                temp = 0;
                temp += (A[i][j+1] + A[i+1][j] + A[i+1][j+1] + A[i+2][j]);
                max = Math.max(max, temp);
                temp = 0;
                temp += (A[i][j+1] + A[i+1][j] + A[i+1][j+1] + A[i+2][j+1]);
                max = Math.max(max, temp);
                temp = 0;
                temp += (A[i][j] + A[i+1][j] + A[i+1][j+1] + A[i+2][j]);
                max = Math.max(max, temp);
            }
        }
        for(int i = 0; i < N-1; i++){
            for(int j = 0; j < M-2; j++){
                int temp = 0;
                temp += (A[i][j+2] + A[i+1][j] + A[i+1][j+1] + A[i+1][j+2]);
                max = Math.max(max, temp);
                temp = 0;
                temp += (A[i][j] + A[i+1][j] + A[i+1][j+1] + A[i+1][j+2]);
                max = Math.max(max, temp);
                temp = 0;
                temp += (A[i][j] + A[i][j+1] + A[i][j+2] + A[i+1][j]);
                max = Math.max(max, temp);
                temp = 0;
                temp += (A[i][j] + A[i][j+1] + A[i][j+2] + A[i+1][j+2]);
                max = Math.max(max, temp);
                temp = 0;
                temp += (A[i][j] + A[i][j+1] + A[i+1][j+1] + A[i+1][j+2]);
                max = Math.max(max, temp);
                temp = 0;
                temp += (A[i][j+1] + A[i][j+2] + A[i+1][j] + A[i+1][j+1]);
                max = Math.max(max, temp);
                temp = 0;
                temp += (A[i][j+1] + A[i+1][j] + A[i+1][j+1] + A[i+1][j+2]);
                max = Math.max(max, temp);
                temp = 0;
                temp += (A[i][j] + A[i][j+1] + A[i][j+2] + A[i+1][j+1]);
                max = Math.max(max, temp);
            }
        }
        System.out.println(max);
    }
}