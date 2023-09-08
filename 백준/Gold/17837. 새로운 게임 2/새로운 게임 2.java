import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static ArrayList<Integer> [][] B;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int [][] A = new int[N][N];
        B = new ArrayList[N][N];
        Mal [] M = new Mal[K];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                B[i][j] = new ArrayList<>();
            }
        }
        for(int i = 0; i < N; i++){
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
            M[i] = new Mal(n, m, k, 0);
        }

        int cnt;
        if(check()){
            System.out.println(0);
            return;
        }
        for(cnt = 0; cnt < 1000; cnt++){
            for(int i = 0; i < K; i++){
                Mal now = M[i];
                int n = now.n;
                int m = now.m;
                if(now.k == 1){
                    if(m+1 > N-1 || A[n][m+1] == 2){
                        now.k = 2;
                        if(m-1 < 0 || A[n][m-1] == 2){
                        }
                        else if(A[n][m-1] == 0){
                            int b = now.b;
                            while(B[n][m].size() > b){
                                int num = B[n][m].remove(b);
                                B[n][m-1].add(num);
                                M[num].b = B[n][m-1].size()-1;
                                M[num].m = m-1;
                            }
                        }
                        else{
                            int b = now.b;
                            while(B[n][m].size() > b){
                                int num = B[n][m].remove(B[n][m].size()-1);
                                B[n][m-1].add(num);
                                M[num].b = B[n][m-1].size()-1;
                                M[num].m = m-1;
                            }
                        }
                    }
                    else if(A[n][m+1] == 0){
                        int b = now.b;
                        while(B[n][m].size() > b){
                            int num = B[n][m].remove(b);
                            B[n][m+1].add(num);
                            M[num].b = B[n][m+1].size()-1;
                            M[num].m = m+1;

                        }
                    }
                    else{
                        int b = now.b;
                        while(B[n][m].size() > b){
                            int num = B[n][m].remove(B[n][m].size()-1);
                            B[n][m+1].add(num);
                            M[num].b = B[n][m+1].size()-1;
                            M[num].m = m+1;
                        }
                    }
                }
                else if(now.k == 2){
                    if(m-1 < 0 || A[n][m-1] == 2){
                        now.k = 1;
                        if(m+1 > N-1 || A[n][m+1] == 2){
                        }
                        else if(A[n][m+1] == 0){
                            int b = now.b;
                            while(B[n][m].size() > b){
                                int num = B[n][m].remove(b);
                                B[n][m+1].add(num);
                                M[num].b = B[n][m+1].size()-1;
                                M[num].m = m+1;
                            }
                        }
                        else{
                            int b = now.b;
                            while(B[n][m].size() > b){
                                int num = B[n][m].remove(B[n][m].size()-1);
                                B[n][m+1].add(num);
                                M[num].b = B[n][m+1].size()-1;
                                M[num].m = m+1;
                            }
                        }
                    }
                    else if(A[n][m-1] == 0){
                        int b = now.b;
                        while(B[n][m].size() > b){
                            int num = B[n][m].remove(b);
                            B[n][m-1].add(num);
                            M[num].b = B[n][m-1].size()-1;
                            M[num].m = m-1;
                        }
                    }
                    else{
                        int b = now.b;
                        while(B[n][m].size() > b){
                            int num = B[n][m].remove(B[n][m].size()-1);
                            B[n][m-1].add(num);
                            M[num].b = B[n][m-1].size()-1;
                            M[num].m = m-1;
                        }
                    }
                }
                else if(now.k == 3){
                    if(n-1 < 0 || A[n-1][m] == 2){
                        now.k = 4;
                        if(n+1 > N-1 || A[n+1][m] == 2){
                        }
                        else if(A[n+1][m] == 0){
                            int b = now.b;
                            while(B[n][m].size() > b){
                                int num = B[n][m].remove(b);
                                B[n+1][m].add(num);
                                M[num].b = B[n+1][m].size()-1;
                                M[num].n = n+1;
                            }
                        }
                        else{
                            int b = now.b;
                            while(B[n][m].size() > b){
                                int num = B[n][m].remove(B[n][m].size()-1);
                                B[n+1][m].add(num);
                                M[num].b = B[n+1][m].size()-1;
                                M[num].n = n+1;
                            }
                        }
                    }
                    else if(A[n-1][m] == 0){
                        int b = now.b;
                        while(B[n][m].size() > b){
                            int num = B[n][m].remove(b);
                            B[n-1][m].add(num);
                            M[num].b = B[n-1][m].size()-1;
                            M[num].n = n-1;
                        }
                    }
                    else{
                        int b = now.b;
                        while(B[n][m].size() > b){
                            int num = B[n][m].remove(B[n][m].size()-1);
                            B[n-1][m].add(num);
                            M[num].b = B[n-1][m].size()-1;
                            M[num].n = n-1;
                        }
                    }
                }
                else if(now.k == 4){
                    if(n+1 > N-1 || A[n+1][m] == 2){
                        now.k = 3;
                        if(n-1 < 0 || A[n-1][m] == 2){
                        }
                        else if(A[n-1][m] == 0){
                            int b = now.b;
                            while(B[n][m].size() > b){
                                int num = B[n][m].remove(b);
                                B[n-1][m].add(num);
                                M[num].b = B[n-1][m].size()-1;
                                M[num].n = n-1;
                            }
                        }
                        else{
                            int b = now.b;
                            while(B[n][m].size() > b){
                                int num = B[n][m].remove(B[n][m].size()-1);
                                B[n-1][m].add(num);
                                M[num].b = B[n-1][m].size()-1;
                                M[num].n = n-1;
                            }
                        }
                    }
                    else if(A[n+1][m] == 0){
                        int b = now.b;
                        while(B[n][m].size() > b){
                            int num = B[n][m].remove(b);
                            B[n+1][m].add(num);
                            M[num].b = B[n+1][m].size()-1;
                            M[num].n = n+1;
                        }
                    }
                    else{
                        int b = now.b;
                        while(B[n][m].size() > b){
                            int num = B[n][m].remove(B[n][m].size()-1);
                            B[n+1][m].add(num);
                            M[num].b = B[n+1][m].size()-1;
                            M[num].n = n+1;
                        }
                    }
                }
                if(check()){
                    System.out.println(cnt+1);
                    return;
                }
            }
        }
        System.out.println(-1);
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