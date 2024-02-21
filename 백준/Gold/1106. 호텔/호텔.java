import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        Promotion [] A = new Promotion[N+1];
        for(int i = 1; i < N+1; i++){
            st = new StringTokenizer(br.readLine());
            A[i] = new Promotion(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        int [][] D = new int[N+1][C+101];
        for(int i = 0; i < N+1; i++) Arrays.fill(D[i], 987654321);
        for(int i = 0; i < N+1; i++) D[i][0] = 0;
        for(int i = 1; i < N+1; i++){
            for(int j = 1; j < C+101; j++){
                D[i][j] = D[i-1][j];
                if(j-A[i].customer >= 0){
                    D[i][j] = Math.min(D[i][j], D[i][j-A[i].customer]+A[i].cost);
                }
            }
        }
        int min = D[N][C];
        for(int i = C+1; i < C+101; i++) min = Math.min(min, D[N][i]);
        System.out.println(min);
    }

    static class Promotion{
        int cost;
        int customer;

        public Promotion(int cost, int customer){
            this.cost = cost;
            this.customer = customer;
        }
    }
}