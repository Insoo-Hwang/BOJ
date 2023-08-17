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
        int [] A = new int[N];
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(A);

        int start = 0; //시작 포인터
        int end = 1; //끝 포인터
        int min = Integer.MAX_VALUE; //두 수의 차이
        while(end < N){ //끝 포인터가 배열 끝까지 가게 되면
            int temp = Math.abs(A[start] - A[end]); //두 수의 차이
            if(temp == M){ //M보다 같으면 더 이상 반복문을 돌 필요가 없음
                min = M;
                break;
            }
            else if(temp < M){ //M보다 작으면 끝 포인터를 이동
                end++;
            }
            else{ //M보다 크면 시작 포인터를 이동
                start++;
                min = Math.min(min, temp); //두 수의 차이 업데이트
            }
        }
        System.out.println(min);
    }
}
