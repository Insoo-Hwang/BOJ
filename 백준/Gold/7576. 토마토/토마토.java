import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int M;
    static int N;
    static int [][] A;
    static Queue<nm> queue = new LinkedList<>();
    static boolean[][] visited;
    static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        A = new int[N][M];
        visited = new boolean[N][M];
        boolean zero = false;
        boolean one = false;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                int temp = Integer.parseInt(st.nextToken());
                A[i][j] = temp;
                if(temp == 0) zero = true;
                if(temp == 1) {
                    one = true;
                    queue.add(new nm(i, j, 0)); //이미 익은 토마토는 queue에 삽입
                    visited[i][j] = true;
                }
            }
        }
        if(!zero){ //0이 없는 경우(모두 익은 경우)
            System.out.println(0);
            return;
        }
        if(!one){ //1이 없는 경우(익을 수 없는 경우)
            System.out.println(-1);
            return;
        }
        BFS();
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(A[i][j] == 0){
                    System.out.println(-1); //익지 않은 토마토가 있는 경우 -1출력
                    return;
                }
            }
        }
        System.out.println(max);
    }

    static void BFS(){
        while(!queue.isEmpty()){
            nm now = queue.poll();
            if(now.m < M-1){ //오른쪽 끝인지 확인
                if(!visited[now.n][now.m+1] && A[now.n][now.m+1] == 0){ //익지 않은 토마토가 있을때
                    visited[now.n][now.m+1] = true;
                    A[now.n][now.m+1] = 1;
                    queue.add(new nm(now.n, now.m+1, now.d+1)); //날짜를 하나 더해 저장
                    max = now.d+1; //queue에 해당 날짜 토마토가 들어 갔으므로 max값 갱신
                }
            }
            if(now.m > 0){ //왼쪽 끝인지 확인
                if(!visited[now.n][now.m-1] && A[now.n][now.m-1] == 0){
                    visited[now.n][now.m-1] = true;
                    A[now.n][now.m-1] = 1;
                    queue.add(new nm(now.n, now.m-1, now.d+1));
                    max = now.d+1;
                }
            }
            if(now.n < N-1){ //앞쪽 끝인지 확인
                if(!visited[now.n+1][now.m] && A[now.n+1][now.m] == 0){
                    visited[now.n+1][now.m] = true;
                    A[now.n+1][now.m] = 1;
                    queue.add(new nm(now.n+1, now.m, now.d+1));
                    max = now.d+1;
                }
            }
            if(now.n > 0){ //뒷쪽 끝인지 확인
                if(!visited[now.n-1][now.m] && A[now.n-1][now.m] == 0){
                    visited[now.n-1][now.m] = true;
                    A[now.n-1][now.m] = 1;
                    queue.add(new nm(now.n-1, now.m, now.d+1));
                    max = now.d+1;
                }
            }
        }
    }
}
class nm{
    int n; //n좌표
    int m; //m좌표
    int d; //익은 날짜
    public nm(int n, int m, int d){
        super();
        this.n = n;
        this.m = m;
        this.d = d;
    }
}
