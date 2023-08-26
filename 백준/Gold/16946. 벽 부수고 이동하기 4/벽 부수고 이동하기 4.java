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

        indexCount = new int[N*M/2+3]; //분리된 빈 공간 영역마다 몇 개의 빈 공간이 있는지 확인하기 위한 배열로 N*M/2+3은 가능성이 있는 배열의 최대 크기
        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(A[i][j] == 0){ //0이라는 것은 아직 방문하지 않은 빈 공간이라는 뜻
                    BFS(i, j); //index번 영역을 방문하며 영역 분류
                    index++;
                }
            }
        } //완료가 되면 모든 빈 공간이 각각의 영역으로 분리
        for(int i = 0; i < N;i++){
            for(int j = 0; j < M; j++){
                if(A[i][j] == 1){ //벽을 만나면
                    int cnt = 1; //갈 수 있는 빈 공간 수
                    HashSet<Integer> hash = new HashSet<>(); //4방향 중 같은 영역의 빈 공간을 만나는 면이 있을 수 있기 때문에 중복 방지
                    for(int k = 0; k < 4; k++){
                        int tempn = i + dy[k];
                        int tempm = j + dx[k];
                        if(tempn < 0 || tempn > N-1 || tempm < 0 || tempm > M-1) continue;
                        if(A[tempn][tempm] != 1){ //1이 아니라는 것은 영역 번호가 지정된 빈 공간
                            hash.add(A[tempn][tempm]); //벽의 한 면과 만나는 영역의 번호를 hashSet에 추가
                        }
                    }
                    for(int n : hash){
                        cnt += indexCount[n]; //hashSet에 들어 있는 영역의 개수 만큼 추가
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

    static void BFS(int n, int m){ //(n, m)과 연결되어 있는 빈 공간을 하나의 영역으로 분류하기 위한 함수
        Queue<NM> queue = new LinkedList<>();
        queue.add(new NM(n, m));
        A[n][m] = index; //index번 영역임을 표시
        indexCount[index]++; //해당 영역의 빈공간 개수 증가
        while(!queue.isEmpty()) {
            NM temp = queue.poll();
            for (int i = 0; i < 4; i++) {
                int tempn = temp.n + dy[i];
                int tempm = temp.m + dx[i];
                if (tempn < 0 || tempn > N - 1 || tempm < 0 || tempm > M - 1 || A[tempn][tempm] != 0) continue; //A[tempn][tempm]이 0이면 방문하지 않은 빈 공간이라는 뜻
                A[tempn][tempm] = index; //index번 영역임을 표시
                indexCount[index]++; //해당 영역의 빈공간 개수 증가
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
