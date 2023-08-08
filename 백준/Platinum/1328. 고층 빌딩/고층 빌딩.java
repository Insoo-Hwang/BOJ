import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());

        long [][][] D = new long[101][101][101]; //N개의 건물이 배치 되어있을때 왼쪽에서 L개, 오른쪽에서 R개의 건물이 보이는 경우의 수 
        D[1][1][1] = 1;
        for(int i = 2; i < N+1; i++){
            for(int j = 1; j < L+1; j++){
                for(int k = 1; k < R+1; k++){
                    D[i][j][k] = (D[i-1][j-1][k] + D[i-1][j][k-1] + D[i-1][j][k]*(i-2))%1000000007; //L-1개 보일 경우(왼쪽에 하나 더 배치) + R-1개 보일 경우(오른쪽에 하나 더 배치) + 보이는 개수는 같음(N-1개의 건물 사이에 배치 가능 하므로 N-2개의 경우의 수가 존재) 
                }
            }
        }
        System.out.println(D[N][L][R]);
    }
}
