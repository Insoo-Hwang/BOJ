import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int [][] A;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        A = new int[N][N];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int min = Integer.MAX_VALUE;
        for(int i = 1; i < (1<<(N-1)); i++){
            List<Integer> start = new ArrayList<>();
            List<Integer> link = new ArrayList<>();
            for(int j = 0; j < N; j++){
                if((i & (1<<j)) > 0) start.add(j);
                else link.add(j);
            }
            min = Math.min(min, Math.abs(score(start)-score(link)));
        }
        System.out.println(min);
    }

    static int score(List<Integer> list){
        int sum = 0;
        for(int i = 0; i < list.size()-1; i++){
            for(int j = i+1; j < list.size(); j++){
                sum+=A[list.get(i)][list.get(j)];
                sum+=A[list.get(j)][list.get(i)];
            }
        }
        return sum;
    }
}