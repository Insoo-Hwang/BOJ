import java.io.*;
import java.nio.Buffer;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static char [][] A;
    static int [] redB = new int[2];
    static int [] blueB = new int[2];
    static int [] dy = {-1, 1, 0, 0};
    static int [] dx = {0, 0, -1, 1};
    static boolean check = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new char[N][M];
        for(int i = 0; i < N; i++){
            String s = br.readLine();
            for(int j = 0; j < M; j++){
                A[i][j] = s.charAt(j);
                if(A[i][j] == 'R'){
                    A[i][j] = '.';
                    redB[0] = i;
                    redB[1] = j;
                }
                if(A[i][j] == 'B'){
                    A[i][j] = '.';
                    blueB[0] = i;
                    blueB[1] = j;
                }
            }
        }
        for(int i = 0; i < 4; i++) {
            DFS(0, redB[0], redB[1], blueB[0], blueB[1], i);
            if(check){
                System.out.println("1");
                return;
            }
        }
        System.out.println("0");
    }

    static void DFS(int d, int redn, int redm, int bluen, int bluem, int a){
        if(d > 9){
            return;
        }

        int r = 0;
        int b = 0;
        while(A[bluen+dy[a]][bluem+dx[a]] != '#'){
            b++;
            bluen+=dy[a];
            bluem+=dx[a];
            if(A[bluen][bluem] == 'O') return;
        }
        while(A[redn+dy[a]][redm+dx[a]] != '#'){
            r++;
            redn+=dy[a];
            redm+=dx[a];
            if (A[redn][redm] == 'O'){
                check = true;
                return;
            }
        }
        if(redn == bluen && redm == bluem){
            if(b > r){
                bluen-=dy[a];
                bluem-=dx[a];
            }
            else{
                redn-=dy[a];
                redm-=dx[a];
            }
        }
        for(int i = 0; i < 4; i++){
            DFS(d+1, redn, redm, bluen, bluem, i);
            if(check) return;
        }
    }
}