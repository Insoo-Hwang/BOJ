import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        Bag [] A = new Bag[N+1];
        int [][] D = new int[N+1][K+1]; //i번째 배낭을 탐색했을때 j무게 이하에서 얻을 수 있는 최대 가치
        for(int i = 1; i < N+1; i++){
            st = new StringTokenizer(br.readLine());
            A[i] = new Bag(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for(int i = 1; i < N+1; i++){
            for (int j = 1; j < K+1; j++){
                D[i][j] = D[i-1][j]; //이전 값 가져오기
                if(j - A[i].w >= 0){ //i번째 가방 무게가 j무게보다 가볍거나 같을 때(가방에 들어갈 수 있을 때)
                    D[i][j] = Math.max(D[i][j], D[i-1][j-A[i].w] + A[i].v); //최대값 업데이트
                }
            }
        }
        System.out.println(D[N][K]);
    }
    static class Bag{
        int w;
        int v;
        public Bag(int w, int v){
            this.w = w;
            this.v = v;
        }
    }
}
