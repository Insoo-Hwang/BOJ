import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int [][] A;
    static int [] dx = {0, 1, 0, -1};
    static int [] dy = {1, 0, -1, 0};
    static int min = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        if(N == 1 && M == 1){
            System.out.println(1);
            return;
        }
        A = new int[N][M];
        for(int i = 0; i < N; i++){
            String[] s = br.readLine().split("");
            for(int j = 0; j < M; j++){
                int temp = Integer.parseInt(s[j]);
                A[i][j] = temp;
            }
        }

        BFS();

        System.out.println(min);
    }

    public static void BFS(){
        boolean [][][] visited = new boolean[2][N][M]; //벽을 부순 경우와 부수지 않은 경우로 나눔
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0, 0, 1, 0}); //0 : n, 1 : m, 2 : 경로, 3 : 부순 벽의 수
        while(!queue.isEmpty()){
            int[] now = queue.poll();
            if(now[0] == N-1 && now[1] == M-1) { //도착한 경우
                min = now[2];
                break;
            }

            int tempb = now[3]; //부순 벽의 수 백업
            for(int i = 0; i < 4; i++){
                int x = now[0] + dx[i];
                int y = now[1] + dy[i];
                if(x >= 0 && y >= 0 && x < N && y < M){
                    if(!visited[now[3]][x][y] && A[x][y] == 0){ //벽이 없는 경우
                        visited[now[3]][x][y] = true; //해당 모드에 맞는 방문 배열에 방문 표시
                        queue.add(new int[]{x, y, now[2]+1, now[3]});
                    }
                    else if(!visited[now[3]][x][y] && tempb == 0){ //벽이 있는데 이미 부수지 않은 경우
                        visited[1][x][y] = true; //벽을 부순 모드 방문 배열에 방문 표시
                        queue.add(new int[]{x, y, now[2]+1, 1});
                    }
                }
            }
        }
    }
}
