import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int K;
    static char [][] A;
    static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        if(K < 5){
            System.out.println(0);
            return;
        }
        A = new char[N][16];
        for(int i = 0; i < N; i++) Arrays.fill(A[i], '.');
        for(int i = 0; i < N; i++){
            char [] s = br.readLine().toCharArray();
            for(int j = 0; j < s.length; j++){
                A[i][j] = s[j];
            }
        }
        int learn = 0;
        learn |= (1<<('a'-'a'));
        learn |= (1<<('n'-'a'));
        learn |= (1<<('t'-'a'));
        learn |= (1<<('c'-'a'));
        learn |= (1<<('i'-'a'));
        DFS(5, learn, 0);
        System.out.println(max);
    }

    static void DFS(int d, int learn, int start){
        if(d == K){
            max = Math.max(max, check(learn));
            return;
        }
        for(int i = start; i < 26; i++){
            if((learn & (1 << i)) > 0) continue;
            DFS(d+1, learn | (1 << i), i+1);
        }
    }

    static int check(int learn){
        int cnt = 0;
        for(int i = 0; i < N; i++){
            for(int j = 0; j < 16; j++){
                if(A[i][j] == '.'){
                    cnt++;
                    break;
                }
                if((learn & (1<<A[i][j]-'a')) == 0) break;
            }
        }
        return cnt;
    }
}