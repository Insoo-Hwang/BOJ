import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int A [][] = new int[N][M];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0; //청소 횟수
        while(true){
            int stop = 0; //while문 탈출 코드
            if(A[r][c] == 0){ //청소가 가능한 경우
                A[r][c] = 2;
                cnt++;
            }
            else{ //청소가 이미 된 경우
                for(int i = 3; i > -1; i--){ //청소가 있는 칸이 존재할 경우 무조건 반시계방향으로 90도 회전해야함
                    int tempr = r;
                    int tempc = c;
                    int tempd = (d+i)%4; //방향 설정
                    switch (tempd){
                        case 0 : //북
                            if(tempr > 0) tempr--; //맨 끝인지 확인
                            break;
                        case 1 : //동
                            if(tempc < M-1) tempc++;
                            break;
                        case 2 : //남
                            if(tempr < N-1) tempr++;
                            break;
                        default : //서
                            if(tempc > 0) tempc--;
                            break;
                    }
                    if(tempr == r && tempc == c) //맨 끝이라 이동하지 못한 경우 다른 방향 탐색
                        continue;
                    if(A[tempr][tempc] == 0){ //청소가 가능한 구역 발견
                        r = tempr;
                        c = tempc;
                        d = tempd;
                        break; //좌표 이동 후 for문에서 나옴
                    }
                    if(i == 0) { //4방향을 모두 탐색 완료
                        tempr = r;
                        tempc = c;
                        switch (d) { //원래 방향에서 후진
                            case 0:
                                if (tempr < N - 1) tempr++; //맨 끝인지 확인
                                break;
                            case 1:
                                if (tempc > 0) tempc--;
                                break;
                            case 2:
                                if (tempr > 0) tempr--;
                                break;
                            default:
                                if (tempc < M - 1) tempc++;
                                break;
                        }
                        if (tempr == r && tempc == c){ //맨 끝이라 이동하지 못한 경우 청소 종료
                            stop = 1;
                            break;
                        }
                        else if(A[tempr][tempc] == 1){ //벽이라 이동하지 못한 경우 청소 종료
                            stop = 1;
                            break;
                        }
                        else{ //이동가능한 경우(이미 청소한 구역)
                            r = tempr;
                            c = tempc;
                            break;
                        }
                    }
                }
            }
            if(stop == 1) //청소 종료시 while문을 빠져나감
                break;
        }
        System.out.println(cnt);

    }
}
