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
            tempC = 0; //연결이 필요한 
            min = Integer.MAX_VALUE;
            int connect = 0;
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    int temp = sc.nextInt();
                    if(temp == 1 && i != 0 && j != 0 && i != N-1 && j != N-1){
                        core[tempC][0] = i;
                        core[tempC][1] = j;
                        tempC++;
                        visited[i][j] = true;
                    }
                    else if(temp == 1){
                        connect++;
                        visited[i][j] = true;
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
    
    static void DFS(int d, int c, int connect){
        if(d == tempC && max == connect){
            min = Math.min(min, c);
            return;
        }
        else if(d == tempC && max < connect){
            max = connect;
            min = c;
            return;
        }
        else if(d == tempC){
            return;
        }
        if(connect + tempC - d < max) return;
        boolean [][] temp = new boolean[N][N];
        backUP(temp, visited);

        int up = UP(core[d][0], core[d][1]);
        DFS(d+1, c+up, connect+Math.min(up, 1));
        backUP(visited, temp);
        int down = DOWN(core[d][0], core[d][1]);
        DFS(d+1, c+down, connect+Math.min(down, 1));
        backUP(visited, temp);
        int left = LEFT(core[d][0], core[d][1]);
        DFS(d+1, c+left, connect+Math.min(left, 1));
        backUP(visited, temp);
        int right = RIGHT(core[d][0], core[d][1]);
        DFS(d+1, c+right, connect+Math.min(right, 1));
        backUP(visited, temp);
    }

    static void backUP(boolean [][] A, boolean[][] B){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                A[i][j] = B[i][j];
            }
        }
    }

    static int UP(int n, int m){
        int cnt = 0;
        for(int i = n-1; i > -1; i--){
            if(visited[i][m]){
                for(int j = i+1; j < n; j++){
                    visited[j][m] = false;
                }
                return 0;
            }
            visited[i][m] = true;
            cnt++;
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
