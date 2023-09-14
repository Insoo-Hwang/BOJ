import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int [][] A;
    static boolean [][] visited;
    static boolean [][] cycle;
    static int cnt = 0;
    static int [] dy = {-1, 1, 0, 0};
    static int [] dx = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N][M];
        for(int i = 0; i < N; i++){
            String s = br.readLine();
            for(int j = 0; j < M; j++){
                char c = s.charAt(j);
                if(c == 'U') A[i][j] = 0;
                else if(c == 'D') A[i][j] = 1;
                else if(c == 'L') A[i][j] = 2;
                else if(c == 'R') A[i][j] = 3;
            }
        }

        visited = new boolean[N][M]; //방문 여부 확인
        cycle = new boolean[N][M]; //사이클 여부 확인
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(visited[i][j]) continue; //이미 지나온 곳이면 패스
                DFS(i, j);
            }
        }
        System.out.println(cnt);
    }

    static void DFS(int n, int m){
        visited[n][m] = true;

        int y = n + dy[A[n][m]];
        int x = m + dx[A[n][m]];
        if(!visited[y][x]){ //방문하지 않은 곳이면
            DFS(y, x); //계속 진행
        }
        else{
            if(!cycle[y][x]){ //이미 사이클이었던 곳인지 확인 후 아니면 세이프티 존 추가
                cnt++;
            }
        }
        cycle[n][m] = true; //지나온 곳은 사이클로 처리
    }
}
