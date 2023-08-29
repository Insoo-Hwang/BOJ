import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int [][] A;
    static int [][] B;
    static int tempB;
    static boolean done = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringTokenizer st = new StringTokenizer(br.readLine());

        A = new int[9][9];
        B = new int[81][2];
        tempB = 0;
        for(int i = 0; i < 9; i++){
            String s = br.readLine();
            for(int j = 0; j < 9; j++){
                A[i][j] = s.charAt(j) - '0';
                if(A[i][j] == 0){
                    B[tempB][0] = i;
                    B[tempB][1] = j;
                    tempB++;
                }
            }
        }

        DFS(0);
    }

    static void DFS(int d){ //d는 채워진 빈칸의 수
        if(d == tempB){ //모든 빈칸을 다 채우면
            done = true; //값을 하나만 출력하기 위해 done변수 사용
            for(int i = 0; i < 9; i++){
                for(int j = 0; j < 9; j++){
                    System.out.print(A[i][j]); //스도쿠 출력
                }
                System.out.println();
            }
            return;
        }

        for(int i = 1; i < 10; i++){ //1~9의 값이 빈칸에 들어갈 수 있음
            A[B[d][0]][B[d][1]] = i;
            if(check(B[d][0], B[d][1])){ //스도쿠 룰이 성립하는지 확인
                DFS(d+1);
                if(done) return; //출력이 완료 됐으면 종료
            }
            A[B[d][0]][B[d][1]] = 0;
        }
    }

    static boolean check(int n, int m){
        boolean [] num = new boolean[10];
        for(int i = 0; i < 9; i++){ //세로줄 확인
            if(A[i][m] == 0) continue;
            if(num[A[i][m]]){
                return false;
            }
            num[A[i][m]] = true;
        }
        num = new boolean[10];
        for(int i = 0; i < 9; i++){ //가로줄 확인
            if(A[n][i] == 0) continue;
            if(num[A[n][i]]){
                return false;
            }
            num[A[n][i]] = true;
        }
        num = new boolean[10];
        for(int i = n/3*3; i < n/3*3+3; i++){ //자신이 포함된 정사각형 확인
            for(int j = m/3*3; j < m/3*3+3; j++){
                if(A[i][j] == 0) continue;
                if(num[A[i][j]]){
                    return false;
                }
                num[A[i][j]] = true;
            }
        }
        return true;
    }
}
