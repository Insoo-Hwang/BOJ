import java.util.*;

public class Solution {
    static int N;
    static int [][] A;
    static int [] dy = {-1, 1, 0, 0};
    static int [] dx = {0, 0, -1, 1};
    static int min;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for(int t = 1; t < T+1; t++){
            N = sc.nextInt();
            A = new int[N][N];
            min = Integer.MAX_VALUE;
            for(int i = 0; i < N; i++){
                String s = sc.next();
                for(int j = 0; j < N; j++){
                    A[i][j] = s.charAt(j) - '0';
                }
            }

            BFS();
            System.out.print("#" + t + " ");
            System.out.println(min);
        }
    }

    static void BFS(){
        int [][] visited = new int[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                visited[i][j] = Integer.MAX_VALUE; //방문 배열보다 값이 작으면 이미 방문한 것으로 처리
            }
        }
        visited[0][0] = 0;
        Queue<Route> queue = new LinkedList<>();
        queue.add(new Route(0, 0, 0));

        while(!queue.isEmpty()){
            Route now = queue.poll();
            if(now.n == N-1 && now.m == N-1){
                min = Math.min(min, now.c); //도착칸에 도달하면 값 업데이트
                continue;
            }
            for(int i = 0; i < 4; i++){
                int y = now.n + dy[i];
                int x = now.m + dx[i];
                if(y > N-1 || y < 0 || x > N-1 || x < 0 || now.c+A[y][x] >= visited[y][x]) continue; //이동하려는 칸의 공사시간보다 길면 패스
                visited[y][x] = now.c+A[y][x]; //이동하려는 칸의 공사시간 보다 짧으면 업데이트
                queue.add(new Route(y, x, now.c+A[y][x]));
            }
        }
    }

    static class Route{
        int n; //n좌표
        int m; //m좌표
        int c; //공사 시간
        public Route(int n, int m, int c){
            this.n = n;
            this.m = m;
            this.c = c;
        }
    }
}
