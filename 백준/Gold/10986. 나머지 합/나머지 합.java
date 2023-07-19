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
        long A[] = new long[N+1];
        long B[] = new long[M];
        B[0] = 1;

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i < N+1; i++){
            long temp = Long.parseLong(st.nextToken());
            A[i] = (A[i-1] + temp)%M;
            B[(int)A[i]]++;
        }

        long ans = 0;
        for(int j = 0; j < M; j++){
            ans += (B[j]*(B[j]-1)/2);
        }

        System.out.println(ans);
    }
}
