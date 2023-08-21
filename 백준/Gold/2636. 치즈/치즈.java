import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int [][] A;
    static boolean [][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int hour = 0;
        int cnt = 0;
        while (check() != 0) {
            cnt = check(); //모두 녹기 한 시간 전에 남아 있는 치즈 조각 수
            visited = new boolean[N][M]; 
            outAir(0, 0); //매번 없어지는 치즈가 있기 때문에 매번 겉과 안의 공기를 파악해주어야 함
            BFS();
            hour++;
        }
        System.out.println(hour);
        System.out.println(cnt);
    }
    static void BFS(){
        int [][] B = new int[N][M];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                B[i][j] = A[i][j]; //한번에 치즈가 없어져야 하므로 clone을 만들어 관리 후 계산이 모두 끝난 후 적용
            }
        }

        for(int i = 1; i < N-1; i++){
            for(int j = 1; j < M-1; j++){
                if(A[i][j] != 1) continue; //공기인 경우 패스
                if(A[i-1][j] == 2 || A[i+1][j] == 2 || A[i][j-1] == 2 || A[i][j+1] == 2){ //주변에 겉 공기가 있는 경우
                    B[i][j] = 0; //치즈가 사라짐
                }
            }
        }
        A = B.clone();
    }

    static int check(){ //남아 있는 치즈 수 파악
        int temp = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(A[i][j] == 1)
                   temp++;
            }
        }
        return temp;
    }

    static void outAir(int n, int m){ //겉과 안 공기 파악
        if(visited[n][m] || A[n][m] == 1){ //이미 방문했거나 치즈인 경우 패스
            return;
        }
        visited[n][m] = true;
        A[n][m] = 2; //겉 공기는 2로 표시
        if(n-1 > -1 && !visited[n-1][m] && A[n-1][m] != 1) //겉 공기로 파악되면 DFS로 이동
            outAir(n-1, m);
        if(n+1 < N && !visited[n+1][m] && A[n+1][m] != 1)
            outAir(n+1, m);
        if(m-1 > -1 && !visited[n][m-1] && A[n][m-1] != 1)
            outAir(n, m-1);
        if(m+1 < M && !visited[n][m+1] && A[n][m+1] != 1)
            outAir(n, m+1);
    }
}
