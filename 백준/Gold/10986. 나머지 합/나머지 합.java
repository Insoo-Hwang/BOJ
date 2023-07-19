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
        long A[] = new long[N+1]; //1~i까지의 부분합
        long B[] = new long[M]; //M으로 나눈 나머지의 개수
        B[0] = 1; //처음(0)부터 i까지의 경우의 수

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i < N+1; i++){ //(A-B)%C = A%C-B%C이기 때문에 같은 것끼리 매칭 시킴
            long temp = Long.parseLong(st.nextToken());
            A[i] = (A[i-1] + temp)%M;
            B[(int)A[i]]++;
        }

        long ans = 0;
        for(int j = 0; j < M; j++){
            ans += (B[j]*(B[j]-1)/2); //N개끼리 매칭
        }

        System.out.println(ans);
    }
}
