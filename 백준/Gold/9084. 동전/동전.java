import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());
        while(T-->0){
            int N = Integer.parseInt(br.readLine());
            int [] A = new int[N];
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++){
                A[i] = Integer.parseInt(st.nextToken());
            }
            int M = Integer.parseInt(br.readLine());
            int [] D = new int[M+1];
            D[0] = 1;
            for(int i = 0; i < N; i++){
                for(int j = 1; j < M+1; j++){
                    if(A[i] > j) continue;
                    D[j]+=D[j-A[i]];
                }
            }
            sb.append(D[M]).append("\n");
        }
        System.out.println(sb);
    }
}