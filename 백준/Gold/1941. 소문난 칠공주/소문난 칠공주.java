import java.io.*;
import java.util.*;

public class Main {
    static int [][] school = new int[5][5];
    static int [] A = new int [7];
    static boolean [][] check = new boolean[5][5];
    static int cnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < 5; i++){
            char [] s = br.readLine().toCharArray();
            for(int j = 0; j < 5; j++){
                if(s[j] == 'Y') school[i][j] = 1;
                else school[i][j] = 0;
            }
        }
        DFS(-1, 0);
        System.out.println(cnt);
    }

    static void DFS(int s, int d){
        if(d == 7){
            if(check()){
                System.out.print("");
                cnt++;
            }
            return;
        }
        for(int i = s+1; i < 25; i++){
            A[d] = i;
            check[i/5][i%5] = true;
            DFS(i, d+1);
            check[i/5][i%5] = false;
        }
    }

    static boolean check(){
        int count = 0;
        for(int i = 0; i < 7; i++){
            if(school[A[i]/5][A[i]%5] == 1) count++;
        }
        if(BFS(A[0]/5, A[0]%5) && count < 4) return true;
        return false;
    }

    static boolean BFS(int n, int m){
        int [] dy = {-1, 1, 0, 0};
        int [] dx = {0, 0, -1, 1};
        boolean [][] visited = new boolean[5][5];
        visited[n][m] = true;
        Queue<int []> queue = new LinkedList<>();
        queue.add(new int[] {n, m});
        int count = 0;
        while(!queue.isEmpty()){
            int [] now = queue.poll();
            count++;
            for(int i = 0; i < 4; i++){
                int y = now[0]+dy[i];
                int x = now[1]+dx[i];
                if(y > 4 || y < 0 || x > 4 || x < 0 || visited[y][x] || !check[y][x]) continue;
                visited[y][x] = true;
                queue.add(new int[]{y, x});
            }
        }
        if(count == 7) return true;
        else return false;
    }
}