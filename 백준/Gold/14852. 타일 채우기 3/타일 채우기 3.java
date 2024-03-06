import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        long [] D = new long[N+1];
        D[0] = 1;
        D[1] = 2;
        long sum = D[0]+D[1];
        for(int i = 2; i < N+1; i++){
            D[i] = (sum*2 + D[i-2])%1000000007;
            sum+=D[i];
        }
        System.out.println(D[N]);
    }
}