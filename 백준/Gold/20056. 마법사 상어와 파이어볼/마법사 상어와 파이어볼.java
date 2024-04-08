import java.io.*;
import java.util.*;

public class Main {
    static int [] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int [] dx = {0, 1, 1, 1, 0, -1, -1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        ArrayList<Fireball> [][] A = new ArrayList[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                A[i][j] = new ArrayList<>();
            }
        }
        ArrayList<Fireball> [][] B = new ArrayList[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                B[i][j] = new ArrayList<>();
            }
        }
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken())-1;
            int c = Integer.parseInt(st.nextToken())-1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            A[r][c].add(new Fireball(r, c, m, s, d));
        }
        for(int i = 0; i < K; i++){
            for(int j = 0; j < N; j++){
                for(int k = 0; k < N; k++){
                    if(A[j][k].isEmpty()) continue;
                    for(Fireball f : A[j][k]){
                        int y = (N*1000+f.s*dy[f.d]+f.r)%N;
                        int x = (N*1000+f.s*dx[f.d]+f.c)%N;
                        B[y][x].add(new Fireball(y, x, f.m, f.s, f.d));
                    }
                }
            }

            for(int j = 0; j < N; j++){
                for(int k = 0; k < N; k++){
                    if(B[j][k].size() < 2) continue;
                    int m = 0;
                    int s = 0;
                    boolean z = true;
                    boolean h = true;
                    for(Fireball f : B[j][k]){
                        m+=f.m;
                        s+=f.s;
                        if(f.d%2 == 0) h = false;
                        else z = false;
                    }
                    if(m/5 == 0){
                        B[j][k].clear();
                        continue;
                    }
                    int size = B[j][k].size();
                    B[j][k].clear();
                    if(z || h){
                        for(int n = 0; n < 4; n++){
                            B[j][k].add(new Fireball(j, k, m/5, s/size, 2*n));
                        }
                    }
                    else{
                        for(int n = 0; n < 4; n++){
                            B[j][k].add(new Fireball(j, k, m/5, s/size, 2*n+1));
                        }
                    }
                }
            }

            for(int j = 0; j < N; j++){
                for(int k = 0; k < N; k++){
                    A[j][k].clear();
                    for(Fireball f : B[j][k]){
                        A[j][k].add(new Fireball(f.r, f.c, f.m, f.s, f.d));
                    }
                    B[j][k].clear();
                }
            }
        }

        int sumM = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(A[i][j].isEmpty()) continue;
                for(Fireball f : A[i][j]) sumM+=f.m;
            }
        }
        System.out.println(sumM);
    }

    static class Fireball{
        int r;
        int c;
        int m;
        int s;
        int d;

        public Fireball(int r, int c, int m, int s, int d){
            this.r = r;
            this.c = c;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }
}