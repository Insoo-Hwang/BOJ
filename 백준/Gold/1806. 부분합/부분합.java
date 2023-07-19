import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int A[] = new int[N+2];

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i < N+1; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        int min = N+1;
        int s = 1, e = 1, sum = 0;
        while(e <= N+1){
            if(sum >= S){
                sum -= A[s++];
                min = Math.min(min, e-s+1);
            }
            else{
                sum += A[e++];
            }
        }
        if(min == N+1) System.out.println(0);
        else System.out.println(min);
    }
}
