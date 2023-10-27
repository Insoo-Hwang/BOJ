import java.io.*;
import java.util.*;

public class Main {
    static int [] dy = {1, -1};
    static int [] dx = {-1, 1};
    static int [] cy = {1, 0, 1};
    static int [] cx = {0, 1, 0};
    static int N;
    static int M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int [][] A = new int[N][M];
        A[0][0] = 1;
        int n = 0;
        int m = 0;
        int check = 0;
        while(A[N-1][M-1] == 0){
            while(can(n+dy[check], m+dx[check])){
                A[n+dy[check]][m+dx[check]] = A[n][m]+1;
                n+=dy[check];
                m+=dx[check];
            }
            int temp = A[n][m];
            int c = 1;
            if(check == 0){
                if(can(n, m-1)) c = A[n][m]-A[n][m-1]+1;
                else if(can(n-1, m)) c = A[n][m]-A[n-1][m]+1;
            }
            else {
                if(can(n-1, m)) c = A[n][m]-A[n-1][m]+1;
                else if(can(n, m-1)) c = A[n][m]-A[n][m-1]+1;
            }
            if(can(n+cy[check], m+cx[check])){
                n+=cy[check];
                m+=cx[check];
            }
            else if(can(n+cy[check+1], m+cx[check+1])){
                n+=cy[check+1];
                m+=cx[check+1];
            }
            else break;
            A[n][m] = temp+c;
            check = (check == 1) ? 0 : 1;
        }
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                sb.append(A[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
    static boolean can(int n, int m){
        if(n > N-1 || n < 0 || m > M-1 || m < 0) return false;
        return true;
    }
}