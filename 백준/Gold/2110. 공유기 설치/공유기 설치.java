import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int [] A = new int[N];
        for(int i = 0; i < N; i++) A[i] = Integer.parseInt(br.readLine());
        Arrays.sort(A);
        int left = 1;
        int right = A[N-1];
        while(left <= right){
            int mid = (left+right)/2;
            int cnt = 1;
            int start = 0;
            for(int i = 1; i < N; i++){
                if(A[i]-A[start] >= mid){
                    cnt++;
                    start = i;
                }
            }
            if(cnt < C) right = mid-1;
            else left = mid+1;
        }
        System.out.println(left-1);
    }
}