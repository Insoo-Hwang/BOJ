import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int[][] visited;
    static int cnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        visited = new int[N][N];

        DFS(0);
        System.out.println(cnt);
    }

    static void DFS(int d){
        if(d == N){
            cnt++;
            return;
        }
        for(int i = 0; i < N; i++){ //체스판의 가로 방향으로 이동, 세로 방향은 d로 확인
            if(visited[d][i] == 0){
                check(d, i, 1); //(d, i)에 퀸을 놓았을때 퀸에 잡히는 모든 위치를 방문 처리
                DFS(d+1);
                check(d, i, -1);
            }
        }
    }

    static void check(int n, int m, int b){
        int x = n - m; //좌상우하 대각선 방향 확인을 위한 변수
        for(int i = 0; i < N; i++){
            visited[i][m] += b; //세로 방향 방문 처리
            visited[n][i] += b; //가로 방향 방문 처리
            if(i-x < N && i-x >= 0)
                visited[i][i-x] += b; //좌상우하 대각선 방문 처리
            int y = n - i; //좌하우상 대각선 방향 확인을 위한 변수
            if(n-y < N && n-y >= 0 && m+y < N && m+y >= 0)
                visited[n-y][m+y] += b; //좌하우상 대각선 방문 처리
        }
    }
}
