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
        start = new int[2]; //상어의 위치
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                int temp = Integer.parseInt(st.nextToken());
                A[i][j] = temp;
                if(temp == 9){
                    A[i][j] = 0; //원래 상어가 있던 곳은 빈공간으로 교체
                    start[0] = i;
                    start[1] = j;
                }
            }
        }

        while(true) {
            BFS(); //먹을 수 있는 물고기 탐색
            if(!eat()){ //먹을 수 있는 물고기가 없다면 종료
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
        int route = 0; //처음 먹을 수 있는 물고기 보다 거리가 멀면 선택될 수 없기 때문에 처음 먹는 물고기 거리를 저장
        while(!queue.isEmpty()){
            Shark now = queue.poll();
            if(A[now.n][now.m] < size && A[now.n][now.m] > 0){ //먹을 수 있는 물고기 발견
                if(route == 0){ //저장되어 있는 거리가 없으면
                    route = now.c; //새롭게 저장
                }
                else if(route < now.c){ //이미 저장된 거리보다 먼 거리에 있는 물고기는 선택 대상이 될 수 없음
                    break;
                }
                fish.add(new Fish(now.n, now.m, now.c)); //먹을 수 있는 물고기에 포함
                continue;
            }
            for(int i = 0; i < 4; i++){
                int tempn = now.n + dy[i];
                int tempm = now.m + dx[i];
                if(tempn < 0 || tempn > N-1 || tempm < 0 || tempm > N-1 || visited[tempn][tempm] || A[tempn][tempm] > size) continue;
                queue.add(new Shark(tempn, tempm, now.c+1)); //이동
                visited[tempn][tempm] = true;
            }
        }
    }

    static boolean eat(){
        if(fish.size() > 0){
            Collections.sort(fish); //물고기를 우선순위에 따라 정렬
            Fish meal = fish.get(0); //가장 우선순위가 높은 물고기를 잡아먹음
            start[0] = meal.n;
            start[1] = meal.m;
            time += meal.c; //물고기의 거리만큼 시간에 합해줌
            fish.clear(); //물고기 배열 초기화(상어의 위치가 바뀌면 다시 우선순위를 정해야함)
            A[meal.n][meal.m] = 0;
            s++; //현재 크기에서 먹은 물고기 수
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
        int c; //걸린 시간
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
        public int compareTo(Fish o) { //우선순위(시간(짧은 순으로), n좌표(더 위에 있는 물고기), m좌표(더 왼쪽에 있는 물고기)
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
