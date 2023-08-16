import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int [] A = new int[N+k-1]; //회전 초밥이기 때문에 마지막 부분 계산을 위해 앞부분을 뒤로 붙임
        int [] B = new int[d+1]; //초밥 종류 파악을 위한 배열
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(br.readLine());
        }
        for(int i = 0; i < k-1; i++){
            A[N+i] = A[i]; //앞부분을 뒤로 붙이기
        }

        int tempmax = 1; //한 슬라이드에서 먹을 수 있는 초밥 가짓수, 쿠폰 사용을 하기 때문에 1로 시작
        B[c] = 1; //쿠폰으로 먹는 초밥
        for(int i = 0; i < k; i++){
            if(B[A[i]] == 0){ //이미 먹지 않은 초밥이면
                tempmax++; //가짓수 증가
            }
            B[A[i]]++; //A[i]라는 초밥을 먹었다고 표시
        }
        int max = tempmax; //가짓수의 최댓값
        int end = k; //마지막을 표시하는 투포인터
        for(int i = 1; i < N; i++){ //각 반복마다 슬라이드를 뒤로 한칸씩 밀어냄
            B[A[i-1]]--; //전 슬라이드의 가장 앞부분 제외
            if(B[A[i-1]] == 0){ //제외한 부분이 0이라면(중간에 이 종류의 초밥이 포함되어 있지 않다면)
                tempmax--; //가짓수에서 제외
            }
            if(B[A[end]] == 0){ //새롭게 추가되는 끝부분의 초밥이 0이라면(중간에 이 종류의 초밥이 포함되어 있지 않다면)
                tempmax++; //가짓수에 포함
            }
            B[A[end]]++;
            end++;
            max = Math.max(max, tempmax); //초밥 가짓수의 최댓값 업데이트 
        }
        System.out.println(max);
    }
}
