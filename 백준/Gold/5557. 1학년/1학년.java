import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int [] A = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) A[i] = Integer.parseInt(st.nextToken());
        long [][] D = new long[N][21];
        D[0][A[0]] = 1;
        for(int i = 0; i < N-1; i++){
            for(int j = 0; j < 21; j++){
                if(D[i][j] == 0) continue;
                if(j+A[i+1] >=0 && j+A[i+1] <= 20){
                    D[i+1][j+A[i+1]]+=D[i][j];
                }
                if(j-A[i+1] >= 0 && j-A[i+1] <= 20){
                    D[i+1][j-A[i+1]]+=D[i][j];
                }
            }
        }
        System.out.println(D[N-2][A[N-1]]);
    }
}