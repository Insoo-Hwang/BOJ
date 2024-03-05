import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int M;
    static int [][] A;
    static int item = 1;
    static int [] start = new int[2];
    static int [] dy = {-1, 1, 0, 0};
    static int [] dx = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        A = new int[N][M];
        for(int i = 0; i < N; i++){
            char [] s = br.readLine().toCharArray();
            for(int j = 0; j < M; j++){
                if(s[j] == '.') A[i][j] = 0;
                else if(s[j] == 'X') A[i][j] = item++;
                else if(s[j] == '#') A[i][j] = 6;
                else if(s[j] == 'E') A[i][j] = 7;
                else{
                    start[0] = i;
                    start[1] = j;
                    A[i][j] = 0;
                }
            }
        }
        item--;
        System.out.print(BFS());
    }

    static int BFS(){
        boolean [][][] visited = new boolean[N][M][1<<item];
        visited[start[0]][start[1]][0] = true;
        Queue<int []> queue = new LinkedList<>();
        queue.add(new int [] {start[0], start[1], 0, 0});
        while(!queue.isEmpty()){
            int [] now = queue.poll();
            if(A[now[0]][now[1]] == 7 && now[2] == (1<<item)-1) return now[3];
            for(int i = 0; i < 4; i++){
                int y = now[0]+dy[i];
                int x = now[1]+dx[i];
                if(y > N-1 || y < 0 || x > M-1 || x < 0 || visited[y][x][now[2]] || A[y][x] == 6) continue;
                if(A[y][x] > 0 && A[y][x] < 6){
                    visited[y][x][now[2]] = true;
                    visited[y][x][now[2]|(1<<(A[y][x]-1))] = true;
                    queue.add(new int[] {y, x, now[2]|(1<<(A[y][x]-1)), now[3]+1});
                }
                else{
                    visited[y][x][now[2]] = true;
                    queue.add(new int[] {y, x, now[2], now[3]+1});
                }
            }
        }
        return 0;
    }
}