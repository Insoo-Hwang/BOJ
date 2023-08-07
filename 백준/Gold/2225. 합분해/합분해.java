import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int [][] D = new int[N+1][K+1];
        for(int i = 0; i < N+1; i++){
            D[i][1] = 1;
        }
        Arrays.fill(D[0], 1);

        for(int i = 1; i < N+1; i++){
            for(int j = 2; j < K+1; j++){
                D[i][j] = (D[i-1][j] + D[i][j-1])%1000000000;
            }
        }
        System.out.println(D[N][K]);
    }
}