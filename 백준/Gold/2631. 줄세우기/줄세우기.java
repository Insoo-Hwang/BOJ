import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int [] A = new int[N];
        int [] D = new int[N]; //LIS활용
        for(int i = 0; i < N; i++){
            A[i] = Integer.parseInt(br.readLine());
        }
        //LIS알고리즘을 활용하여 최장 연속 배열을 찾고 전체 아이들 수에서 빼면 이동해야하는 아이의 수가 나옴(바르게 서있는 아이들을 제외한 나머지 아이들의 위치를 옮겨주면 됨)

        D[0] = 1;
        int max = 0;
        for(int i = 1; i < N; i++){
            D[i] = 1;
            for(int j = 0; j < i; j++){
                if(A[i] > A[j]){
                    D[i] = Math.max(D[i], D[j]+1);
                }
            }
            max = Math.max(max, D[i]);
        }
        System.out.println(N-max);
    }
}
