import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        st= new StringTokenizer(br.readLine());
        long [] A = new long[N+2];
        long sum = 0;
        for(int i = 1; i < N+1; i++) {
            A[i] = (long)Integer.parseInt(st.nextToken());
            sum += A[i];
        }
        if(C >= B){
            long temp = B*sum;
            System.out.println(temp);
            return;
        }

        long count = 0;
        for(int i = 1; i < N+1; i++){
            if(A[i-1] > 0){
                if(A[i-1] < A[i] && A[i+1] < A[i]){
                    count += (A[i] - A[i+1]);
                    A[i-1] -= (A[i] - A[i+1]);
                    A[i] = A[i+1];
                    if(A[i-1] > 0){
                        count += A[i-1];
                        A[i] -= A[i-1];
                        A[i+1] -= Math.min(A[i+1], A[i-1]);
                        A[i-1] = 0;
                    }
                }
                else if((A[i-1] < A[i] && A[i+1] > A[i]) || (A[i-1] < A[i] && A[i+1] == A[i])){
                    count += A[i-1];
                    A[i] -= A[i-1];
                    A[i+1] -= A[i-1];
                    A[i-1] = 0;
                }
                else if((A[i-1] == A[i] && A[i+1] < A[i]) || (A[i-1] == A[i] && A[i+1] == A[i])){
                    count += A[i];
                    A[i-1] = 0;
                    A[i+1] = 0;
                    A[i] = 0;
                }
                else if(A[i-1] == A[i] && A[i+1] > A[i]){
                    count += A[i];
                    A[i-1] = 0;
                    A[i+1] -= A[i];
                    A[i] = 0;
                }
            }
            else{
                if(A[i+1] == 0)
                    count += A[i];
                else if(A[i] > A[i+1]){
                    count += (A[i] - A[i+1]);
                    A[i] = A[i+1];
                }
            }
        }
        long temp = sum*C + count*(B-C);
        System.out.println(temp);
    }
}
