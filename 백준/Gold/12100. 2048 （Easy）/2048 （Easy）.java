import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int [][] A;
    static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        A = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int [][] B = new int[N][N]; //A배열 초기화를 위한 복사본 생성(백트래킹)
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                B[i][j] = A[i][j];
            }
        }
        move(1, 0);
        A = B; //move(1, 0)으로 변경된 A배열을 초기화 해줌
        move(2, 0);
        A = B;
        move(3, 0);
        A = B;
        move(4, 0);
        System.out.println(max);
    }
    static void move(int n, int d){ //n은 방향 d는 move함수가 불려진 횟수
        if(d == 5){ //move함수가 5번 불려지면 종료 후 가장 큰 값 확인
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    max = Math.max(max, A[i][j]);
                }
            }
            return;
        }

        if(n == 1){ //위로 이동하는 경우
            int [][] B = new int[N][N]; //이동할때 한번에 이동해야하지만 배열 특성상 순차적으로 이동하기 때문에 이를 맞춰주기 위한 복사본 생성
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    B[i][j] = A[i][j];
                }
            }
            for(int i = 0; i < N; i++){ //위에 있는 값부터 아래로 내려가며 합쳐짐(항상 자신의 아래값과 같다면 합쳐지고 0이 나오지 않을 때까지 위로 이동)
                for(int j = 0; j < N; j++){
                    if(B[i][j] == 0) continue; //해당 배열에 값이 없다면 패스
                    if(i != N-1){ //가장 아래에 있는 값은 더 아래의 값과 합쳐질 수 없음
                        for(int k = i+1; k < N; k++){ //아래 값이 0일때 그보다 더 아래 값과 합쳐질 수 있음
                            if(B[i][j] != B[k][j] && B[k][j] 우
            int [][] B = new int[N][N];
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    B[i][j] = A[i][j];
                }
            }
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    if(B[i][j] == 0) continue;
                    if(j != N-1){
                        for(int k = j+1; k < N; k++){
                            if(B[i][j] != B[i][k] && B[i][k] != 0){
                                break;
                            }
                            else if(B[i][j] == B[i][k]){
                                B[i][j]*=2;
                                B[i][k] = 0;
                                break;
                            }
                        }
                    }
                    int temp = j-1;
                    while(true){
                        if(temp < 0) break;
                        if(B[i][temp] != 0) break;
                        B[i][temp] = B[i][temp+1];
                        B[i][temp+1] = 0;
                        temp--;
                    }
                }
            }
            A = B;
            move(1, d+1);
            A = B;
            move(2, d+1);
            A = B;
            move(3, d+1);
            A = B;
            move(4, d+1);
        }
    }
}
