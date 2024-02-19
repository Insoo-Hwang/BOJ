import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int [] A = new int[N];
        for(int i = 0; i < N; i++) A[i] = Integer.parseInt(br.readLine());
        Arrays.sort(A);
        int [] D = new int[K+1];
        Arrays.fill(D, Integer.MAX_VALUE);
        D[0] = 0;
        for(int i = 0; i < N; i++){
            for(int j = 1; j < K+1; j++){
                if(A[i] > j || D[j-A[i]] == Integer.MAX_VALUE) continue;
                D[j] = Math.min(D[j], D[j-A[i]]+1);
            }
        }
        if(D[K] == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(D[K]);
    }
}