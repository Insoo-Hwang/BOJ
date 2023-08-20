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
        Queue<Product> queue = new LinkedList<>();
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int tempK = 1;
            while(tempK <= K){
                queue.add(new Product(V*tempK, C*tempK));
                K-=tempK;
                tempK*=2;
            }
            if(K != 0){
                queue.add(new Product(V*K, C*K));
            }
        }

        int [] D = new int[M+1];
        Arrays.fill(D,  -1);
        int max = 0;
        for(Product temp : queue){
            int V = temp.V;
            int C = temp.C;
            for(int i = M; i >= V; i--){
                if(D[i-V] != -1){
                    D[i] = Math.max(D[i], D[i-V] + C);
                    max = Math.max(max, D[i]);
                }
            }
            D[V] = Math.max(D[V], C);
            max = Math.max(max, D[V]);
        }
        System.out.println(max);
    }
    static class Product{
        int V;
        int C;
        public Product(int V, int C){
            this.V = V;
            this.C = C;
        }
    }
}