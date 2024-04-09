import java.io.*;
import java.util.*;

public class Main {
    static int [] dy = {-1, 1, 0, 0};
    static int [] dx = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int Q = Integer.parseInt(st.nextToken());
        int S = (int)Math.pow(2, N);
        int [][] A = new int[S][S];
        for(int i = 0; i < S; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < S; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < Q; i++){
            int L = Integer.parseInt(st.nextToken());
            int [][] B = new int[S][S];
            int s = (int)Math.pow(2, L);
            for(int j = 0; j < S/s; j++){
                for(int k = 0; k < S/s; k++){
                    int [][] temp1 = new int[s][s];
                    for(int i1 = 0; i1 < s; i1++){
                        for(int j1 = 0; j1 < s; j1++){
                            temp1[i1][j1] = A[s*j+i1][s*k+j1];
                        }
                    }
                    int [][] temp2 = new int[s][s];
                    for(int i1 = 0; i1 < s; i1++){
                        for(int j1 = 0; j1 < s; j1++){
                            temp2[i1][j1] = temp1[s-1-j1][i1];
                        }
                    }
                    for(int i1 = 0; i1 < s; i1++){
                        for(int j1 = 0; j1 < s; j1++){
                            B[s*j+i1][s*k+j1] = temp2[i1][j1];
                        }
                    }
                }
            }
            for(int j = 0; j < S; j++){
                for(int k = 0; k < S; k++){
                    A[j][k] = B[j][k];
                }
            }
            for(int j = 0; j < S; j++){
                for(int k = 0; k < S; k++){
                    if(A[j][k] == 0) continue;
                    int cnt = 0;
                    for(int d = 0; d < 4; d++){
                        int y = j+dy[d];
                        int x = k+dx[d];
                        if(y > S-1 || y < 0 || x > S-1 || x < 0) continue;
                        if(B[y][x] > 0) cnt++;
                    }
                    if(cnt < 3) A[j][k]--;
                }
            }
        }
        int sum = 0;
        for(int i = 0; i < S; i++){
            for(int j = 0; j < S; j++){
                sum+=A[i][j];
            }
        }
        int max = 0;
        boolean [][] visited = new boolean[S][S];
        for(int i = 0; i < S; i++){
            for(int j = 0; j < S; j++){
                if(A[i][j] < 1 || visited[i][j]) continue;
                Queue<int []> queue = new LinkedList<>();
                queue.add(new int[] {i, j});
                visited[i][j] = true;
                int cnt = 0;
                while(!queue.isEmpty()){
                    int [] now = queue.poll();
                    cnt++;
                    for(int k = 0; k < 4; k++){
                        int y = now[0]+dy[k];
                        int x = now[1]+dx[k];
                        if(y > S-1 || y < 0 || x > S-1 || x < 0 || visited[y][x] || A[y][x] == 0) continue;
                        visited[y][x] = true;
                        queue.add(new int[] {y, x});
                    }
                }
                max = Math.max(max, cnt);
            }
        }
        System.out.println(sum);
        System.out.println(max);
    }
}