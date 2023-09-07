import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int [][] A;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        A = new int[N+1][N+1];
        for(int i = 1; i < N+1; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j < N+1; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        xy();
        System.out.println(min);
    }

    static void xy(){
        for(int i = 1; i <= N-2; i++){
            for(int j = 2; j <= N-1; j++){
                d1d2(i, j);
            }
        }
    }

    static void d1d2(int x, int y){
        for(int i = 1; i <= 100; i++){
            for(int j = 1; j <= 100; j++){
                if(!check(x+i,y-i) || !check(x+j,y+j) ||!check(x+i+j,y-i+j) || !check(x+j+i,y+j-i))
                    break;
                gerrymandering(x, y, i, j);
            }
        }
    }

    static boolean check(int x, int y){
        return x >= 1 && y >= 1 && y <= N && x <= N;
    }

    static void gerrymandering(int x, int y, int d1, int d2){
        int [] B = new int[5];
        boolean [][] visited = new boolean[N+1][N+1];
        int left = 0;
        int right = 0;
        boolean l = true;
        boolean r = true;
        for(int i = x; i <= x+d1+d2; i++){
            for(int j = y+left; j <= y+right; j++){
                visited[i][j] = true;
                B[4]+=A[i][j];
            }
            if(left == -d1) l = !l;
            if(right == d2) r = !r;
            if(l) left--;
            else left++;
            if(r) right++;
            else right--;
        }
        for(int i = 1; i < x+d1; i++){
            for(int j = 1; j <= y; j++){
                if(!visited[i][j]){
                    B[0]+=A[i][j];
                }
            }
        }
        for(int i = 1; i <= x+d2; i++){
            for(int j = y+1; j <= N; j++){
                if(!visited[i][j]){
                    B[1]+=A[i][j];
                }
            }
        }
        for(int i = x+d1; i <= N; i++){
            for(int j = 1; j < y-d1+d2; j++){
                if(!visited[i][j]){
                    B[2]+=A[i][j];
                }
            }
        }
        for(int i = x+d2+1; i <= N; i++){
            for(int j = y-d1+d2; j <= N; j++){
                if(!visited[i][j]){
                    B[3]+=A[i][j];
                }
            }
        }
        Arrays.sort(B);
        min = Math.min(min, B[4]-B[0]);
    }
}