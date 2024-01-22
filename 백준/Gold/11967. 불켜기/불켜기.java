import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static List<int []> [][] A;
    static Queue<int []> queue = new LinkedList<>();
    static int [][] visited;
    static int [] dy = {-1, 1, 0, 0};
    static int [] dx = {0, 0, -1 ,1};
    static int cnt = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        A = new List[N][N];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                A[i][j] = new ArrayList<>();
            }
        }
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            A[x][y].add(new int [] {a, b});
        }
        BFS();
        System.out.println(cnt);
    }

    static void BFS(){
        visited = new int[N][N];
        visited[0][0] = 2;
        queue.add(new int [] {0, 0});
        while(!queue.isEmpty()){
            int [] now = queue.poll();
            for(int i = 0; i < A[now[0]][now[1]].size(); i++){
                int [] temp = A[now[0]][now[1]].get(i);
                if(visited[temp[0]][temp[1]] == 0){
                    cnt++;
                    boolean check = false;
                    for(int j = 0; j < 4; j++){
                        int y = temp[0]+dy[j];
                        int x = temp[1]+dx[j];
                        if(y > N-1 || y < 0 || x > N-1 || x < 0 || visited[y][x] != 2) continue;
                        check = true;
                    }
                    if(check){
                        visited[temp[0]][temp[1]] = 2;
                        queue.add(new int[] {temp[0], temp[1]});
                        DFS(temp[0], temp[1]);
                    }
                    else visited[temp[0]][temp[1]] = 1;
                }
            }
        }
    }

    static void DFS(int n, int m){
        for(int i = 0; i < 4; i++){
            int y = n+dy[i];
            int x = m+dx[i];
            if(y > N-1 || y < 0 || x > N-1 || x < 0 || visited[y][x] != 1) continue;
            visited[y][x] = 2;
            queue.add(new int [] {y, x});
            DFS(y, x);
        }
    }
}