import java.util.*;
import java.io.*;

public class Main {
    static int H;
    static int W;
    static int [][] A;
    static boolean [][] visited;
    static int [] dy = {-1, 1, 0, 0};
    static int [] dx = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        while(T-->0) {
            st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            A = new int[H][W];
            visited = new boolean[H][W];
            for(int i = 0; i < H; i++){
                char [] s = br.readLine().toCharArray();
                for(int j = 0; j < W; j++){
                    if(s[j] == '#') A[i][j] = 1;
                    else A[i][j] = 0;
                }
            }
            int cnt = 0;
            for(int i = 0; i < H; i++){
                for(int j = 0; j < W; j++){
                    if(visited[i][j] || A[i][j] == 0) continue;
                    DFS(i, j);
                    cnt++;
                }
            }
            System.out.println(cnt);
        }
    }

    static void DFS(int n, int m){
        visited[n][m] = true;
        Queue<int []> queue = new LinkedList<>();
        queue.add(new int [] {n, m});
        while(!queue.isEmpty()){
            int [] now = queue.poll();
            for(int i = 0; i < 4; i++){
                int y = now[0]+dy[i];
                int x = now[1]+dx[i];
                if(y > H-1 || y < 0 || x > W-1 || x < 0 || visited[y][x] || A[y][x] == 0) continue;
                visited[y][x] = true;
                queue.add(new int [] {y, x});
            }
        }
    }
}