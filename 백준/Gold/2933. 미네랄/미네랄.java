import java.io.*;
import java.util.*;

public class Main {
    static int R;
    static int C;
    static char [][] A;
    static List<Cluster> list = new ArrayList<>();
    static int [] dy = {-1, 0, 0, 1};
    static int [] dx = {0, 1, -1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        A = new char[R][C];
        for(int i = 0; i < R; i++){
            char [] s = br.readLine().toCharArray();
            for(int j = 0; j < C; j++){
                A[i][j] = s[j];
            }
        }
        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++){
            int h = Integer.parseInt(st.nextToken());
            list.clear();
            stick(R-h, i%2 == 0);
        }
        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                sb.append(A[i][j]);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static void stick(int h, boolean place){
        if(place){
            int index = 0;
            while(index < C){
                if(A[h][index] == 'x'){
                    A[h][index] = '.';
                    for(int i = 0; i < 4; i++){
                        list.clear();
                        int y = h+dy[i];
                        int x = index+dx[i];
                        if(y > R-1 || y < 0 || x > C-1 || x < 0) continue;
                        findCluster(y, x);
                        if(!list.isEmpty()) drop();
                    }
                    break;
                }
                index++;
            }
        }
        else{
            int index = C-1;
            while(index > -1){
                if(A[h][index] == 'x'){
                    A[h][index] = '.';
                    for(int i = 0; i < 4; i++){
                        list.clear();
                        int y = h+dy[i];
                        int x = index+dx[i];
                        if(y > R-1 || y < 0 || x > C-1 || x < 0) continue;
                        findCluster(y, x);
                        if(!list.isEmpty()) drop();
                    }
                    break;
                }
                index--;
            }
        }
    }

    static void findCluster(int n, int m){
        if(A[n][m] == '.') return;
        boolean [][] visited = new boolean[R][C];
        visited[n][m] = true;
        Queue<Cluster> queue = new LinkedList<>();
        queue.add(new Cluster(n, m));
        A[n][m] = '.';
        list.add(new Cluster(n, m));
        while(!queue.isEmpty()){
            Cluster now = queue.poll();
            for(int i = 0; i < 4; i++){
                int y = now.n+dy[i];
                int x = now.m+dx[i];
                if(y > R-1 || y < 0 || x > C-1 || x < 0 || visited[y][x] || A[y][x] == '.') continue;
                A[y][x] = '.';
                visited[y][x] = true;
                queue.add(new Cluster(y, x));
                list.add(new Cluster(y, x));
            }
        }
    }

    static void drop(){
        Collections.sort(list);
        int dh = 1;
        while(true){
            boolean check = true;
            for(int i = 0; i < list.size(); i++){
                Cluster now = list.get(i);
                if(now.n+dh > R-1 || A[now.n+dh][now.m] == 'x'){
                    check = false;
                    break;
                }
            }
            if(!check){
                dh--;
                break;
            }
            dh++;
        }
        for(int i = 0; i < list.size(); i++){
            Cluster now = list.get(i);
            A[now.n+dh][now.m] = 'x';
        }
    }

    static class Cluster implements Comparable<Cluster>{
        int n;
        int m;

        public Cluster(int n, int m){
            this.n = n;
            this.m = m;
        }

        @Override
        public int compareTo(Cluster o){
            return o.n-this.n;
        }
    }
}