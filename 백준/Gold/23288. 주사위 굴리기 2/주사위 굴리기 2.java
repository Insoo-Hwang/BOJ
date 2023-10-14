import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int [][] A;
    static int [][] mark;
    static int [] score;
    static boolean [][] visited;
    static int [] dy = {0, -1, 0, 1};
    static int [] dx = {1, 0, -1, 0};
    static int one = 1;
    static int two = 4;
    static int three = 3;
    static int four = 5;
    static int five = 6;
    static int six = 2;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        A = new int [N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        mark = new int[N][M];
        visited = new boolean[N][M];
        score = new int[N*M];
        int ma = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(!visited[i][j]){
                    BFS(i, j, ma);
                    ma++;
                }
            }
        }

        int n = 0;
        int m = 0;
        int d = 0;
        int s = 0;
        for(int i = 0; i < K; i++){
            if(five < A[n][m] && i != 0){
                d = (d+1)%4;
            }
            else if(five > A[n][m] && i != 0){
                d--;
                if(d == -1) d = 3;
            }
            int y = n+dy[d];
            int x = m+dx[d];
            if(y > N-1 || y < 0 || x > M-1 || x < 0){
                d = (d+2)%4;
                y = n+dy[d];
                x = m+dx[d];
            }
            n = y;
            m = x;
            if(d == 0) right();
            else if(d == 1) up();
            else if(d == 2) left();
            else down();
            s+=score[mark[n][m]];
        }
        System.out.println(s);
    }

    static void BFS(int n, int m, int ma){
        Queue<int []> queue = new LinkedList<>();
        queue.add(new int [] {n, m, A[n][m]});
        visited[n][m] = true;
        int cnt = 0;
        while(!queue.isEmpty()){
            int [] now = queue.poll();
            mark[now[0]][now[1]] = ma;
            cnt+=now[2];
            for(int i = 0; i < 4; i++){
                int y = now[0]+dy[i];
                int x = now[1]+dx[i];
                if(y > N-1 || y < 0 || x > M-1 || x < 0 || visited[y][x] || A[y][x] != now[2]) continue;
                queue.add(new int [] {y, x, A[y][x]});
                visited[y][x] = true;
            }
        }
        score[ma] = cnt;
    }

    static void left(){
        int temp = one;
        one = three;
        three = five;
        five = two;
        two = temp;
    }

    static void right(){
        int temp = one;
        one = two;
        two = five;
        five = three;
        three = temp;
    }

    static void up(){
        int temp = one;
        one = four;
        four = five;
        five = six;
        six = temp;
    }

    static void down(){
        int temp = one;
        one = six;
        six = five;
        five = four;
        four = temp;
    }
}