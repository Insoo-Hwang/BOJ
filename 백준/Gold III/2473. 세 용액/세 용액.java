import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long [] A = new long[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            A[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(A);
        long min = Long.MAX_VALUE;
        long [] ans = new long[3];
        for(int i = 0; i < N-2; i++){
            int start = i+1;
            int end = N-1;
            while(start < end){
                long sum = Math.abs(A[i]+A[start]+A[end]);
                if(sum < min){
                    ans[0] = A[i];
                    ans[1] = A[start];
                    ans[2] = A[end];
                    min = sum;
                }
                if(A[i]+A[start]+A[end] > 0) end--;
                else start++;
            }
        }
        for(int i = 0; i < 3; i++){
            System.out.print(ans[i] + " ");
        }
    }
}