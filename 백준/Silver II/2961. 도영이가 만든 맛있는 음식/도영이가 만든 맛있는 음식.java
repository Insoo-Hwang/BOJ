import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int [][] A = new int[N][2];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            A[i][0] = S;
            A[i][1] = B;
        }

        long min = Long.MAX_VALUE;
        for(int i = 1; i < (1<<N); i++){
            long S = 1;
            long B = 0;
            for(int j = 0; j < N; j++){
                if((i & (1<<j)) > 0){
                    S*=A[j][0];
                    B+=A[j][1];
                }
            }
            min = Math.min(min, Math.abs(S-B));
        }
        System.out.println(min);
    }
}