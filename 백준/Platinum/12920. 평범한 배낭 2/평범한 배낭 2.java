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
            while(tempK <= K){ //17인 경우 17개를 모두 저장하는 방법보다 17 = 8 + 4 + 2 + 1 + 2 총 5개만 저장하는 방법이 시간복잡도 측면에서 유리함
                queue.add(new Product(V*tempK, C*tempK));
                K-=tempK;
                tempK*=2;
            }
            if(K != 0){ //17 = 16 + 1 방식을 사용하면 16 + 5 등 17을 넘는 수까지 저장됨
                queue.add(new Product(V*K, C*K));
            }
        }

        int [] D = new int[M+1]; //i무게에서 얻을 수 있는 최대 행복
        Arrays.fill(D,  -1); //방문 여부 확인용
        int max = 0;
        for(Product temp : queue){
            int V = temp.V;
            int C = temp.C;
            for(int i = M; i >= V; i--){
                if(D[i-V] != -1){ //이미 값이 업데이트 된 경우
                    D[i] = Math.max(D[i], D[i-V] + C); //최대 행복값 업데이트
                    max = Math.max(max, D[i]);
                }
            }
            D[V] = Math.max(D[V], C); //1개만 선택한 경우 행복값 업데이트
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
