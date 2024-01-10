import java.io.*;
import java.util.*;

public class Main {
    static int L;
    static int R;
    static int C;
    static int [][][] A;
    static int [] start;
    static int [] dl = {-1 ,1, 0, 0, 0, 0};
    static int [] dr = {0, 0, -1, 1, 0, 0};
    static int [] dc = {0, 0, 0, 0, -1, 1};
    static int min;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringTokenizer st = new StringTokenizer(br.readLine());

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            if(L == 0 && R == 0 && C == 0) break;
            A = new int[L][R][C];
            start = new int[3];
            min = -1;
            for(int i = 0; i < L; i++){
                for(int j = 0; j < R; j++){
                    char[] s = br.readLine().toCharArray();
                    for(int k = 0; k < C; k++){
                        if(s[k] == '#') A[i][j][k] = 1;
                        else if(s[k] == '.') A[i][j][k] = 0;
                        else if(s[k] == 'E') A[i][j][k] = 2;
                        else{
                            A[i][j][k] = 0;
                            start[0] = i;
                            start[1] = j;
                            start[2] = k;
                        }
                    }
                }
                String s = br.readLine();
            }
            BFS();
            if(min == -1) System.out.println("Trapped!");
            else System.out.println("Escaped in " + min + " minute(s).");
        }
    }

    static void BFS(){
        boolean [][][] visited = new boolean[L][R][C];
        visited[start[0]][start[1]][start[2]] = true;
        Queue<int []> queue = new LinkedList<>();
        queue.add(new int [] {start[0], start[1], start[2], 0});
        while(!queue.isEmpty()){
            int [] now = queue.poll();
            if(A[now[0]][now[1]][now[2]] == 2){
                min = now[3];
            }
            for(int i = 0; i < 6; i++){
                int l = now[0] + dl[i];
                int r = now[1] + dr[i];
                int c = now[2] + dc[i];
                if(l > L-1 || l < 0 || r > R-1 || r < 0 || c > C-1 || c < 0 || visited[l][r][c] || A[l][r][c] == 1) continue;
                visited[l][r][c] = true;
                queue.add(new int [] {l, r, c, now[3]+1});
            }
        }
    }
}