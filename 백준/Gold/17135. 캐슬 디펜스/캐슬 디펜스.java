import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int D;
    static int [][] A;
    static int [] arrow = new int[3];
    static int [] dy = {0, -1, 0};
    static int [] dx = {-1, 0, 1};
    static int max = 0;
    static boolean maxCheck = false;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        A = new int[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        DFS(0, 0);
        System.out.println(max);
    }

    static void DFS(int d, int s){
        if(d == 3){
            max = Math.max(max, match());
            return;
        }

        for(int i = s; i < M; i++){
            arrow[d] = i;
            DFS(d+1, i+1);
            if(maxCheck) return;
        }
    }

    static int match(){
        int [][] map = new int[N+1][M];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                map[i][j]  = A[i][j];
            }
        }

        int cnt = 0;
        boolean [][] visited;
        for(int i = 0; i < N; i++){
            if(max >= 3*(N-i)+cnt) break;
            List<int []> dead =  new LinkedList<>();
            for(int j = 0; j < 3; j++){
                visited = new boolean[N+1][M];
                visited[N-i][arrow[j]] = true;
                Queue<int []> queue = new LinkedList<>();
                queue.add(new int[] {N-i, arrow[j], 0});
                while(!queue.isEmpty()){
                    int [] now = queue.poll();
                    if(now[2] > D) break;
                    if(map[now[0]][now[1]] == 1 && now[0] < N-i){
                        dead.add(new int[] {now[0], now[1]});
                        break;
                    }
                    for(int k = 0; k < 3; k++){
                        int y = now[0]+dy[k];
                        int x = now[1]+dx[k];
                        if(y > N || y < 0 || x > M-1 || x < 0 || visited[y][x]) continue;
                        visited[y][x] = true;
                        queue.add(new int[] {y, x, now[2]+1});
                    }
                }
            }
            for(int [] d : dead){
                if(map[d[0]][d[1]] != 1) continue;
                map[d[0]][d[1]] = 0;
                cnt++;
            }
        }
        if(cnt == N*3) maxCheck = true;
        return cnt;
    }
}