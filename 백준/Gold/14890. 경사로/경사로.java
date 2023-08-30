import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int [][] A = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0; //이동 가능한 길의 수 
        boolean [][] B = new boolean[N][N]; //경사로가 겹치는지 확인용 배열(가로/세로 따로 확인)
        for(int i = 0; i < N; i++){ //세로로 된 길 확인
            for(int j = 1; j < N; j++){
                int prev = A[j-1][i]; //이전 칸
                int now = A[j][i]; //현재 칸
                if(prev == now){ //이전 칸과 현재 칸의 높이가 같다면 패스
                    if(j == N-1) cnt++; //끝 칸이라면 이동 가능한 길
                    continue;
                }
                else if(Math.abs(prev-now) > 1) break; //높이가 1이상 차이나면 불가능한 길
                boolean check = false; //경사로를 지었을 때 가능한지 여부 확인
                if(prev-now == -1){ //이전 칸의 높이가 더 낮은 경우
                    for (int k = 0; k < L; k++) { //경사로의 길이 만큼 탐색
                        if (j-1-k < 0) break; //지도를 벗어나면 불가능
                        if (A[j-1-k][i] != prev) break; //경사로가 깔리는 길의 높이가 다르면 불가능
                        if (B[j-1-k][i]) break; //이미 경사로가 설치된 곳이면 불가능
                        if (k == L-1) { //경사로 설치가 가능하면
                            for (int h = 0; h < L; h++) {
                                B[j-1-h][i] = true; //경사로 설치
                            }
                            check = true;
                        }
                    }
                }
                else{ //현재 칸의 높이가 더 낮은 경우
                    for (int k = 0; k < L; k++) {
                        if (j+k > N-1) break;
                        if (A[j+k][i] != now) break;
                        if (B[j+k][i]) break;
                        if (k == L-1) {
                            for (int h = 0; h < L; h++) {
                                B[j+h][i] = true;
                            }
                            check = true;
                        }
                    }
                }
                if(!check) break;
                if(j == N-1) cnt++; //끝에 도달하면 길 추가
            }
        }
        B = new boolean[N][N];
        for(int i = 0; i < N; i++){ //가로로 된 길 확인
            for(int j = 1; j < N; j++){
                int prev = A[i][j-1];
                int now = A[i][j];
                if(prev == now){
                    if(j == N-1) cnt++;
                    continue;
                }
                else if(Math.abs(prev-now) > 1) break;
                boolean check = false;
                if(prev-now == -1){
                    for (int k = 0; k < L; k++) {
                        if (j-1-k < 0) break;
                        if (A[i][j-1-k] != prev) break;
                        if (B[i][j-1-k]) break;
                        if (k == L-1) {
                            for (int h = 0; h < L; h++) {
                                B[i][j-1-h] = true;
                            }
                            check = true;
                        }
                    }
                }
                else{
                    for (int k = 0; k < L; k++) {
                        if (j+k > N-1) break;
                        if (A[i][j+k] != now) break;
                        if (B[i][j+k]) break;
                        if (k == L-1) {
                            for (int h = 0; h < L; h++) {
                                B[i][j+h] = true;
                            }
                            check = true;
                        }
                    }
                }
                if(!check) break;
                if(j == N-1) cnt++;
            }
        }
        System.out.println(cnt);
    }
}
