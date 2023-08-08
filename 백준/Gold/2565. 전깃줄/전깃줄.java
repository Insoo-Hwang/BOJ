import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int [] A = new int[501];
        int start = 501;
        int end = 0;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            A[a] = b;
            start = Math.min(start, a);
            end = Math.max(end, a);
        }

        int [] D = new int[501];
        int max = Integer.MIN_VALUE;
        for(int i = start; i < end+1; i++){
            D[i] = 1;
            if(A[i] == 0) continue;
            for(int j = start; j < i; j++){
                if(A[j] == 0) continue;
                if(A[i] > A[j]){
                    D[i] = Math.max(D[i], D[j] + 1);
                }
            }
            max = Math.max(max, D[i]);
        }
        System.out.println(N-max);
    }
}