import java.util.*;

public class Main {
    static ArrayList<Integer>[] A;
    static boolean visited[];
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();
        A = new ArrayList[N+1];
        visited = new boolean[N+1];
        for(int i = 1; i < N+1; i++)
            A[i] = new ArrayList<>();

        for(int i = 0; i < M; i++){
            int s = sc.nextInt();
            int e = sc.nextInt();
            A[s].add(e);
            A[e].add(s);
        }
        DFS(1);
        int count = 0;
        for(int i = 1; i < N+1; i++)
            if(visited[i]) count++;

        System.out.print(count-1);
    }

    static void DFS(int k){
        if(visited[k])
            return;
        visited[k] = true;
        for(int i : A[k]){
            if(visited[i] == false){
                DFS(i);
            }
        }
    }
}