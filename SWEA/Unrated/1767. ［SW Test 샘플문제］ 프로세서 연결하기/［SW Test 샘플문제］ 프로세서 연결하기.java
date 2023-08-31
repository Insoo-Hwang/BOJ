import java.util.Scanner;
import java.io.FileInputStream;

class Solution
{
    static int N;
    static int [][] A;
    static int [][] core;
    static boolean [][] visited;
    static int tempC;
    static int min;
    static int max;
	public static void main(String args[]) throws Exception
	{
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();
        for(int t = 1; t < T+1; t++){
            N = sc.nextInt();
            A = new int[N][N];
            core = new int [12][2];
            visited = new boolean[N][N];
            tempC = 0; //연결이 필요한 core의 수
            min = Integer.MAX_VALUE; //최소 전선의 수
            int connect = 0; //이미 연결된 core의 수
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    int temp = sc.nextInt();
                    if(temp == 1 && i != 0 && j != 0 && i != N-1 && j != N-1){
                        core[tempC][0] = i;
                        core[tempC][1] = j;
                        tempC++;
                        visited[i][j] = true; //가장자리가 아닌 부분에 있는 core는 연결이 필요
                    }
                    else if(temp == 1){
                        connect++;
                        visited[i][j] = true; //가장자리에 있는 core는 연결이 불필요
                    }
                    A[i][j] = temp;
                }
            }

            max = connect;
            DFS(0, 0, connect);
            System.out.print("#" + t + " ");
            if(min == Integer.MAX_VALUE) System.out.println(0);
            else System.out.println(min);
        }
	}
    
    static void DFS(int d, int c, int connect){ //d는 현재 탐색 중인 core의 번호, c는 연결된 전선의 길이, connect는 이미 연결된 core의 수
        if(d == tempC && max == connect){ //
            min = Math.min(min, c); //최소 전선 길이 업데이트
            return;
        }
        else if(d == tempC && max < connect){ //더 많은 core에 연결되면 해당 c로 초기화
            max = connect;
            min = c;
            return;
        }
        else if(d == tempC){
            return;
        }
        if(connect + tempC - d < max) return; //앞으로 연결될 수 있는 core수 + 현재 연결된 core 수가 최대 core수 보다 적으면 종료
        boolean [][] temp = new boolean[N][N];
        backUP(temp, visited); //백트래킹에 활용

        int up = UP(core[d][0], core[d][1]); //위쪽 탐색
        DFS(d+1, c+up, connect+Math.min(up, 1)); 
        backUP(visited, temp);
        int down = DOWN(core[d][0], core[d][1]); //아래쪽 탐색
        DFS(d+1, c+down, connect+Math.min(down, 1));
        backUP(visited, temp);
        int left = LEFT(core[d][0], core[d][1]); //왼쪽 탐색
        DFS(d+1, c+left, connect+Math.min(left, 1));
        backUP(visited, temp);
        int right = RIGHT(core[d][0], core[d][1]); /가
        }
        return cnt;
    }

    static int DOWN(int n, int m){
        int cnt = 0;
        for(int i = n+1; i < N; i++){
            if(visited[i][m]){
                for(int j = i-1; j > n; j--){
                    visited[j][m] = false;
                }
                return 0;
            }
            visited[i][m] = true;
            cnt++;
        }
        return cnt;
    }

    static int LEFT(int n, int m){
        int cnt = 0;
        for(int i = m-1; i > -1; i--){
            if(visited[n][i]){
                for(int j = i+1; j < m; j++){
                    visited[n][j] = false;
                }
                return 0;
            }
            visited[n][i] = true;
            cnt++;
        }
        return cnt;
    }

    static int RIGHT(int n, int m){
        int cnt = 0;
        for(int i = m+1; i < N; i++){
            if(visited[n][i]){
                for(int j = i-1; j > m; j--){
                    visited[n][j] = false;
                }
                return 0;
            }
            visited[n][i] = true;
            cnt++;
        }
        return cnt;
    }
}
