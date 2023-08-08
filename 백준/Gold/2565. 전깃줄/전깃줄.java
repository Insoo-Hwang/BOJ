import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int [] A = new int[501];
        int start = 501;
        int end = 0;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            A[a] = b;
            start = Math.min(start, a); //전기줄 시작 부분
            end = Math.max(end, a); //전기줄 끝 부분
        }
        //LIS로 최장 증가 부분을 찾고 그 결과를 전체 전기줄 수에 빼면 제외할 전기줄 수가 나옴
        int [] D = new int[501]; //자신을 포함한 최장 증가 부분 수열
        int max = Integer.MIN_VALUE;
        for(int i = start; i < end+1; i++){
            D[i] = 1;
            if(A[i] == 0) continue; //전기줄이 없으면 통과
            for(int j = start; j < i; j++){
                if(A[j] == 0) continue; //전기줄이 없으면 통과
                if(A[i] > A[j]){
                    D[i] = Math.max(D[i], D[j] + 1); //증가하는 부분일 경우 max값 갱신
                }
            }
            max = Math.max(max, D[i]);
        }
        System.out.println(N-max);
    }
}
