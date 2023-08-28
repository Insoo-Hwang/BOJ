import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int [][] A;
    static int [] start;
    static int [] dy = {-1, 0, 1, 0};
    static int [] dx = {0, -1, 0, 1};
    static int size = 2;
    static int s = 0;
    static int time = 0;
    static LinkedList<Fish> fish = new LinkedList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        A = new int[N][N];
        start = new int[2];
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                int temp = Integer.parseInt(st.nextToken());
                A[i][j] = temp;
                if(temp == 9){
                    A[i][j] = 0;
                    start[0] = i;
                    start[1] = j;
                }
            }
        }

        while(true) {
            BFS();
            if(!eat()){
                System.out.println(time);
                return;
            }
        }
    }

    static void BFS(){
        Queue<Shark> queue = new LinkedList<>();
        queue.add(new Shark(start[0], start[1], 0));
        boolean [][] visited = new boolean[N][N];
        visited[start[0]][start[1]] = true;
        int route = 0;
        while(!queue.isEmpty()){
            Shark now = queue.poll();
            if(A[now.n][now.m] < size && A[now.n][now.m] > 0){
                if(route == 0){
                    route = now.c;
                }
                else if(route < now.c){
                    break;
                }
                fish.add(new Fish(now.n, now.m, now.c));
                continue;
            }
            for(int i = 0; i < 4; i++){
                int tempn = now.n + dy[i];
                int tempm = now.m + dx[i];
                if(tempn < 0 || tempn > N-1 || tempm < 0 || tempm > N-1 || visited[tempn][tempm] || A[tempn][tempm] > size) continue;
                queue.add(new Shark(tempn, tempm, now.c+1));
                visited[tempn][tempm] = true;
            }
        }
    }

    static boolean eat(){
        if(fish.size() > 0){
            Collections.sort(fish);
            Fish meal = fish.get(0);
            start[0] = meal.n;
            start[1] = meal.m;
            time += meal.c;
            fish.clear();
            A[meal.n][meal.m] = 0;
            s++;
            if(size == s){
                size++;
                s = 0;
            }
        }
        else{
            return false;
        }
        return true;
    }

    static class Shark{
        int n;
        int m;
        int c;
        public Shark(int n, int m, int c){
            this.n = n;
            this.m = m;
            this.c = c;
        }
    }

    static class Fish implements Comparable<Fish>{
        int n;
        int m;
        int c;

        public Fish(int n, int m, int c){
            this.n = n;
            this.m = m;
            this.c = c;
        }

        @Override
        public int compareTo(Fish o) {
            if(this.c == o.c){
                if(this.n == o.n){
                    return this.m - o.m;
                }
                return this.n - o.n;
            }
            return this.c - o.c;
        }
    }
}