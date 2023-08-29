import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static boolean [][] A;
    static int [][] black;
    static int [][] white;
    static int tempB = 0;
    static int tempW = 0;
    static int maxB = 0; //흑칸에 둘 수 있는 최대 비숍의 수
    static int maxW = 0; //백칸에 둘 수 있는 최대 비숍의 수
    static int avaB = 0; //비숍을 둘 수 있는 흑칸의 수
    static int avaW = 0; //비숍을 둘 수 있는 백칸의 수
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        //비숍은 대각선으로 움직이기 때문에 흑/백으로 나누어서(흑 발판에서 흑 발판으로 백 발판에서 백 발판으로 밖에 이동 불가) 따로 백트래킹 해주는 것이 시간 복잡도에 유리
        N = Integer.parseInt(st.nextToken());
        A = new boolean[N][N];
        black = new int[N*N/2+1][2]; //비숍 놓기가 가능한 흑의 위치
        white = new int[N*N/2+1][2]; //비숍 놓기가 가능한 백의 위치
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                int temp = Integer.parseInt(st.nextToken());
                if(temp == 0){
                    A[i][j] = true; //불가능한 위치는 true
                }
                else{
                    if((i+j)%2 == 0){ //좌표의 합이 짝수면 흑
                        black[tempB][0] = i;
                        black[tempB][1] = j;
                        tempB++;
                        avaB++;
                    }
                    else { //좌표의 합이 홀수면 흑
                        white[tempW][0] = i;
                        white[tempW][1] = j;
                        tempW++;
                        avaW++;
                    }
                }
            }
        }

        boolean [][] copy = new boolean[N][N]; //백트래킹을 위한 복사본
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                copy[i][j] = A[i][j];
            }
        }
        DFSB(0, 0);
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                A[i][j] = copy[i][j];
            }
        }
        DFSW(0, 0);
        System.out.println(maxB + maxW);
    }

    static void DFSB(int s, int n){ //s는 배열의 시작 인덱스 n은 놓은 비숍의 수
        if(avaB == 0 || s == tempB){ //더이상 놓을 자리가 없거나 배열의 끝에 도달하면
            maxB = Math.max(maxB, n); //비숍의 최대값 업데이트
            return;
        }
        boolean [][] copy = new boolean[N][N]; //백트래킹을 위한 복사본
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                copy[i][j] = A[i][j];
            }
        }
        int copyAvailable = avaB; //백트래킹을 위한 복사본

        for(int i = s; i < tempB; i++){
            if(!A[black[i][0]][black[i][1]]){ //놓을 수 있는 칸이면
                BishopB(black[i][0], black[i][1]); //그 자리에 놓고 대각선을 true로 처리해줌
                DFSB(i+1, n+1);
                for(int j = 0; j < N; j++){
                    for(int k = 0; k < N; k++){
                        A[j][k] = copy[j][k];
                    }
                }
                avaB = copyAvailable;
            }
        }
    }

    static void BishopB(int n, int m){ //(n, m)에 비숍이 놓아졌을 때 놓을 수 없는 곳 처리
        A[n][m] = true;
        int temp = 1; //놓아진 비숍과의 가로축 거리
        for(int i = m-1; i > -1; i--){ //왼쪽 부분
            if(n-temp > -1){
                if(!A[n-temp][i]) { //원래 가능한 곳이 었으면
                    A[n - temp][i] = true; //불가능하게 만들고
                    avaB--; //가능한 자리수 감소소
                }
            }
            if(n+temp < N){
                if(!A[n+temp][i]) {
                    A[n + temp][i] = true;
                    avaB--;
                }
            }
            temp++;
        }
        temp = 1;
        for(int i = m+1; i < N; i++){ //오른쪽 부분분
            if(n-temp > -1){
                if(!A[n-temp][i]) {
                    A[n - temp][i] = true;
                    avaB--;
                }
            }
            if(n+temp < N){
                if(!A[n+temp][i]) {
                    A[n + temp][i] = true;
                    avaB--;
                }
            }
            temp++;
        }
    }
    static void DFSW(int s, int n){
        if(avaW == 0 || s == tempW){
            maxW = Math.max(maxW, n);
            return;
        }
        boolean [][] copy = new boolean[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                copy[i][j] = A[i][j];
            }
        }
        int copyAvailable = avaW;

        for(int i = s; i < tempW; i++){
            if(!A[white[i][0]][white[i][1]]){
                BishopW(white[i][0], white[i][1]);
                DFSW(i+1, n+1);
                for(int j = 0; j < N; j++){
                    for(int k = 0; k < N; k++){
                        A[j][k] = copy[j][k];
                    }
                }
                avaW = copyAvailable;
            }
        }
    }

    static void BishopW(int n, int m){
        A[n][m] = true;
        int temp = 1;
        for(int i = m-1; i > -1; i--){
            if(n-temp > -1){
                if(!A[n-temp][i]) {
                    A[n - temp][i] = true;
                    avaW--;
                }
            }
            if(n+temp < N){
                if(!A[n+temp][i]) {
                    A[n + temp][i] = true;
                    avaW--;
                }
            }
            temp++;
        }
        temp = 1;
        for(int i = m+1; i < N; i++){
            if(n-temp > -1){
                if(!A[n-temp][i]) {
                    A[n - temp][i] = true;
                    avaW--;
                }
            }
            if(n+temp < N){
                if(!A[n+temp][i]) {
                    A[n + temp][i] = true;
                    avaW--;
                }
            }
            temp++;
        }
    }
}
