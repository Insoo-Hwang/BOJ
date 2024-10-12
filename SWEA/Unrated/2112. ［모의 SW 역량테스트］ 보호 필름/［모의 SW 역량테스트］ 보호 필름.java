import java.util.*;

public class Solution {
    static int D;
    static int W;
    static int K;
    static int min;
    static int [][] A;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();

        int T = sc.nextInt();
        for(int t = 1; t < T+1; t++){
            D = sc.nextInt();
            W = sc.nextInt();
            K = sc.nextInt();
            A = new int[D][W];
            for(int i = 0; i < D; i++){
                for(int j = 0; j < W; j++){
                    A[i][j] = sc.nextInt();
                }
            }
            min = Integer.MAX_VALUE;
            DFS(0, 0);
            sb.append("#").append(t).append(" ").append(min).append("\n");
        }
        System.out.println(sb);
    }

    private static void DFS(int d, int m){
        if(d == D){
            if(test()) min = Math.min(min, m);
            return;
        }

        DFS(d+1, m);

        if(min > m) {
            int[] temp = new int[W];
            for (int i = 0; i < W; i++) {
                temp[i] = A[d][i];
                A[d][i] = 0;
            }
            DFS(d + 1, m + 1);
            for (int i = 0; i < W; i++) A[d][i] = temp[i];

            for (int i = 0; i < W; i++) {
                temp[i] = A[d][i];
                A[d][i] = 1;
            }
            DFS(d + 1, m + 1);
            for (int i = 0; i < W; i++) A[d][i] = temp[i];
        }
    }

    private static boolean test() {
        for(int i = 0; i < W; i++){
            int cnt = 0;
            int prev = -1;
            int max = 0;
            for(int j = 0; j < D; j++){
                if(A[j][i] == prev) cnt++;
                else{
                    prev = A[j][i];
                    cnt = 1;
                }
                max = Math.max(max, cnt);
                if(max >= K) break;
            }
            if(max < K) return false;
        }
        return true;
    }
}