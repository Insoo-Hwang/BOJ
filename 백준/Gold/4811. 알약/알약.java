import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(true){
            int N = Integer.parseInt(br.readLine());
            if(N == 0) break;
            long [][] D = new long[2*N+1][N];
            D[1][N-1] = 1;
            for(int i = 1; i < 2*N+1; i++){
                for(int j = 0; j < N; j++){
                    if(D[i][j] == 0) continue;
                    if(j > 0) D[i+1][j-1]+=D[i][j];
                    if(2*(N-j) > i) D[i+1][j]+=D[i][j];
                }
            }
            sb.append(D[2*N][0]).append("\n");
        }
        System.out.println(sb);
    }
}