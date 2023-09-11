import jdk.dynalink.beans.StaticClass;

import java.beans.Introspector;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int tempM;
    static int F;
    static int startN;
    static int startM;
    static int [][] A; //지도
    static int [][] C; //승객의 위치 및 도착 정보
    static int [] dy = {-1, 1, 0, 0};
    static int [] dx = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        tempM = M;
        F = Integer.parseInt(st.nextToken());
        A = new int[N][N];
        C = new int[M+1][4];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                int temp = Integer.parseInt(st.nextToken());
                if(temp == 1){ //장애물이 있다면 -1입력으로 바꾸어줌
                    temp-=2;
                }
                A[i][j] = temp;
            }
        }
        st = new StringTokenizer(br.readLine());
        startN = Integer.parseInt(st.nextToken())-1;
        startM = Integer.parseInt(st.nextToken())-1;
        for(int i = 1; i < M+1; i++){
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken())-1;
            int m = Integer.parseInt(st.nextToken())-1;
            A[n][m] = i; //지도에 손님의 현재 위치 표시
            C[i][0] = n;
            C[i][1] = m;
            C[i][2] = Integer.parseInt(st.nextToken())-1; //도착 정보
            C[i][3] = Integer.parseInt(st.nextToken())-1;
        }

        for(int i = 0; i < M; i++){
            BFS(); //손님의 수 만큼 BFS실행
            if(tempM+i+1 != M){ //남은 손님의 수가 줄어들지 않는다는 것은 모든 손님을 태울 수 없다는 뜻
                F = -1;
            }
        }
        System.out.println(F);
    }

    static void BFS(){
        if(F == -1) return; //이미 손님을 태울 수 없을 경우 패스
        boolean [][] visited = new boolean[N][N];
        visited[startN][startM] = true;
        Queue<Location> queue = new LinkedList<>();
        queue.add(new Location(startN, startM, 0, false));
        int min = Integer.MAX_VALUE; //승객 중 우선순위가 높은 승객의 번호
        int minN = Integer.MAX_VALUE; //승객의 위치
        int minM = Integer.MAX_VALUE;
        int r = Integer.MAX_VALUE; //승객까지의 거리
        while(!queue.isEmpty()){
            Location now = queue.remove();
            if(A[now.n][now.m] > 0 && r == Integer.MAX_VALUE && !now.r){ //가장 가까운 승객 발견시
                r = now.c;
                min = A[now.n][now.m];
                minN = now.n;
                minM = now.m;
            }
            else if(A[now.n][now.m] > 0 && r == now.c && !now.r){ //거리가 같은 승객 발견시 우선순위를 따져 업데이트
                if(minN == now.n){
                    if(minM > now.m){
                        min = A[now.n][now.m];
                        minM = now.m;
                    }
                }
                else if(minN > now.n){
                    min = A[now.n][now.m];
                    minN = now.n;
                    minM = now.m;
                }
            }
            if((r < now.c || (queue.isEmpty() && min != Integer.MAX_VALUE)) &&!now.r){ //거리가 더 멀거나 더 이상 탐색할 곳이 없을 때
                if(F < now.c){ //이동이 불가능할
                    F = -1;
                    return;
                }
                F-=r;
                A[minN][minM] = 0; //승객을 태움
                queue.clear();
                visited = new boolean[N][N];
                visited[minN][minM] = true;
                queue.add(new Location(minN, minM, 0, true)); //돌아가기 시작
                continue;
            }
            if(now.r && now.n == C[min][2] && now.m == C[min][3]){ //목적지에 도착하면
                if(F < now.c){ //기름이 부족하면 이동 불가
                    F = -1;
                    return;
                }
                F+=now.c; //기름이 채워짐
                startN = C[min][2]; //현재 위치가 다음 장소를 찾기 위한 시작 위치
                startM = C[min][3];
                tempM--; //남은 승객 수 감소
                return;
            }
            for(int i = 0; i < 4; i++){
                int y = now.n + dy[i];
                int x = now.m + dx[i];
                if(y > N-1 || y < 0 || x > N-1 || x < 0 || visited[y][x] || A[y][x] == -1) continue; //이동이 불가능한 경우
                visited[y][x] = true;
                queue.add(new Location(y, x, now.c+1, now.r));
            }
        }
    }
    static class Location{
        int n; //n좌표
        int m; //m좌표
        int c; //이동거리
        boolean r; //true일시 손님을 태운 경우, false일시 손님에게 가는 경우
        public Location(int n, int m, int c, boolean r){
            this.n = n;
            this.m = m;
            this.c = c;
            this.r = r;
        }
    }
}
