import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());
        if(2*K > N){
            System.out.println(0);
            return;
        }
        int [][] D = new int[N+1][K+1];
        for(int i = 0; i < N+1; i++){
            D[i][1] = i;

        }
        for(int i = 4; i < N+1; i++){
            for(int j = 2; j < K+1; j++){
                D[i][j] = (D[i-2][j-1]+D[i-1][j])%1000000003;
            }
        }
        System.out.println(D[N][K]);
    }
}