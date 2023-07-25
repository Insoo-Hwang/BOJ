import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static boolean [] visited;
    static int [] A;
    static Queue<Integer> queue = new LinkedList<>();
    static int cnt;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        while(T-->0){
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            visited = new boolean[N+1];
            A = new int[N+1];

            cnt = 0;
            st = new StringTokenizer(br.readLine());
            for(int i = 1; i < N+1; i++){
                int temp = Integer.parseInt(st.nextToken());
                A[i] = temp;
                if(i == temp) {
                    visited[i] = true;
                }
            }

            for(int i = 1; i < N+1; i++){
                if(!visited[i] && A[i] > i) {
                    queue.clear();
                    DFS(i);
                }
                else if(!visited[i] && A[i] < i){
                    visited[i] = true;
                    cnt++;
                }
            }

            sb.append(cnt);
            sb.append("\n");
        }
        System.out.println(sb);
    }

    static void DFS(int n){
        visited[n] = true;
        queue.add(n);
        if(!visited[A[n]]) {
            DFS(A[n]);
        }else{
            if(queue.isEmpty() || A[n] != queue.peek()){
                for(int i : queue){
                    if(i == A[n]){
                        break;
                    }
                    else{
                        cnt++;
                    }
                }
            }
        }
    }
}
