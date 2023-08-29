import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int [][] A = new int[N+1][3];
        int [][] D = new int[N+1][3];
        for(int i = 1; i < N+1; i++){
            st = new StringTokenizer(br.readLine());
            A[i][0] = Integer.parseInt(st.nextToken());
            A[i][1] = Integer.parseInt(st.nextToken());
            A[i][2] = Integer.parseInt(st.nextToken());
        }

        int min = Integer.MAX_VALUE;
        D[1][0] = A[1][0]; //R색으로 칠하고 마지막 집은 R색이 아닌 것 중 최소값 찾기
        D[1][1] = 1000001;
        D[1][2] = 1000001;
        for(int i = 2; i < N+1; i++){
            D[i][0] = Math.min(D[i-1][1], D[i-1][2]) + A[i][0]; //전에 칠한 집과 색이 겹치지 않게 DP 업데이트
            D[i][1] = Math.min(D[i-1][0], D[i-1][2]) + A[i][1];
            D[i][2] = Math.min(D[i-1][0], D[i-1][1]) + A[i][2];
        }
        min = Math.min(min, Math.min(D[N][1], D[N][2])); //1번 집에서 R색을 칠했으므로 마지막 집은 G 또는 B색 중 최소값 찾기
        D[1][0] = 1000001;
        D[1][1] = A[1][1];
        D[1][2] = 1000001;
        for(int i = 2; i < N+1; i++){
            D[i][0] = Math.min(D[i-1][1], D[i-1][2]) + A[i][0];
            D[i][1] = Math.min(D[i-1][0], D[i-1][2]) + A[i][1];
            D[i][2] = Math.min(D[i-1][0], D[i-1][1]) + A[i][2];
        }
        min = Math.min(min, Math.min(D[N][0], D[N][2]));
        D[1][0] = 1000001;
        D[1][1] = 1000001;
        D[1][2] = A[1][2];
        for(int i = 2; i < N+1; i++){
            D[i][0] = Math.min(D[i-1][1], D[i-1][2]) + A[i][0];
            D[i][1] = Math.min(D[i-1][0], D[i-1][2]) + A[i][1];
            D[i][2] = Math.min(D[i-1][0], D[i-1][1]) + A[i][2];
        }
        min = Math.min(min, Math.min(D[N][0], D[N][1]));
        System.out.println(min);
    }
}
