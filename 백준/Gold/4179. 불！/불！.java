import java.util.*;
import java.io.*;

public class Main {
    static int R;
    static int C;
    static int [][] A;
    static int sn = 0;
    static int sm = 0;
    static int [] dy = {-1, 1, 0, 0};
    static int [] dx = {0, 0, -1, 1};
    static List<int []> newFire = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        A = new int[R][C];
        for(int i = 0; i < R; i++){
            char [] s = br.readLine().toCharArray();
            for(int j = 0; j < C; j++){
                if(s[j] == '.' && (i != 0 && i != R-1 && j != 0 && j != C-1)) A[i][j] = 0;
                else if(s[j] == '.') A[i][j] = 1;
                else if(s[j] == 'F'){
                    A[i][j] = 3;
                    newFire.add(new int[] {i, j, 0});
                }
                else if(s[j] == '#') A[i][j] = 4;
                else{
                    sn = i;
                    sm = j;
                    if(i != 0 && i != R-1 && j != 0 && j != C-1) A[i][j] = 0;
                    else A[i][j] = 1;
                }
            }
        }
        int escape = BFS();
        if(escape == -1) System.out.println("IMPOSSIBLE");
        else System.out.println(escape);
    }

    static int BFS(){
        Queue<int []> queue = new LinkedList<>();
        queue.add(new int[] {sn, sm, 0});
        boolean [][] visited = new boolean[R][C];
        visited[sn][sm] = true;
        int time = 0;
        while(!queue.isEmpty()){
            int [] now = queue.poll();
            if(A[now[0]][now[1]] == 1){
                return now[2]+1;
            }
            if(now[2] == time){
                fire(time);
                time++;
            }
            for(int i = 0; i < 4; i++){
                int y = now[0]+dy[i];
                int x = now[1]+dx[i];
                if(y > R-1 || y < 0 || x > C-1 || x < 0 || visited[y][x] || A[y][x] == 4 || A[y][x] == 3 || A[y][x] == 2) continue;
                visited[y][x] = true;
                queue.add(new int[] {y, x, now[2]+1});
            }
        }
        return -1;
    }

    static void fire(int time){
        while(!newFire.isEmpty()){
            int [] now = newFire.remove(0);
            if(now[2] != time){
                newFire.add(now);
                break;
            }
            A[now[0]][now[1]] = 2;
            for(int i = 0; i < 4; i++){
                int y = now[0]+dy[i];
                int x = now[1]+dx[i];
                if(y > R-1 || y < 0 || x > C-1 || x < 0 || A[y][x] == 4 || A[y][x] == 3 || A[y][x] == 2) continue;
                newFire.add(new int[] {y, x, time+1});
                A[y][x] = 3;
            }
        }
    }
}