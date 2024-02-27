import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        long [] D = new long[N+1];
        for(int i = 1; i < N+1; i++) D[i] = i;
        for(int i = 4; i < N+1; i++){
            for(int j = 3; j < i; j++){
                D[i] = Math.max(D[i], D[i-j]*(j-1));
            }
        }
        System.out.println(D[N]);
    }
}