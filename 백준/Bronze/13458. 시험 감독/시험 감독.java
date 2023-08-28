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
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++){
            A[i] -= B;
        }
        long cnt = N;
        for(int i = 0; i < N; i++){
            if(A[i] < 0) continue;
            if(A[i]%C != 0){
                cnt++;
            }
            cnt+=A[i]/C;
        }
        System.out.println(cnt);
    }
}