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
        int [] m = new int[N];
        int [] c = new int[N];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            m[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            c[i] = Integer.parseInt(st.nextToken());
        }

        int [] D = new int [10001]; //i라는 코스트를 사용했을때 메모리의 최대값
        Arrays.fill(D, -1);
        for(int i = 0; i < N; i++){
            int tempc = c[i];
            for(int j = 10000; j >= tempc; j--){
                if(D[j-tempc] != -1){ //이전 값을 방문한적이 있을때
                    D[j] = Math.max(D[j], D[j-tempc] + m[i]); //메모리의 최대값 업데이트
                }
            }
            D[tempc] = Math.max(D[tempc], m[i]); //i번째 앱을 종료했을때의 메모리값 업데이트
        }
        for(int i = 0; i < 10001; i++){
            if(D[i] >= M){
                System.out.println(i);
                return;
            }
        }
    }
}
