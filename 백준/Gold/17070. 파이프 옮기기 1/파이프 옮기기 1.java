import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int [][] A = new int[N+1][N+1];
        for(int i = 1; i < N+1; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1 ; j < N+1; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int [][][] D = new int[N+1][N+1][3]; //행과 열 그리고 놓여있는 방향(0은 가로, 1은 대각선, 2는 세로)
        D[1][2][0] = 1; //(1, 2) 가로 방향은 항상 1가지 경우의 수만 가능
        for(int i = 1; i < N+1; i++){
            for(int j = 1; j < N+1; j++){
                if(i == 1 && j == 2) continue; //(1, 2)는 예외로 제외
                if(A[i][j] == 1) continue; //벽이 있는 곳은 제외
                D[i][j][0] = D[i][j-1][0] + D[i][j-1][1]; //(i, j)에 가로 방향으로 놓일 수 있는 경우
                if(A[i-1][j] != 1 && A[i][j-1] != 1){ //대각선으로 놓일 경우 윗칸과 옆칸이 벽이 아닌지 확인
                    D[i][j][1] = D[i-1][j-1][0] + D[i-1][j-1][1] + D[i-1][j-1][2]; //(i, j)에 대각선 방향으로 놓일 수 있는 경우
                }
                D[i][j][2] = D[i-1][j][1] + D[i-1][j][2]; //(i, j)에 세로 방향으로 놓일 수 있는 경우
            }
        }
        System.out.println(D[N][N][0] + D[N][N][1] + D[N][N][2]);
    }
}
