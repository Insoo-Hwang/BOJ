import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int [] dy = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int [] dx = {-1, 0, 1, -1, 1, -1, 0, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int [][] A = new int[N][N]; //매년 추가되는 양분의 양
        int [][] map = new int[N][N]; //현재 땅의 양분의 양
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                map[i][j] = 5;
            }
        }
        PriorityQueue<Tree> list = new PriorityQueue<>(); //현재 있는 나무들
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            list.add(new Tree(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())));
        }

        while(K-->0){
            PriorityQueue<Tree> temp = new PriorityQueue<>(); //봄이 지나고 생존한 나무들을 담아두는 큐
            PriorityQueue<Tree> summer = new PriorityQueue<>(); //봄에 죽고 여름에 양분이 되는 나무들을 담아두는 큐
            while(!list.isEmpty()){ //봄
                Tree now = list.remove();
                if(map[now.n][now.m] >= now.a){ //나무가 먹을 충분한 양분이 있는 경우
                    map[now.n][now.m]-=now.a;
                    now.a++;
                    temp.add(now);
                }
                else{ //양분이 충분히 없는 경우
                    summer.add(now);
                }
            }
            if(temp.isEmpty()){ //나무가 더 이상 없으면 정답은 항상 0
                break;
            }
            for(Tree now : summer){ //여름
                map[now.n][now.m]+=now.a/2;
            }
            while(!temp.isEmpty()){ //가을
                Tree now = temp.remove();
                if(now.a%5 == 0){ //현재 나이가 5의 배수인 나무들은 번식을 함
                    for(int i = 0; i < 8; i++){
                        int y = now.n+dy[i];
                        int x = now.m+dx[i];
                        if(y > N-1 || y < 0 || x > N-1 || x < 0) continue;
                        list.add(new Tree(y, x, 1));
                    }
                }
                list.add(now);
            }
            for(int i = 0; i < N; i++){ //겨울
                for(int j = 0; j < N; j++){
                    map[i][j]+=A[i][j]; //매년마다 양분이 추가로 채워짐
                }
            }
        }
        System.out.println(list.size());
    }

    static class Tree implements Comparable<Tree>{
        int n; //n좌표
        int m; //m좌표
        int a; //나무의 나이
        public Tree(int n, int m, int a){
            this.n = n;
            this.m = m;
            this.a = a;
        }

        @Override
        public int compareTo(Tree o) {
            return this.a - o.a; //나이가 어린 순으로 양분을 먹기 때문에 나이가 어린 순으로 정렬
        }
    }
}
