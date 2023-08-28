import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static char [][] A;
    static int [] blue = new int[2]; //파란 구슬의 좌표
    static int [] red = new int[2]; //빨간 구슬의 좌표
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
                    A[i][j] = '.'; //구슬의 위치는 계속 바뀌기 때문에 무의미함
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

        for(int i = 0; i < 4; i++){ //4방향으로 DFS
            DFS(blue[0], blue[1], red[0], red[1], 1, i);
        }
        if(cnt == 11) System.out.println(-1);
        else System.out.println(cnt);
    }

    static void DFS(int nB, int mB, int nR, int mR, int c, int a){
        if(c > 10){ //11회 이상일 시 -1 리턴
            return;
        }
        int blue = 0; //파란 구슬이 움직인 거리
        int red = 0; //빨간 구슬이 움직인 거리
        while(A[nB+dy[a]][mB+dx[a]] != '#'){ //벽을 만날 때까지 구슬 이동
            nB+=dy[a];
            mB+=dx[a];
            blue++;
            if(A[nB][mB] == 'O') return; //파란 구슬이 구멍에 들어가면 게임 종료
        }
        while(A[nR+dy[a]][mR+dx[a]] != '#'){
            nR+=dy[a];
            mR+=dx[a];
            red++;
            if(A[nR][mR] == 'O'){ //빨간 구슬이 구멍에 들어가면 게임 횟수 업데이트
                cnt = Math.min(cnt, c);
            }
        }
        if(nB == nR && mB == mR){ //빨간 구슬과 파란 구슬의 위치가 같으면 더 많이 이동한 구슬이 이동한 방향 기준 더 뒤에 위치
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
