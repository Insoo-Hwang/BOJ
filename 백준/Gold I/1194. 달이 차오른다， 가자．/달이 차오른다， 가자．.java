import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static char [][] A;
    static int [] start = new int[2];
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
                if(A[i][j] == '0'){
                    start[0] = i;
                    start[1] = j;
                    A[i][j] = '.';
                }
            }
        }
        System.out.println(BFS());
    }

    static int BFS(){
        boolean [][][] visited = new boolean[N][M][1<<6];
        visited[start[0]][start[1]][0] = true;
        Queue<int []> queue = new LinkedList<>();
        queue.add(new int [] {start[0], start[1], 0, 0});
        while(!queue.isEmpty()){
            int [] now = queue.poll();
            if(A[now[0]][now[1]] == '1') return now[3];
            for(int i = 0; i < 4; i++){
                int y = now[0]+dy[i];
                int x = now[1]+dx[i];
                if(y > N-1 || y < 0 || x > M-1 || x < 0 || A[y][x] == '#' || visited[y][x][now[2]]) continue;
                int key = now[2];
                if(A[y][x]-64 > 0 && A[y][x]-64 < 7 && (key & (1<<(A[y][x]-65))) == 0) continue;
                else if(A[y][x]-96 > 0 && A[y][x]-96 < 7) key |= (1<<(A[y][x]-97));
                queue.add(new int[] {y, x, key, now[3]+1});
                visited[y][x][key] = true;
                visited[y][x][now[2]] = true;
            }
        }
        return -1;
    }
}