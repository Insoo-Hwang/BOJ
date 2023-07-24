import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static boolean [][] visited; //DFS 방문배열
    static int [][] A; //빙하의 양
    static int N;
    static int M;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        visited = new boolean[N][M];
        A = new int[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                int temp = Integer.parseInt(st.nextToken());
                A[i][j] = temp;
            }
        }

        int cnt = 0; //햇수
        while(true){
            if(check()){ //빙하가 모두 0이면
                cnt = 0; //0출력
                break;
            }
            visited = new boolean[N][M];
            int sep = 0; //분리된 빙하의 개수
            for(int i = 0; i < N; i++){
                for(int j = 0; j < M; j++){
                    if(!visited[i][j] && A[i][j] != 0){ //빙하가 0이면 방문x
                        DFS(i, j);
                        sep++;
                    }
                }
            }
            if(sep > 1) break; //2조각 이상으로 분리시 while문 종료
            ice(); //한해가 지나면 빙하의 양 감소
            cnt++;
        }
        System.out.println(cnt);
    }

    static void DFS(int n, int m){
        if(visited[n][m] || A[n][m] == 0)
            return;
        visited[n][m] = true;
        if(!visited[n-1][m] && A[n-1][m] != 0) //이미 방문했거나 0이면 방문x
            DFS(n-1, m);
        if(!visited[n+1][m] && A[n+1][m] != 0)
            DFS(n+1, m);
        if(!visited[n][m-1] && A[n][m-1] != 0)
            DFS(n, m-1);
        if(!visited[n][m+1] && A[n][m+1] != 0)
            DFS(n, m+1);
    }

    static boolean check(){ //모든 빙하가 0인지 확인하는 함수
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(A[i][j] > 0)
                    return false;
            }
        }
        return true;
    }

    static void ice(){
        int [][] B = new int[N][M]; //그냥 A배열을 활용하면 빙하가 녹는 것이 한번에 이루어 지지 않음(이전 배열값의 영향을 받음) 
        for(int i = 1; i < N-1; i++){
            for(int j = 1; j < M-1; j++){
                B[i][j] = A[i][j]; //B = A.clone()활용 불가
            }
        }
        for(int i = 1; i < N-1; i++){
            for(int j = 1; j < M-1; j++){
                if(A[i-1][j] == 0 && B[i][j] > 0) //A배열 기준으로 근처 바다 확인 및 B배열으로 현재 빙하 상태파악 후 이미 바다면 감소x
                    B[i][j]--;
                if(A[i+1][j] == 0 && B[i][j] > 0)
                    B[i][j]--;
                if(A[i][j-1] == 0 && B[i][j] > 0)
                    B[i][j]--;
                if(A[i][j+1] == 0 && B[i][j] > 0)
                    B[i][j]--;
            }
        }
        A = B.clone();
    }
}
