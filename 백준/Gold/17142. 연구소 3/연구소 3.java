import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int [][] A;
    static int [][] virus;
    static int tempV;
    static int [] dy = {-1, 1, 0, 0};
    static int [] dx = {0, 0, -1, 1};
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int[N][N];
        virus = new int[N*N][2]; //전체 바이러스의 위치를 저장하는 배열
        tempV = 0; //전체 바이러스의 개수
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                int temp = Integer.parseInt(st.nextToken());
                if(temp == 2){
                    virus[tempV][0] = i;
                    virus[tempV][1] = j;
                    tempV++;
                }
                A[i][j] = temp;
            }
        }

        DFS(0, 0);
        if(min == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(min);
    }
    static void DFS(int d, int s){ //d는 선택된 바이러스의 수, s는 바이러스 배열에서 탐색 중인 위치
        if(d == M){ //활성 바이러스를 M개 선택하면
            BFS(); //바이러스 퍼뜨리기 시작
            return;
        }
        if(s == tempV) return; //탐색 범위를 넘어가면 종료

        A[virus[s][0]][virus[s][1]] = 3; //현재 순서의 바이러스를 선택하는 케이스
        DFS(d+1, s+1);
        A[virus[s][0]][virus[s][1]] = 2; //현재 순서의 바이러스를 선택하지 않는 케이스
        DFS(d, s+1);
    }

    static void BFS(){
        boolean [][] visited = new boolean[N][N];
        Queue<Virus> queue = new LinkedList<>();
        int check = N*N; //전체 칸을 전염시켰는지 확인하기 위한 변수
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                if(A[i][j] == 3){ //활성 바이러스가 있는 경우
                    visited[i][j] = true;
                    queue.add(new Virus(i, j, 0));
                    check--;
                }
                else if(A[i][j] == 2){ //비활성 바이러스가 있는 경우
                    check--; //이미 전염되어 있기 때문에
                }
                else if(A[i][j] == 1){ //벽이 있는 경우
                    visited[i][j] = true; //벽에는 접근이 불가능
                    check--; //벽은 전염 불가
                }
            }
        }

        int max = 0; //시간의 최대값(전부 전염시키는데에 걸리는 시간)
        while(!queue.isEmpty()){
            Virus now = queue.poll();
            for(int i = 0; i < 4; i++){
                int y = now.n + dy[i];
                int x = now.m + dx[i];
                if(y < 0 || y > N-1 || x < 0 || x > N-1 || visited[y][x]) continue;
                visited[y][x] = true;
                queue.add(new Virus(y, x, now.t+1));
                if(A[y][x] != 2){ //비활성 바이러스는 전염시간이 0초이기 때문에 시간 업데이트 x
                    max = Math.max(max, now.t+1);
                    check--;
                }
            }
        }
        if(check == 0){ //모든 칸이 전염되면
            min = Math.min(max, min); //최소 시간 업데이트
        }
    }

    static class Virus{
        int n; //n좌표
        int m; //m좌표
        int t; //걸린 시간
        public Virus(int n, int m, int t){
            this.n = n;
            this.m = m;
            this.t = t;
        }
    }
}
