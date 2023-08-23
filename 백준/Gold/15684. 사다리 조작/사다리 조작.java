import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int H;
    static boolean [][][] A;
    static boolean check;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        A = new boolean[N+2][N+2][H+1];
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            A[s][s+1][h] = true;
            A[s+1][s][h] = true;
        }
        if(M == 0){
            System.out.println(0);
            return;
        }

        for(int i = 0; i < 4; i++){
            check = false;
            DFS(0, i);
            if(check){
                min = i;
                break;
            }
        }

        if(min == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(min);
    }

    static void DFS(int c, int l){
        if(check){
            return;
        }
        if(c == l){
            if(move()){
                check = true;
            }
            return;
        }

        for(int i = 1; i < H+1; i++){
            for(int j = 1; j < N; j++){
                if(!A[j][j+1][i] && !A[j-1][j][i] && !A[j+1][j+2][i]){
                    A[j][j+1][i] = true;
                    A[j+1][j][i] = true;
                    DFS(c+1, l);
                    A[j][j+1][i] = false;
                    A[j+1][j][i] = false;
                }
            }
        }
    }

    static boolean move(){
        for(int i = 1; i < N+1; i++){
            int s = i;
            int h = 1;
            while(h <= H){
                if(A[s][s+1][h]){
                    s++;
                }
                else if(A[s][s-1][h]){
                    s--;
                }
                h++;
            }
            if(s != i){
                return false;
            }
        }
        return true;
    }
}