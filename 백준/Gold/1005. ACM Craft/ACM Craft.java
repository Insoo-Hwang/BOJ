import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        while(T-->0){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            int [] A = new int[N+1]; //건물 건설 소요시간 저장
            int [] D = new int[N+1]; //각 건물 별 최소 건설 시간
            for(int i = 1; i < N+1; i++){
                D[i] = -1; //DP 배열에 저장되지 않은 건물은 -1로 표시
            }
            ArrayList<Integer> [] B = new ArrayList[N+1]; //각 건물별 선행 건물 목록
            boolean [] C = new boolean[N+1]; //건물 건설 여부
            for(int i = 1; i < N+1; i++){
                B[i] = new ArrayList<>();
            }
            st = new StringTokenizer(br.readLine());
            for(int i = 1; i < N+1; i++){
                A[i] = Integer.parseInt(st.nextToken());
            }
            for(int i = 0; i < K; i++){
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                B[b].add(a);
            }
            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());

            int count = 0; //while문 탈출을 위한 변수(DP배열에 저장된 항목의 개수) 
            while(count < N){
                for(int i = 1; i < N+1; i++){
                    if(D[i] != -1){ //이미 DP배열에 저장되었으면 패스
                        continue;
                    }
                    boolean check = true; //건설 가능여부
                    int max = 0; //선행 건물 시간 중 가장 오래 걸린 건물의 소요시간
                    for(int j : B[i]){ //j는 선행 건물 번호
                        if(!C[j]){ //선행되어 있지 않으면 패스
                            check = false;
                            break;
                        }
                        max = Math.max(max, D[j]); //선행 되어있다면 오래 걸린 선행 건물의 시간 저장 
                    }
                    if(check){
                        D[i] = max + A[i]; //가장 오래 걸린 선행 건물 + 현재 건물이 건설 소요 시간
                        C[i] = true;
                        count++;
                    }
                }
            }
            System.out.println(D[W]);
        }
    }
}
