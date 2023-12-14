import java.util.*;
import java.io.*;

public class Main {
    static int R;
    static int C;
    static int [][] A;
    static boolean [][] visited;
    static int sheep = 0;
    static int wolf = 0;
    static int [] dy = {-1, 1, 0, 0};
    static int [] dx = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        A = new int [R][C];
        visited = new boolean[R][C];
        for(int i = 0; i < R; i++){
            char[] s = br.readLine().toCharArray();
            for(int j = 0; j < C; j++){
                if(s[j] == '#') A[i][j] = 3;
                else if(s[j] == '.') A[i][j] = 0;
                else if(s[j] == 'o') A[i][j] = 1;
                else A[i][j] = 2;
            }
        }

        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                if((i != 0 && i != R-1 && j != 0 && j != C-1) || visited[i][j] || A[i][j] == 3) continue;
                BFS(i, j);
            }
        }
        sheep = 0;
        wolf = 0;
        for(int i = 1; i < R-1; i++){
            for(int j = 1; j < C-1; j++){
                if(visited[i][j] || A[i][j] == 3) continue;
                BFS(i, j);
            }
        }
        System.out.println(sheep + " " + wolf);
    }

    static void BFS(int n, int m){
        Queue<int []> queue = new LinkedList<>();
        queue.add(new int[] {n, m});
        visited[n][m] = true;
        int s = 0;
        int w = 0;
        while(!queue.isEmpty()){
            int [] now = queue.poll();
            if(A[now[0]][now[1]] == 1) s++;
            else if(A[now[0]][now[1]] == 2) w++;
            for(int i = 0; i < 4; i++){
                int y = now[0]+dy[i];
                int x = now[1]+dx[i];
                if(y < 0 || y > R-1 || x < 0 || x > C-1 || visited[y][x] || A[y][x] == 3) continue;
                visited[y][x] = true;
                queue.add(new int [] {y, x});
            }
        }
        if(s > w){
            sheep+=s;
        }
        else{
            wolf+=w;
        }
    }
}