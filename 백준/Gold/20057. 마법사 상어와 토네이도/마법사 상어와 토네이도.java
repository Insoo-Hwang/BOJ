import java.io.*;
import java.util.*;

public class Main {
    static int [] dy = {0, 1, 0, -1};
    static int [] dx = {-1, 0, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int [][] A = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int [] dn = new int[2*N-1];
        int idx = 0;
        for(int i = 1; i < N; i++){
            dn[idx++] = i;
            dn[idx++] = i;
        }
        dn[idx] = N-1;
        int sum = 0;
        int n = N/2;
        int m = N/2;
        for(int i = 0; i < dn.length; i++){
            for(int j = 0; j < dn[i]; j++){
                int total = A[n+dy[i%4]][m+dx[i%4]];
                int y = n+dy[(i+1)%4];
                int x = m+dx[(i+1)%4];
                if(y > N-1 || y < 0 || x > N-1 || x < 0) sum+=(int)(0.01*total);
                else A[y][x]+=(int)(0.01*total);
                y = n+dy[(i+3)%4];
                x = m+dx[(i+3)%4];
                if(y > N-1 || y < 0 || x > N-1 || x < 0) sum+=(int)(0.01*total);
                else A[y][x]+=(int)(0.01*total);
                y = n+dy[(i+1)%4]+dy[i%4];
                x = m+dx[(i+1)%4]+dx[i%4];
                if(y > N-1 || y < 0 || x > N-1 || x < 0) sum+=(int)(0.07*total);
                else A[y][x]+=(int)(0.07*total);
                y = n+dy[(i+3)%4]+dy[i%4];
                x = m+dx[(i+3)%4]+dx[i%4];
                if(y > N-1 || y < 0 || x > N-1 || x < 0) sum+=(int)(0.07*total);
                else A[y][x]+=(int)(0.07*total);
                y = n+2*dy[(i+1)%4]+dy[i%4];
                x = m+2*dx[(i+1)%4]+dx[i%4];
                if(y > N-1 || y < 0 || x > N-1 || x < 0) sum+=(int)(0.02*total);
                else A[y][x]+=(int)(0.02*total);
                y = n+2*dy[(i+3)%4]+dy[i%4];
                x = m+2*dx[(i+3)%4]+dx[i%4];
                if(y > N-1 || y < 0 || x > N-1 || x < 0) sum+=(int)(0.02*total);
                else A[y][x]+=(int)(0.02*total);
                y = n+dy[(i+1)%4]+2*dy[i%4];
                x = m+dx[(i+1)%4]+2*dx[i%4];
                if(y > N-1 || y < 0 || x > N-1 || x < 0) sum+=(int)(0.1*total);
                else A[y][x]+=(int)(0.1*total);
                y = n+dy[(i+3)%4]+2*dy[i%4];
                x = m+dx[(i+3)%4]+2*dx[i%4];
                if(y > N-1 || y < 0 || x > N-1 || x < 0) sum+=(int)(0.1*total);
                else A[y][x]+=(int)(0.1*total);
                y = n+3*dy[i%4];
                x = m+3*dx[i%4];
                if(y > N-1 || y < 0 || x > N-1 || x < 0) sum+=(int)(0.05*total);
                else A[y][x]+=(int)(0.05*total);
                total-=((int)(0.01*total)+(int)(0.01*total)+(int)(0.07*total)+(int)(0.07*total)+(int)(0.02*total)+(int)(0.02*total)+(int)(0.1*total)+(int)(0.1*total)+(int)(0.05*total));
                y = n+2*dy[i%4];
                x = m+2*dx[i%4];
                if(y > N-1 || y < 0 || x > N-1 || x < 0) sum+=total;
                else A[y][x]+=total;

                n+=dy[i%4];
                m+=dx[i%4];
            }
        }
        System.out.println(sum);
    }
}