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
        int [][] D = new int[N+1][K+1]; //K개의 수를 사용하여 N을 만드는 총 개수
        for(int i = 0; i < N+1; i++){
            D[i][1] = 1; //1개를 이용하여 수를 만드는 법은 항상 1개
        }
        Arrays.fill(D[0], 1); //0을 만드는 법도 항상 1개

        for(int i = 1; i < N+1; i++){
            for(int j = 2; j < K+1; j++){
                D[i][j] = (D[i-1][j] + D[i][j-1])%1000000000; //0~i라는 수를 만들기 위해 j-1개를 사용한 개수(i-1라는 수를 만들기 위해 j개 사용한 개수 = D[i-1][j], i라는 수를 만들기 위해 j-1개 사용한 개수 = D[i][j-1])와 i라는 수를 만들기 위해 j개를 사용한 개수는 항상 같음(필요한 값만큼 한번만 더해주면 되므로)
            }
        }
        System.out.println(D[N][K]);
    }
}
