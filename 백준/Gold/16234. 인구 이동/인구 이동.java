import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int L;
    static int R;
    static int [][] A;
    static boolean [][] visited;
    static int [] dx = {1, -1, 0, 0};
    static int [] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        A = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;
        while(true) {
            if(!check()) break;
            visited = new boolean[N][N]; //하루가 지나면 방문 배열 초기화
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if(!visited[i][j])
                        BFS(i, j);
                }

            }
            cnt++;
        }
        System.out.println(cnt);
    }

    public static void BFS(int x, int y){
        Queue<int[]> queue = new LinkedList<>(); //방문 예정인 곳 넣기
        Queue<int[]> backup = new LinkedList<>(); //합쳐지는 나라 명단
        queue.add(new int[]{x, y});
        backup.add(new int[]{x, y});
        visited[x][y] = true;
        int sum = A[x][y]; //연합인 나라의 인구 수 전체 합
        int n = 1; //연합인 나라의 수
        while(!queue.isEmpty()){
            int [] now = queue.poll();
            for(int i = 0; i < 4; i++){
                x = now[0] + dx[i];
                y = now[1] + dy[i];
                if(x >= N || x < 0 || y >= N || y < 0) continue;
                if(visited[x][y]) continue;
                int cal = Math.abs(A[x][y] - A[now[0]][now[1]]); //두 나라간의 인구수 차
                if(cal >= L && cal <= R){ //범위 안에 들면
                    visited[x][y] = true;
                    backup.add(new int[]{x, y});
                    sum+=A[x][y];
                    n++;
                    queue.add(new int[]{x, y});
                }
            }
        }
        int avg = sum/n;
        for(int[] temp : backup){
            A[temp[0]][temp[1]] = avg; //연합인 나라들의 인구수 조정
        }
    }

    public static boolean check(){ //모든 인접 나라의 인구수가 범위 안에 있는지 여부 확인
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                for(int k = 0; k < 4; k++){
                    int x = i + dx[k];
                    int y = j + dy[k];
                    if(x >= N || x < 0 || y >= N || y < 0) continue;
                    int temp = Math.abs(A[x][y] - A[i][j]);
                    if(temp >= L && temp <= R) return true;
                }
            }
        }
        return false;
    }
}
