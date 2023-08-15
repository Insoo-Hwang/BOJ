import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int [][] A;
    static int [][] B;
    static boolean[] visited;
    static int house;
    static int chicken;
    static int delete;
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        A = new int[N*2][2];
        B = new int[13][2];
        house = 0;
        chicken = 0;
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                int temp = Integer.parseInt(st.nextToken());
                if(temp == 1){
                    A[house][0] = i;
                    A[house][1] = j;
                    house++;
                }
                else if(temp == 2){
                    B[chicken][0] = i;
                    B[chicken][1] = j;
                    chicken++;
                }
            }
        }
        visited = new boolean[chicken];
        delete = chicken - M;

        DFS(0, 0);
        System.out.println(min);
    }

    static void DFS(int p, int n){
        if(n == chicken && p != delete) return;
        if(p == delete){
            chickenRoute();
            return;
        }
        visited[n] = true;
        DFS(p+1, n+1);
        visited[n] = false;
        DFS(p, n+1);
    }

    static void chickenRoute(){
        int temp = 0;
        for(int i = 0; i < house; i++){
            int tempmin = Integer.MAX_VALUE;
            for(int j = 0; j < chicken; j++){
                if(!visited[j]){
                    tempmin = Math.min(tempmin, Math.abs(A[i][0] - B[j][0]) + Math.abs(A[i][1] - B[j][1]));
                }
            }
            temp += tempmin;
        }
        min = Math.min(min, temp);
    }
}