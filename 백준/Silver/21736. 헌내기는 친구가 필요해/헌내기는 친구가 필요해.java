import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int M;
    static int [][] A;
    static int [] start = new int[2];
    static int [] dy = {-1, 1, 0, 0};
    static int [] dx = {0, 0, -1, 1};
    static int cnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N][M];
        for(int i = 0; i < N; i++){
            char [] s = br.readLine().toCharArray();
            for(int j = 0; j < M; j++){
                if(s[j] == 'O') A[i][j] = 0;
                else if(s[j] == 'X') A[i][j] = 1;
                else if(s[j] == 'P') A[i][j] = 2;
                else{
                    start[0] = i;
                    start[1] = j;
                    A[i][j] = 0;
                }
            }
        }
        BFS();
        if(cnt == 0) System.out.println("TT");
        else System.out.println(cnt);
    }

    static void BFS(){
        boolean [][] visited = new boolean[N][M];
        visited[start[0]][start[1]] = true;
        Queue<int []> queue = new LinkedList<>();
        queue.add(new int[] {start[0], start[1]});
        while(!queue.isEmpty()){
            int [] now = queue.poll();
            if(A[now[0]][now[1]] == 2) cnt++;
            for(int i = 0; i < 4; i++){
                int y = now[0]+dy[i];
                int x = now[1]+dx[i];
                if(y > N-1 || y < 0 || x > M-1 || x < 0 || visited[y][x] || A[y][x] == 1) continue;
                visited[y][x] = true;
                queue.add(new int[] {y, x});
            }
        }
    }
}