import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());
        int [] A = new int[T+1];
        for(int i = 1; i < T+1; i++){
            A[i] = Integer.parseInt(br.readLine());
        }
        int [][][] D = new int[T+1][3][W+2];
        if(A[1] == 1){
            D[1][1][0] = 1;
            D[1][2][1] = 0;
        }
        else{
            D[1][1][1] = 0;
            D[1][2][1] = 1;
        }
        for(int i = 1; i < T; i++){
            if(A[i+1] == 1){
                for(int j = 0; j < W+1; j++){
                    D[i+1][1][j] = Math.max(D[i+1][1][j], D[i][1][j]+1);
                    D[i+1][2][j] = Math.max(D[i+1][2][j], D[i][2][j]);
                    if(j != 0){
                        D[i+1][1][j] = Math.max(D[i+1][1][j], D[i][2][j-1]+1);
                        D[i+1][2][j] = Math.max(D[i+1][2][j], D[i][1][j-1]);
                    }
                }
            }
            else{
                for(int j = 0; j < W+1; j++){
                    D[i+1][2][j] = Math.max(D[i+1][2][j], D[i][2][j]+1);
                    D[i+1][1][j] = Math.max(D[i+1][1][j], D[i][1][j]);
                    if(j != 0){
                        D[i+1][2][j] = Math.max(D[i+1][2][j], D[i][1][j-1]+1);
                        D[i+1][1][j] = Math.max(D[i+1][1][j], D[i][2][j-1]);
                    }
                }
            }
        }
        int max = 0;
        for(int i = 1; i < 3; i++){
            for(int j = 0; j < W+1; j++){
                max = Math.max(max, D[T][i][j]);
            }
        }
        System.out.println(max);
    }
}