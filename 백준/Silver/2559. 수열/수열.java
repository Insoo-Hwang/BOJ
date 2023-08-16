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
        st = new StringTokenizer(br.readLine());
        int A [] = new int[N+1];
        for(int i = 1; i < N+1; i++){
            A[i] = A[i-1] + Integer.parseInt(st.nextToken());
        }

        int max = Integer.MIN_VALUE;
        for(int i = K; i < N+1; i++){
            max = Math.max(max, A[i] - A[i-K]);
        }
        System.out.println(max);
    }
}