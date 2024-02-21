import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());
        Munjae [] A = new Munjae[N+1];
        for(int i = 1; i < N+1; i++){
            st = new StringTokenizer(br.readLine());
            A[i] = new Munjae(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        int [][] D = new int[N+1][T+1];
        for(int i = 1; i < N+1; i++){
            for(int j = 1; j < T+1; j++){
                D[i][j] = D[i-1][j];
                if(j-A[i].K >= 0){
                    D[i][j] = Math.max(D[i][j], D[i-1][j-A[i].K]+A[i].S);
                }
            }
        }
        System.out.println(D[N][T]);
    }

    static class Munjae{
        int K;
        int S;

        public Munjae(int K, int S){
            this.K = K;
            this.S = S;
        }
    }
}