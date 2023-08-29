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

    static void DFS(int d){
        if(d == tempB){
            done = true;
            for(int i = 0; i < 9; i++){
                for(int j = 0; j < 9; j++){
                    System.out.print(A[i][j]);
                }
                System.out.println();
            }
            return;
        }

        for(int i = 1; i < 10; i++){
            A[B[d][0]][B[d][1]] = i;
            if(check(B[d][0], B[d][1])){
                DFS(d+1);
                if(done) return;
            }
            A[B[d][0]][B[d][1]] = 0;
        }
    }

    static boolean check(int n, int m){
        boolean [] num = new boolean[10];
        for(int i = 0; i < 9; i++){
            if(A[i][m] == 0) continue;
            if(num[A[i][m]]){
                return false;
            }
            num[A[i][m]] = true;
        }
        num = new boolean[10];
        for(int i = 0; i < 9; i++){
            if(A[n][i] == 0) continue;
            if(num[A[n][i]]){
                return false;
            }
            num[A[n][i]] = true;
        }
        num = new boolean[10];
        for(int i = n/3*3; i < n/3*3+3; i++){
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