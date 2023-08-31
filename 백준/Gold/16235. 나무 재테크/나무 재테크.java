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
        int [][] A = new int[N][N];
        int [][] map = new int[N][N];
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
        PriorityQueue<Tree> list = new PriorityQueue<>();
        for(int i = 0; i < M; i++){
            st = new StringTokenizer(br.readLine());
            list.add(new Tree(Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())-1, Integer.parseInt(st.nextToken())));
        }

        while(K-->0){
            PriorityQueue<Tree> temp = new PriorityQueue<>();
            PriorityQueue<Tree> summer = new PriorityQueue<>();
            while(!list.isEmpty()){
                Tree now = list.remove();
                if(map[now.n][now.m] >= now.a){
                    map[now.n][now.m]-=now.a;
                    now.a++;
                    temp.add(now);
                }
                else{
                    summer.add(now);
                }
            }
            if(temp.isEmpty()){
                break;
            }
            for(Tree now : summer){
                map[now.n][now.m]+=now.a/2;
            }
            while(!temp.isEmpty()){
                Tree now = temp.remove();
                if(now.a%5 == 0){
                    for(int i = 0; i < 8; i++){
                        int y = now.n+dy[i];
                        int x = now.m+dx[i];
                        if(y > N-1 || y < 0 || x > N-1 || x < 0) continue;
                        list.add(new Tree(y, x, 1));
                    }
                }
                list.add(now);
            }
            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    map[i][j]+=A[i][j];
                }
            }
        }
        System.out.println(list.size());
    }

    static class Tree implements Comparable<Tree>{
        int n;
        int m;
        int a;
        public Tree(int n, int m, int a){
            this.n = n;
            this.m = m;
            this.a = a;
        }

        @Override
        public int compareTo(Tree o) {
            return this.a - o.a;
        }
    }
}