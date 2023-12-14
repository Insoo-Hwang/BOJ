import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int M;
    static ArrayList<Integer> [] A;
    static int [] visited;
    static int num = 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        A = new ArrayList[N+1];
        visited = new int[N+1];
        for(int i = 1; i < N+1; i++){
            A[i] = new ArrayList<>();
        }
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            A[s].add(e);
            A[e].add(s);
        }
        for(int i = 1; i < N+1; i++){
            Collections.sort(A[i], Collections.reverseOrder());
        }
        DFS(R);
        for(int i = 1; i < N+1; i++){
            System.out.println(visited[i]);
        }
    }

    static void DFS(int n){
        visited[n] = num;
        num++;
        for(int i = 0; i < A[n].size(); i++){
            if(visited[A[n].get(i)] != 0) continue;
            DFS(A[n].get(i));
        }
    }
}