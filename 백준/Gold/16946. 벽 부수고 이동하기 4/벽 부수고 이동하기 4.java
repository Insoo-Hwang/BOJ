import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int M;
    static int [][] A;
    static int [][] B;
    static int index = 2;
    static int [] indexCount;
    static int [] dy = {-1, 1, 0, 0};
    static int [] dx = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new int [N][M];
        B = new int [N][M];
        for(int i = 0; i < N; i++){
            String s = br.readLine();
            for(int j = 0; j < M; j++){
                A[i][j] = s.charAt(j) - '0';
            }
        }

        indexCount = new int[N*M/2+3];
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(A[i][j] == 0){
                    BFS(i, j);
                    index++;
                }
            }
        }
        for(int i = 0; i < N;i++){
            for(int j = 0; j < M; j++){
                if(A[i][j] == 1){
                    int cnt = 1;
                    HashSet<Integer> hash = new HashSet<>();
                    for(int k = 0; k < 4; k++){
                        int tempn = i + dy[k];
                        int tempm = j + dx[k];
                        if(tempn < 0 || tempn > N-1 || tempm < 0 || tempm > M-1) continue;
                        if(A[tempn][tempm] != 1){
                            hash.add(A[tempn][tempm]);
                        }
                    }
                    for(int n : hash){
                        cnt += indexCount[n];
                    }
                    sb.append(cnt%10);
                }
                else{
                    sb.append(0);
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void BFS(int n, int m){
        Queue<NM> queue = new LinkedList<>();
        queue.add(new NM(n, m));
        A[n][m] = index;
        indexCount[index]++;
        while(!queue.isEmpty()) {
            NM temp = queue.poll();
            for (int i = 0; i < 4; i++) {
                int tempn = temp.n + dy[i];
                int tempm = temp.m + dx[i];
                if (tempn < 0 || tempn > N - 1 || tempm < 0 || tempm > M - 1 || A[tempn][tempm] != 0) continue;
                A[tempn][tempm] = index;
                indexCount[index]++;
                queue.add(new NM(tempn, tempm));
            }
        }
    }

    static class NM{
        int n;
        int m;
        public NM(int n, int m){
            this.n = n;
            this.m = m;
        }
    }
}