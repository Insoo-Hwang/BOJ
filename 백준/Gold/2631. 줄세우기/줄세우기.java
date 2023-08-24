import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int [] A = new int[N];
        int [] D = new int[N];
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(br.readLine());
        }

        D[0] = 1;
        int max = 0;
        for(int i = 1; i < N; i++){
            D[i] = 1;
            for(int j = 0; j < i; j++){
                if(A[i] > A[j]){
                    D[i] = Math.max(D[i], D[j]+1);
                }
            }
            max = Math.max(max, D[i]);
        }
        System.out.println(N-max);
    }
}