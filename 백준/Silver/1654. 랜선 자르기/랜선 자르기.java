import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int [] A = new int[K];
        for(int i = 0; i < K; i++){
            A[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(A);

        long start = 0;
        long end = A[K-1];
        long ans = 0;
        while(start <= end){
            long mid = (start + end)/2;
            if(mid == 0){
                ans = 1;
                break;
            }
            long temp = 0;
            for(int i = 0; i < K; i++){
                temp += A[i]/mid;
            }
            if(temp < N){
                end = mid - 1;
            }
            else{
                start = mid + 1;
                ans = Math.max(ans, mid);
            }
        }
        System.out.println(ans);
    }
}