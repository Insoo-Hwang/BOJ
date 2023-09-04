import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int [][] A = new int[10][10];
    static int [] paper = new int[6];
    static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 0; i < 10; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 10; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        Arrays.fill(paper, 5);

        int [] temp = next();
        DFS(temp[0], temp[1], 0);
        if(min == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(min);
    }

    static void DFS(int n, int m, int c){
        if(check(n, m, 10, 1)){
            min = Math.min(min, c);
            return;
        }

        for(int i = 1; i < 6; i++){
            if(check(n, m, i, 0) && paper[i] > 0){
                paper[i]--;
                change(n, m, i, 0);
                int [] temp = next();
                DFS(temp[0], temp[1], c+1);
                paper[i]++;
                change(n, m, i, 1);
            }
        }
    }

    static boolean check(int n, int m, int s, int v){
        if(n+s > 10 || m+s > 10) return false;
        for(int i = n; i < n+s; i++){
            for(int j = m; j < m+s; j++){
                if(A[i][j] == v) return false;
            }
        }
        return true;
    }

    static void change(int n, int m, int s, int v){
        for(int i = n; i < n+s; i++){
            for(int j = m; j < m+s; j++){
                A[i][j] = v;
            }
        }
    }

    static int[] next(){
        int [] temp = new int[2];
        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(A[i][j] == 1){
                    temp[0] = i;
                    temp[1] = j;
                    return temp;
                }
            }
        }
        return temp;
    }
}