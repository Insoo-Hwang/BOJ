import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int A[] = new int[N+2]; //while문에서 e가 끝까지 갈 수 있도록 마지막 칸을 비워둠

        st = new StringTokenizer(br.readLine());
        for(int i = 1; i < N+1; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        int min = N+1;
        int s = 1, e = 1, sum = 0; //s, e 두포인터를 둠
        while(e <= N+1){ //end포인터가 끝까지 갈 경우 종료
            if(sum >= S){ //합계가 S보다 클 경우는
                sum -= A[s++]; //start포인터를 앞으로 땡겨서 길이를 줄임
                min = Math.min(min, e-s+1);
            }
            else{
                sum += A[e++]; //아닐 경우는 end포인터를 뒤로 밀어 S보다 커지게 함
            }
        }
        if(min == N+1) System.out.println(0);
        else System.out.println(min);
    }
}
