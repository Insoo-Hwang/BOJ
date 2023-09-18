import java.io.*;
import java.util.*;

public class Main {
    static int R;
    static int C;
    static int [][] A;
    static boolean [][] visited;
    static int [] dy = {-1, 0, 1}; //오른쪽 위, 오른쪽, 오른쪽 아래로만 이동 가능
    static int [] dx = {1, 1, 1};
    static int cnt = 0;
    static boolean check;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        A = new int[R][C];
        for(int i = 0; i < R; i++){
            String s = br.readLine();
            for(int j = 0; j < C; j++){
                if(s.charAt(j) == '.') {
                    A[i][j] = 0;
                }
                else{
                    A[i][j] = 1; //건물이 있는 곳
                }
            }
        }
        visited = new boolean[R][C];
        for(int i = 0; i < R; i++){ //1열의 모든 행에서 출발
            check = false;
            DFS(i, 0);
        }
        System.out.println(cnt);
    }

    static void DFS(int n, int m){
        if(m == C-1){
            cnt++;
            check = true; //배관 건설에 성공시
            return;
        }

        for(int i = 0; i < 3; i++){
            int y = n + dy[i];
            int x = m + dx[i];
            if(y < 0 || y > R-1 || x < 0 || x > C-1 || visited[y][x] || A[y][x] == 1) continue;
            visited[y][x] = true; //배관 건설에 실패하더라도 한 번 방문한 곳은 다시 방문해도 건설 불가능
            DFS(y, x);
            if(check){ //건설 성공시 더 이상 시도 x
                break;
            }
        }
    }
}
