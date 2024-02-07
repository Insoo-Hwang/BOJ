import java.io.*;
import java.util.*;

public class Main {
    static int D;
    static int P;
    static int max = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        D = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());
        DFS(0, 1, 9);
        System.out.println(max);
    }

    static void DFS(int d, int num, int start){
        if(digits(num) > D) return;
        if(d == P){
            max = Math.max(max, num);
            return;
        }
        for(int i = start; i > 1; i--) DFS(d+1, num*i, i);
    }

    static int digits(int num){
        int cnt = 0;
        while(num != 0){
            num/=10;
            cnt++;
        }
        return cnt;
    }
}