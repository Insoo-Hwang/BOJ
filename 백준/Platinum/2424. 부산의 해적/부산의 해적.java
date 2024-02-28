import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static char [][] A;
    static int [] sua = new int[2];
    static int [] busan = new int[2];
    static int [][] busanvisited;
    static int [] dy = {-1, 1, 0, 0};
    static int [] dx = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new char[N][M];
        for(int i = 0; i < N; i++){
            char [] s = br.readLine().toCharArray();
            for(int j = 0; j < M; j++){
                A[i][j] = s[j];
                if(A[i][j] == 'Y'){
                    sua[0] = i;
                    sua[1] = j;
                    A[i][j] = '.';
                }
                else if(A[i][j] == 'V'){
                    busan[0] = i;
                    busan[1] = j;
                    A[i][j] = '.';
                }
            }
        }
        if(BFS()) System.out.println("YES");
        else System.out.println("NO");
    }

    static boolean BFS(){
        busanvisited = new int[N][M];
        for(int i = 0; i < N; i++) Arrays.fill(busanvisited[i], -1);
        busanvisited[busan[0]][busan[1]] = 0;
        Queue<int []> queue = new LinkedList<>();
        queue.add(new int[] {busan[0], busan[1], 0});
        while(!queue.isEmpty()){
            int [] now = queue.poll();
            for(int i = 0; i < 4; i++){
                int y = now[0]+dy[i];
                int x = now[1]+dx[i];
                if(y > N-1 || y < 0 || x > M-1 || x < 0 || busanvisited[y][x] != -1 || A[y][x] == 'I') continue;
                queue.add(new int[] {y, x, now[2]+1});
                busanvisited[y][x] = now[2]+1;
            }
        }

        boolean [][] visited = new boolean[N][M];
        visited[sua[0]][sua[1]] = true;
        queue = new LinkedList<>();
        queue.add(new int [] {sua[0], sua[1], 0});
        while(!queue.isEmpty()){
            int [] now = queue.poll();
            if(A[now[0]][now[1]] == 'T') return true;
            for(int i = 0; i < 4; i++){
                int y = now[0]+dy[i];
                int x = now[1]+dx[i];
                if(y > N-1 || y < 0 || x > M-1 || x < 0 || visited[y][x] || A[y][x] == 'I' || kill(y, x, now[2]+1)) continue;
                queue.add(new int[] {y, x, now[2]+1});
                visited[y][x] = true;
            }
        }
        return false;
    }

    static boolean kill(int y, int x, int n){
        for(int i = y; i > -1; i--){
            if(busanvisited[i][x] == -1) break;
            if(busanvisited[i][x] <= n) return true;
        }
        for(int i = y; i < N; i++){
            if(busanvisited[i][x] == -1) break;
            if(busanvisited[i][x] <= n) return true;
        }
        for(int i = x; i > -1; i--){
            if(busanvisited[y][i] == -1) break;
            if(busanvisited[y][i] <= n) return true;
        }
        for(int i = x; i < M; i++){
            if(busanvisited[y][i] == -1) break;
            if(busanvisited[y][i] <= n) return true;
        }
        return false;
    }
}