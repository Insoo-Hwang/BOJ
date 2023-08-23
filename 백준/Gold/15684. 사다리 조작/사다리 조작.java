import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int H;
    static boolean [][][] A;
    static boolean check;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        H = Integer.parseInt(st.nextToken());
        A = new boolean[N+2][N+2][H+1];
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int h = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            A[s][s+1][h] = true; //h높이에서 s와 s+1이 연결되어있음
            A[s+1][s][h] = true;
        }
        if(M == 0){ //사다리에 선이 없으면 그대로 내려옴
            System.out.println(0);
            return;
        }

        for(int i = 0; i < 4; i++){ //추가되는 선의 개수
            check = false;
            DFS(0, i);
            if(check){ //i개만 추가하고 통과된 경우
                min = i;
                break;
            }
        }

        if(min == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(min);
    }

    static void DFS(int c, int l){ //c는 지금까지 추가된 선의 수, l개까지만 추가 가능
        if(check){
            return;
        }
        if(c == l){ //모두 추가가 되었으면
            if(move()){ //그대로 내려오는지 확인
                check = true;
            }
            return;
        }

        for(int i = 1; i < H+1; i++){ //세로
            for(int j = 1; j < N; j++){ //가로
                if(!A[j][j+1][i] && !A[j-1][j][i] && !A[j+1][j+2][i]){ //새로운 선을 추가 가능한지 확인
                    A[j][j+1][i] = true; //추가
                    A[j+1][j][i] = true;
                    DFS(c+1, l); //다음 선 추가
                    A[j][j+1][i] = false; //제거 후 다른 경우의 수 탐색(백트래킹)
                    A[j+1][j][i] = false;
                }
            }
        }
    }

    static boolean move(){ //사다리 결과 확인
        for(int i = 1; i < N+1; i++){
            int s = i;
            int h = 1;
            while(h <= H){
                if(A[s][s+1][h]){
                    s++;
                }
                else if(A[s][s-1][h]){
                    s--;
                }
                h++;
            }
            if(s != i){
                return false;
            }
        }
        return true;
    }
}
