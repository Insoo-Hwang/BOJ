import java.beans.Introspector;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int K;
    static int [][] A;
    static ArrayList<Integer>[][] B;
    static Mal [] mal;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        A = new int[N][N];
        B = new ArrayList[N][N];
        mal = new Mal[K];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                B[i][j] = new ArrayList<>();
            }
        }
        for(int i = 0 ; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i = 0; i < K; i++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken())-1;
            int m = Integer.parseInt(st.nextToken())-1;
            int k = Integer.parseInt(st.nextToken());
            B[n][m].add(i);
            mal[i] = new Mal(n, m, k, 0);
        }

        for(int i = 0; i < 1000; i++){
            for(int j = 0; j < K; j++){
                Mal now = mal[j];
                if(now.b != 0){
                    continue;
                }
                int n = now.n;
                int m = now.m;
                if(now.k == 1){
                    if(m == N-1 || A[n][m+1] == 2){
                        now.k = 2;
                        LEFT(now);
                    }
                    else{
                        RIGHT(now);
                    }
                }
                else if(now.k == 2){
                    if(m == 0 || A[n][m-1] == 2){
                        now.k = 1;
                        RIGHT(now);
                    }
                    else{
                        LEFT(now);
                    }
                }
                else if(now.k == 3){
                    if(n == 0 || A[n-1][m] == 2){
                        now.k = 4;
                        DOWN(now);
                    }
                    else{
                        UP(now);
                    }
                }
                else if(now.k == 4){
                    if(n == N-1 || A[n+1][m] == 2){
                        now.k = 3;
                        UP(now);
                    }
                    else{
                        DOWN(now);
                    }
                }
                if(check()){
                    System.out.println(i+1);
                    return;
                }
            }
        }
        System.out.println(-1);
    }

    static void RIGHT(Mal now){
        int n = now.n;
        int m = now.m;
        if(m+1 < N && A[n][m+1] == 0){
            white(now, n, m, n, m+1);
        }
        else if(m+1 < N && A[n][m+1] == 1){
            red(now, n, m, n, m+1);
        }
    }

    static void LEFT(Mal now){
        int n = now.n;
        int m = now.m;
        if(m-1 > -1 && A[n][m-1] == 0){
            white(now, n, m, n, m-1);
        }
        else if(m-1 > -1 && A[n][m-1] == 1){
            red(now, n, m, n, m-1);
        }
    }

    static void UP(Mal now){
        int n = now.n;
        int m = now.m;
        if(n-1 > -1 && A[n-1][m] == 0){
            white(now, n, m, n-1, m);
        }
        else if(n-1 > -1 && A[n-1][m] == 1){
            red(now, n, m, n-1, m);
        }
    }

    static void DOWN(Mal now){
        int n = now.n;
        int m = now.m;
        if(n+1 < N && A[n+1][m] == 0){
            white(now, n, m, n+1, m);
        }
        else if(n+1 < N && A[n+1][m] == 1){
            red(now, n, m, n+1, m);
        }
    }

    static void white(Mal now, int n1, int m1, int n2, int m2){
        int b = now.b;
        while(b < B[n1][m1].size()){
            int temp = B[n1][m1].remove(b);
            B[n2][m2].add(temp);
            mal[temp].n = n2;
            mal[temp].m = m2;
            mal[temp].b = B[n2][m2].size()-1;
        }
    }

    static void red(Mal now, int n1, int m1, int n2, int m2){
        int b = now.b;
        while(b < B[n1][m1].size()){
            int temp = B[n1][m1].remove(B[n1][m1].size()-1);
            B[n2][m2].add(temp);
            mal[temp].n = n2;
            mal[temp].m = m2;
            mal[temp].b = B[n2][m2].size()-1;
        }
    }

    static boolean check(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(B[i][j].size() >= 4){
                    return true;
                }
            }
        }
        return false;
    }
    static class Mal{
        int n;
        int m;
        int k;
        int b;
        public Mal(int n, int m, int k, int b){
            this.n = n;
            this.m = m;
            this.k = k;
            this.b = b;
        }
    }
}