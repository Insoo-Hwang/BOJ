import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int R;
    static int C;
    static int M;
    static Shark [] A;
    static ArrayList<Integer> [][] B;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new Shark[M];
        B = new ArrayList[R][C];
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                B[i][j] = new ArrayList<>();
            }
        }
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            A[i] = new Shark(r, c, s, d, z);
            B[r][c].add(i);
        }

        int get = 0;
        for(int i = 0; i < C; i++){
            for(int j = 0; j < R; j++){
                if(!B[j][i].isEmpty()){
                    get+=A[B[j][i].get(0)].z;
                    A[B[j][i].get(0)].d = -1;
                    B[j][i].remove(0);
                    break;
                }
            }

            for(int j = 0; j < M; j++){
                if(A[j].d == -1){
                    continue;
                }
                move(j);
            }

            for(int j = 0; j < R; j++){
                for(int k = 0; k < C; k++){
                    if(B[j][k].size() < 2) continue;
                    int size = A[B[j][k].get(0)].z;
                    int index = 0;
                    for(int h = 1; h < B[j][k].size(); h++){
                        if(size < A[B[j][k].get(h)].z){
                            A[B[j][k].get(index)].d = -1;
                            index = h;
                            size = A[B[j][k].get(h)].z;
                        }
                        else{
                            A[B[j][k].get(h)].d = -1;
                        }
                    }
                    int zz = 0;
                    while(B[j][k].size() != 1){
                        if(A[B[j][k].get(zz)].d == -1){
                            B[j][k].remove(zz);
                        }
                        else{
                            zz++;
                        }
                    }
                }
            }
        }
        System.out.println(get);
    }

    static void move(int s){
        B[A[s].r][A[s].c].remove(0);
        if(A[s].d == 1){
            if(A[s].r - A[s].s > -1){
                A[s].r -= A[s].s;
            }
            else{
                int t = A[s].s - A[s].r - 1;
                int d = t/(R-1);
                int w = t%(R-1);
                if(d%2 == 0){
                    A[s].d = 2;
                    A[s].r = w+1;
                }
                else{
                    A[s].r = Math.abs(w-(R-2));
                }
            }
        }
        else if(A[s].d == 2){
            if(A[s].r + A[s].s < R){
                A[s].r += A[s].s;
            }
            else{
                int t = A[s].s + A[s].r - 1;
                int d = t/(R-1);
                int w = t%(R-1);
                if(d%2 == 1){
                    A[s].d = 1;
                    A[s].r = Math.abs(w-(R-2));
                }
                else{
                    A[s].r = w+1;
                }
            }
        }
        else if(A[s].d == 3){
            if(A[s].c + A[s].s < C){
                A[s].c += A[s].s;
            }
            else{
                int t = A[s].s + A[s].c - 1;
                int d = t/(C-1);
                int w = t%(C-1);
                if(d%2 == 1){
                    A[s].d = 4;
                    A[s].c = Math.abs(w-(C-2));
                }
                else{
                    A[s].c = w+1;
                }
            }
        }
        else{
            if(A[s].c - A[s].s > -1){
                A[s].c -= A[s].s;
            }
            else{
                int t = A[s].s - A[s].c - 1;
                int d = t/(C-1);
                int w = t%(C-1);
                if(d%2 == 0){
                    A[s].d = 3;
                    A[s].c = w+1;
                }
                else{
                    A[s].c = Math.abs(w-(C-2));
                }
            }
        }
        B[A[s].r][A[s].c].add(s);
    }

    static class Shark{
        int r;
        int c;
        int s;
        int d;
        int z;
        public Shark(int r, int c, int s, int d, int z){
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }
}