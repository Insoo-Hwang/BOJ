import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static char [][] A;
    static int [] blue = new int[2];
    static int [] red = new int[2];
    static int [] dy = {-1, 1, 0, 0};
    static int [] dx = {0, 0, -1, 1};
    static int cnt = 11;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new char[N][M];
        for(int i = 0; i < N; i++){
            String s = br.readLine();
            for(int j = 0; j < M; j++){
                char temp = s.charAt(j);
                A[i][j] = temp;
                if(temp == 'B'){
                    A[i][j] = '.';
                    blue[0] = i;
                    blue[1] = j;
                }
                if(temp == 'R'){
                    A[i][j] = '.';
                    red[0] = i;
                    red[1] = j;
                }
            }
        }

        for(int i = 0; i < 4; i++){
            DFS(blue[0], blue[1], red[0], red[1], 1, i);
        }
        if(cnt == 11) System.out.println(-1);
        else System.out.println(cnt);
    }

    static void DFS(int nB, int mB, int nR, int mR, int c, int a){
        if(c > 10){
            return;
        }
        int blue = 0;
        int red = 0;
        while(A[nB+dy[a]][mB+dx[a]] != '#'){
            nB+=dy[a];
            mB+=dx[a];
            blue++;
            if(A[nB][mB] == 'O') return;
        }
        while(A[nR+dy[a]][mR+dx[a]] != '#'){
            nR+=dy[a];
            mR+=dx[a];
            red++;
            if(A[nR][mR] == 'O'){
                cnt = Math.min(cnt, c);
            }
        }
        if(nB == nR && mB == mR){
            if(blue > red){
                nB-=dy[a];
                mB-=dx[a];
            }
            else{
                nR-=dy[a];
                mR-=dx[a];
            }
        }

        for(int i = 0; i < 4; i++){
            DFS(nB, mB, nR, mR, c+1, i);
        }
    }
}