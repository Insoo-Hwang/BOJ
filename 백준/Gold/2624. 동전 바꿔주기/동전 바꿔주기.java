import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());
        int [][] D = new int[K+1][T+1];
        Coin [] A = new Coin[K+1];
        List<Coin> list = new ArrayList<>();
        for(int i = 1; i < K+1; i++){
            st = new StringTokenizer(br.readLine());
            list.add(new Coin(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
        }
        Collections.sort(list);
        for(int i = 1; i < K+1; i++) A[i] = list.get(i-1);
        D[0][0] = 1;
        for(int i = 1; i < K+1; i++){
            for(int j = 0; j < A[i].n+1; j++){
                for(int k = 0; k < T+1; k++){
                    if(k+A[i].p*j > T) break;
                    D[i][k+A[i].p*j]+=D[i-1][k];
                }
            }
        }
        System.out.println(D[K][T]);
    }

    static class Coin implements Comparable<Coin>{
        int p;
        int n;

        public Coin(int p, int n){
            this.p = p;
            this.n = n;
        }

        @Override
        public int compareTo(Coin o){
            return this.p-o.p;
        }
    }
}