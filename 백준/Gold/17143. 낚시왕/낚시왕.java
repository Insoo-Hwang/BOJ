import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int R;
    static int C;
    static int M;
    static Shark [] A; //상어 정보 배열
    static ArrayList<Integer> [][] B; //현재 좌표에서 상어가 있는지 확인
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        A = new Shark[M];
        B = new ArrayList[R][C];
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                B[i][j] = new ArrayList<>();
            }
        }
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            A[i] = new Shark(r, c, s, d, z);
            B[r][c].add(i); //(r, c)에 i번 상어가 존재
        }

        int get = 0;
        for(int i = 0; i < C; i++){ //낚시꾼 이동
            for(int j = 0; j < R; j++){ //현재 열에서 가장 가까운 상어 잡기
                if(!B[j][i].isEmpty()){
                    get+=A[B[j][i].get(0)].z;
                    A[B[j][i].get(0)].d = -1; //이미 잡은 상어의 속력을 -1로 업데이트
                    B[j][i].remove(0); //좌표당 상어는 1마리
                    break;
                }
            }

            for(int j = 0; j < M; j++){
                if(A[j].d == -1){ //없는 상어는 패스
                    continue;
                }
                move(j); //이동
            }

            for(int j = 0; j < R; j++){
                for(int k = 0; k < C; k++){
                    if(B[j][k].size() < 2) continue; //잡아먹힐 상어가 없으면 패스
                    int size = A[B[j][k].get(0)].z;
                    int index = 0;
                    for(int h = 1; h < B[j][k].size(); h++){
                        if(size < A[B[j][k].get(h)].z){ //현재 사이즈보다 더 큰 사이즈의 상어가 있다면
                            A[B[j][k].get(index)].d = -1; //현재 상어가 잡아먹힘
                            index = h; //큰 상어 위치 업데이트
                            size = A[B[j][k].get(h)].z; //큰 상어의 크기 업데이트
                        }
                        else{
                            A[B[j][k].get(h)].d = -1; //비교 상어가 잡아먹힘
                        }
                    }
                    int zz = 0;
                    while(B[j][k].size() != 1){
                        if(A[B[j][k].get(zz)].d == -1){
                            B[j][k].remove(zz); //이미 잡아먹힌 상어를 없애줌
                        }
                        else{
                            zz++;
                        }
                    }
                }
            }
        }
        System.out.println(get);
    }

    static void move(int s){ //s는 상어의 번호
        B[A[s].r][A[s].c].remove(0); //현재 상어를 B배열에서 제거거
        if(A[s].d == 1){ //위로 이동
            if(A[s].r - A[s].s > -1){ //방향을 바꾸지 않는 경우
                A[s].r -= A[s].s;
            }
            else{ //방향을 바꿔야 하는 경우
                int t = A[s].s - A[s].r - 1;
                int d = t/(R-1);
                int w = t%(R-1);
                if(d%2 == 0){
                    A[s].d = 2;
                    A[s].r = w+1;
                }
                else{
                    A[s].r = Math.abs(w-(R-2));
                }
            }
        }
        else if(A[s].d == 2){ //아래로 이동동
            if(A[s].r + A[s].s < R){
                A[s].r += A[s].s;
            }
            else{
                int t = A[s].s + A[s].r - 1;
                int d = t/(R-1);
                int w = t%(R-1);
                if(d%2 == 1){
                    A[s].d = 1;
                    A[s].r = Math.abs(w-(R-2));
                }
                else{
                    A[s].r = w+1;
                }
            }
        }
        else if(A[s].d == 3){ //오른쪽으로 이동동
            if(A[s].c + A[s].s < C){
                A[s].c += A[s].s;
            }
            else{
                int t = A[s].s + A[s].c - 1;
                int d = t/(C-1);
                int w = t%(C-1);
                if(d%2 == 1){
                    A[s].d = 4;
                    A[s].c = Math.abs(w-(C-2));
                }
                else{
                    A[s].c = w+1;
                }
            }
        }
        else{ //왼쪽으로 이동
            if(A[s].c - A[s].s > -1){
                A[s].c -= A[s].s;
            }
            else{
                int t = A[s].s - A[s].c - 1;
                int d = t/(C-1);
                int w = t%(C-1);
                if(d%2 == 0){
                    A[s].d = 3;
                    A[s].c = w+1;
                }
                else{
                    A[s].c = Math.abs(w-(C-2));
                }
            }
        }
        B[A[s].r][A[s].c].add(s); //이동한 위치에 상어 추가
    }

    static class Shark{
        int r; //r좌표
        int c; //c좌표
        int s; //속력
        int d; //방향
        int z; //사이즈
        public Shark(int r, int c, int s, int d, int z){
            this.r = r;
            this.c = c;
            this.s = s;
            this.d = d;
            this.z = z;
        }
    }
}
