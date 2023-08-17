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

        int start = 0;
        int end = 1;
        int min = Integer.MAX_VALUE;
        while(end < N){
            int temp = Math.abs(A[start] - A[end]);
            if(temp == M){
                min = M;
                break;
            }
            else if(temp < M){
                end++;
            }
            else{
                start++;
                min = Math.min(min, temp);
            }
        }
        System.out.println(min);
    }
}