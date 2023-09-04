import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int [] A; //유죄지수
    static boolean [] alive; //살아있는지 여부
    static int [][] R; //반응
    static int E; //마피아
    static int max = 0; //버틸 수 있는 밤의 수의 최대값
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        A = new int[N];
        alive = new boolean[N];
        Arrays.fill(alive, true);
        R = new int[N][N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                R[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        E = Integer.parseInt(br.readLine());

        DFS(0, N);
        System.out.println(max);
    }

    static void DFS(int c, int p){ //c는 보낸 밤의 수, p는 남은 사람의 수
        if(!alive[E] || p == 1){ //마피아가 죽거나 남은 사람이 1명이면 종료
            max = Math.max(max, c);
            return;
        }

        if(p%2 == 0){ //밤
            for(int i = 0; i < N; i++){
                if(!alive[i] || i == E) continue; //죽어있거나 마피아가 죽는 경우는 패스
                change(i, 1); //유죄지수 변경
                alive[i] = false; //선택된 시민 사망
                DFS(c+1, p-1);
                alive[i] = true;
                change(i, -1);
            }
        }
        else{ //낮
            int max = 0; //유죄지수의 최대값
            int idx = N-1; //인덱스값
            for(int i = 0; i < N; i++) {
                if(alive[i] && max < A[i]) { //살아있으면서 유죄지수가 높으면
                    max = A[i]; //유죄지수 업데이트
                    idx = i; //인덱스 업데이트
                }else if(alive[i] && max == A[i]) { //유죄지수가 같으면 인덱스가 작은 사람 사망
                    max = A[i];
                    idx = Math.min(i, idx);
                }
            }
            alive[idx] = false;
            DFS(c, p-1);
            alive[idx] = true;
        }
    }

    static void change(int d, int t){
        for(int i = 0; i < N; i++){
            if(!alive[i]) continue;
            A[i]+=R[d][i]*t;
        }
    }
}
