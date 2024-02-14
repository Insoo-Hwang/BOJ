import java.io.*;
import java.util.*;

public class Main {
    static int w;
    static int h;
    static char [][] A;
    static int [] start = new int[2];
    static int dirty;
    static int [] dy = {-1, 1, 0, 0};
    static int [] dx = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        while(true){
            StringTokenizer st = new StringTokenizer(br.readLine());
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            if(w == 0 && h == 0) break;
            A = new char[h][w];
            dirty = 0;
            for(int i = 0; i < h; i++){
                char [] s = br.readLine().toCharArray();
                for(int j = 0; j < w; j++){
                    A[i][j] = s[j];
                    if(A[i][j] == 'o'){
                        start[0] = i;
                        start[1] = j;
                        A[i][j] = '.';
                    }
                    else if(A[i][j] == '*'){
                        A[i][j] = (char) (dirty+'0');
                        dirty++;
                    }
                }
            }
            sb.append(BFS()).append("\n");
        }
        System.out.println(sb);
    }

    static int BFS(){
        boolean [][][] visited = new boolean[h][w][1<<dirty];
        visited[start[0]][start[1]][0] = true;
        Queue<int []> queue = new LinkedList<>();
        queue.add(new int [] {start[0], start[1], 0, 0});
        while(!queue.isEmpty()){
            int [] now = queue.poll();
            if(now[2] == (1<<dirty)-1) return now[3];
            for(int i = 0; i < 4; i++){
                int y = now[0]+dy[i];
                int x = now[1]+dx[i];
                if(y > h-1 || y < 0 || x > w-1 || x < 0 || A[y][x] == 'x' || visited[y][x][now[2]]) continue;
                int temp = now[2];
                if(A[y][x] != '.' && A[y][x] != 'x'){
                    int num = A[y][x]-'0';
                    temp |= (1<<num);
                }
                visited[y][x][now[2]] = true;
                visited[y][x][temp] = true;
                queue.add(new int[] {y, x, temp, now[3]+1});
            }
        }
        return -1;
    }
}