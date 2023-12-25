import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int M;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        DFS(0, "");
        System.out.println(sb);
    }

    static void DFS(int d, String s){
        if(d == M){
            sb.append(s).append("\n");
            return;
        }

        for(int i = 1; i < N+1; i++){
            DFS(d+1, s+i+" ");
        }
    }
}