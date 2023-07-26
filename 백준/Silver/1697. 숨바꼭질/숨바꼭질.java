import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static boolean[] visited = new boolean[100001];
    static int K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        if(N >= K){
            System.out.println(N-K);
            return;
        }
        BFS(N);
    }

    static void BFS(int n){
        Queue<cnt> queue = new LinkedList<>();
        queue.add(new cnt(n, 1));
        visited[n] = true;

        while(!queue.isEmpty()){
            cnt now = queue.poll();
            if(now.n > 0 && !visited[now.n-1]){
                visited[now.n-1] = true;
                queue.add(new cnt(now.n-1, now.cnt+1));
                if(now.n-1 == K){
                    queue.clear();
                    System.out.println(now.cnt);
                    break;
                }
            }
            if(now.n*2 <= 100000 && !visited[now.n*2]){
                visited[now.n*2] = true;
                queue.add(new cnt(now.n*2, now.cnt+1));
                if(now.n*2 == K) {
                    queue.clear();
                    System.out.println(now.cnt);
                    break;
                }
            }
            if(now.n+1 <= 100000 && !visited[now.n+1]){
                visited[now.n+1] = true;
                queue.add(new cnt(now.n+1, now.cnt+1));
                if(now.n+1 == K) {
                    queue.clear();
                    System.out.println(now.cnt);
                    break;
                }
            }
        }
    }
}
class cnt{
    int n;
    int cnt;
    public cnt(int n, int cnt){
        super();
        this.n = n;
        this.cnt = cnt;
    }
}
