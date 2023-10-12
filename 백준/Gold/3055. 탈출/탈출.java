import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int R;
    static int C;
    static char[][] A;
    static PriorityQueue<Water> queue;
    static boolean [][] visited0;
    static boolean [][] visited1;
    static int [] dy = {-1, 1, 0, 0};
    static int [] dx = {0, 0, -1, 1};
    static int time = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        A = new char[R][C];
        visited0 = new boolean[R][C];
        visited1 = new boolean[R][C];
        queue = new PriorityQueue<>();
        for(int i = 0; i < R; i++){
            String s = br.readLine();
            for(int j = 0; j < C; j++){
                A[i][j] = s.charAt(j);
                if(A[i][j] == '*'){
                    queue.add(new Water(i, j, 0, -1));
                    visited0[i][j] = true;
                    visited1[i][j] = true;
                }
                else if(A[i][j] == 'S'){
                    queue.add(new Water(i, j , 1, 0));
                    visited1[i][j] = true;
                }
                else if(A[i][j] == 'X'){
                    visited0[i][j] = true;
                    visited1[i][j] = true;
                }
            }
        }
        BFS();
        if(time == Integer.MAX_VALUE) System.out.println("KAKTUS");
        else System.out.println(time);
    }

    static void BFS(){
        while(!queue.isEmpty()){
            Water now = queue.poll();
            if(A[now.n][now.m] == 'D'){
                time = now.c;
                break;
            }
            if(now.j == 0){
                for(int i = 0; i < 4; i++){
                    int y = now.n+dy[i];
                    int x = now.m+dx[i];
                    if(y > R-1 || y < 0 || x > C-1 || x < 0 || visited0[y][x] || A[y][x] == 'D') continue;
                    visited0[y][x] = true;
                    visited1[y][x] = true;
                    queue.add(new Water(y, x, now.j, now.c+1));
                }
            }
            else{
                for(int i = 0; i < 4; i++){
                    int y = now.n+dy[i];
                    int x = now.m+dx[i];
                    if(y > R-1 || y < 0 || x > C-1 || x < 0 || visited0[y][x] || visited1[y][x]) continue;
                    visited1[y][x] = true;
                    queue.add(new Water(y, x, now.j, now.c+1));
                }
            }
        }
    }

    static class Water implements Comparable<Water>{
        int n;
        int m;
        int j;
        int c;

        public Water(int n, int m, int j, int c){
            this.n = n;
            this.m = m;
            this.j = j;
            this.c = c;
        }

        @Override
        public int compareTo(Water o) {
            if(this.c == o.c) {
                return o.j - this.j;
            }
            return this.c-o.c;
        }
    }
}