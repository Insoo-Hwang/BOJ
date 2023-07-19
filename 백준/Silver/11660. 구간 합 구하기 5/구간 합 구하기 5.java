import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        long [][] A = new long[N+1][N+1];

        for(int i = 1; i < N+1; i++){
            long add = 0;
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j < N+1; j++){
                long temp = Long.parseLong(st.nextToken());
                add += temp;
                A[i][j] = A[i-1][j] + add;
            }
        }

        StringBuilder ans = new StringBuilder();
        while(M-->0){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            ans.append(A[x2][y2] + A[x1-1][y1-1] - A[x2][y1-1] - A[x1-1][y2] + "\n");
        }
        System.out.println(ans);
    }
}
