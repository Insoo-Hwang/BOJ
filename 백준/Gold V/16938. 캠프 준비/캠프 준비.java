import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        int X = Integer.parseInt(st.nextToken());
        int [] A = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }
        int cnt = 0;
        for(int i = 1; i < (1<<N); i++){
            if(Integer.bitCount(i) < 2) continue;
            int sum = 0;
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            for(int j = 0; j < N; j++){
                if((i & (1<<j)) > 0){
                    sum+=A[j];
                    max = Math.max(max, A[j]);
                    min = Math.min(min, A[j]);
                }
            }
            if(sum >= L && sum <= R && max-min >= X) cnt++;
        }
        System.out.print(cnt);
    }
}