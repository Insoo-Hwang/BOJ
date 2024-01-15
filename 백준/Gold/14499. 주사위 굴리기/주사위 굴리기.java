import java.util.*;
import java.io.*;

public class Main {
    static int [] dice = new int[7];
    static int [] dx = {0, 0, 0, -1, 1};
    static int [] dy = {0, 1, -1, 0, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int [][] A = new int[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i = 0; i < 7; i++) dice[i] = i;
        int [] B = new int[7];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < K; i++){
            int d = Integer.parseInt(st.nextToken());
            x+=dx[d];
            y+=dy[d];
            if(x > N-1 || x < 0 || y > M-1 || y < 0){
                x-=dx[d];
                y-=dy[d];
                continue;
            }
            roll(d);
            if(A[x][y] == 0){
                A[x][y] = B[dice[6]];
            }
            else{
                B[dice[6]] = A[x][y];
                A[x][y] = 0;
            }
            sb.append(B[dice[1]]+"\n");
        }
        System.out.println(sb);
    }

    static void roll(int d){
        if(d == 1){
            int temp = dice[1];
            dice[1] = dice[4];
            dice[4] = dice[6];
            dice[6] = dice[3];
            dice[3] = temp;
        }
        else if(d == 2){
            int temp = dice[1];
            dice[1] = dice[3];
            dice[3] = dice[6];
            dice[6] = dice[4];
            dice[4] = temp;
        }
        else if(d == 3){
            int temp = dice[1];
            dice[1] = dice[5];
            dice[5] = dice[6];
            dice[6] = dice[2];
            dice[2] = temp;
        }
        else{
            int temp = dice[1];
            dice[1] = dice[2];
            dice[2] = dice[6];
            dice[6] = dice[5];
            dice[5] = temp;
        }
    }
}