import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static char [][] A;
    static int min = Integer.MAX_VALUE;
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
            }
        }
        char [][] B = new char[N][M];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                B[i][j] = A[i][j];
            }
        }
        move(1, 1); //위쪽으로 이동
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                A[i][j] = B[i][j];
            }
        }
        move(1, 2); //아래쪽으로 이동
        for(int i = 0; i < N; i++){
         향
        if(d == 11){
            return;
        }
        int tempRed = -1;
        if(a == 1) { //위쪽으로 이동
            for (int i = 1; i < N-1; i++) {
                for (int j = 1; j < M-1; j++) {
                    int n = i;
                    int m = j;
                    if (A[i][j] == '#' || A[i][j] == 'O' || A[i][j] == '.') continue; //공만 움직일 수 있도록
                    while (true) {
                        if(n < 1 || n > N-2 || m < 1 || m > M-2) break; //게임기의 범위를 넘어가면 break
                        if (A[n-1][m] == '.') { //빈공간이 있으면 그 쪽으로 이동
                            A[n-1][m] = A[n][m];
                            A[n][m] = '.';
                            n--;
                        } else if (A[n-1][m] == '#' || A[n-1][m] == 'B' || A[n-1][m] == 'R') { //가로막히면 이동 중지
                            break;
                        } else if (A[n-1][m] == 'O' && A[n][m] == 'B') { //구멍에 파란 공이 들어가면 게임 종료
                            return;
                        } else if (A[n-1][m] == 'O' && A[n][m] == 'R') { //구멍에 빨간 공이 들어가면 d(횟수) 업데이트, 파란 공이 추가로 들어가면 return될 수 있도록 min에는 업데이트 x
                            tempRed = d;
                            A[n][m] = '.';
                            break;
                        }
                    }
                }
            }
        }
        else if(a == 2) { //아래쪽으로 이동
            for (int i = N-2; i > 0; i--) {
                for (int j = 1; j < M-1; j++) {
                    int n = i;
                    int m = j;
                    if (A[i][j] == '#' || A[i][j] == 'O' || A[i][j] == '.') continue;
                    while (true) {
                        if(n < 1 || n > N-2 || m < 1 || m > M-2) break;
                        if (A[n+1][m] == '.') {
                            A[n+1][m] = A[n][m];
                            A[n][m] = '.';
                            n++;
                        } else if (A[n+1][m] == '#' || A[n+1][m] == 'B' || A[n+1][m] == 'R') {
                            break;
                        } else if (A[n+1][m] == 'O' && A[n][m] == 'B') {
                            return;
                        } else if (A[n+1][m] == 'O' && A[n][m] == 'R') {
                            tempRed = d;
                            A[n][m] = '.';
                            break;
                        }
                    }
                }
            }
        }
        else if(a == 3) { //왼쪽으로 이동
            for (int i = 1; i < N-1; i++) {
                for (int j = 1; j < M-1; j++) {
                    int n = i;
                    int m = j;
                    if (A[i][j] == '#' || A[i][j] == 'O' || A[i][j] == '.') continue;
                    while (true) {
                        if(n < 1 || n > N-2 || m < 1 || m > M-2) break;
                        if (A[n][m-1] == '.') {
                            A[n][m-1] = A[n][m];
                            A[n][m] = '.';
                            m--;
                        } else if (A[n][m-1] == '#' || A[n][m-1] == 'B' || A[n][m-1] == 'R') {
                            break;
                        } else if (A[n][m-1] == 'O' && A[n][m] == 'B') {
                            return;
                        } else if (A[n][m-1] == 'O' && A[n][m] == 'R') {
                            tempRed = d;
                            A[n][m] = '.';
                            break;
                        }
                    }
                }
            }
        }
        else if(a == 4) { //오른쪽으로 이동
            for (int i = 1; i < N-1; i++) {
                for (int j = M-2; j > 0; j--) {
                    int n = i;
                    int m = j;
                    if (A[i][j] == '#' || A[i][j] == 'O' || A[i][j] == '.') continue;
                    while (true) {
                        if(n < 1 || n > N-2 || m < 1 || m > M-2) break;
                        if (A[n][m+1] == '.') {
                            A[n][m+1] = A[n][m];
                            A[n][m] = '.';
                            m++;
                        } else if (A[n][m+1] == '#' || A[n][m+1] == 'B' || A[n][m+1] == 'R') {
                            break;
                        } else if (A[n][m+1] == 'O' && A[n][m] == 'B') {
                            return;
                        } else if (A[n][m+1] == 'O' && A[n][m] == 'R') {
                            tempRed = d;
                            A[n][m] = '.';
                            break;
                        }
                    }
                }
            }
        }


        if(tempRed != -1){ //파란 공은 들어가지 않고 빨간 공만 들어가면
            min = Math.min(min, tempRed); //min 업데이트
        }
        else{
            char [][] B = new char[N][M];
            for(int i = 0; i < N; i++){
                for(int j = 0; j < M; j++){
                    B[i][j] = A[i][j];
                }
            }
            move(d+1, 1); //위쪽으로 이동
            for(int i = 0; i < N; i++){
                for(int j = 0; j < M; j++){
                    A[i][j] = B[i][j];
                }
            }
            move(d+1, 2); //아래쪽으로 이동
            for(int i = 0; i < N; i++){
                for(int j = 0; j < M; j++){
                    A[i][j] = B[i][j];
                }
            }
            move(d+1, 3); //왼쪽으로 이동
            for(int i = 0; i < N; i++){
                for(int j = 0; j < M; j++){
                    A[i][j] = B[i][j];
                }
            }
            move(d+1, 4); //오른쪽으로 이동
        }
    }
}
