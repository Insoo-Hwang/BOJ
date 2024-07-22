import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int [] A = new int[N+k-1];
        int [] B = new int[d+1];
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(br.readLine());
        }
        for(int i = 0; i < k-1; i++){
            A[N+i] = A[i];
        }

        int tempmax = 1;
        B[c] = 1;
        for(int i = 0; i < k; i++){
            if(B[A[i]] == 0){
                tempmax++;
            }
            B[A[i]]++;
        }
        int max = tempmax;
        int end = k;
        for(int i = 1; i < N; i++){
            B[A[i-1]]--;
            if(B[A[i-1]] == 0){
                tempmax--;
            }
            if(B[A[end]] == 0){
                tempmax++;
            }
            B[A[end]]++;
            end++;
            max = Math.max(max, tempmax);
        }
        System.out.println(max);
    }
}