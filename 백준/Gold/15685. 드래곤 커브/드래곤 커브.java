import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        boolean [][] A = new boolean[101][101];
        int [] opp = {1, 2, 3, 0};
        int [] dy = {0, -1, 0, 1};
        int [] dx = {1, 0, -1, 0};
        int N = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g = Integer.parseInt(st.nextToken());
            List<Integer> list = new ArrayList<>();
            A[y][x] = true;
            y+=dy[d];
            x+=dx[d];
            A[y][x] = true;
            list.add(d);
            if(g == 0) continue;
            d = opp[d];
            y+=dy[d];
            x+=dx[d];
            A[y][x] = true;
            list.add(d);
            for(int j = 1; j < g; j++) {
                for (int k = (int) Math.pow(2, j); k > 0; k--) {
                    d = opp[list.get(k-1)];
                    y += dy[d];
                    x += dx[d];
                    A[y][x] = true;
                    list.add(d);
                }
            }
        }
        int cnt = 0;
        for(int i = 0; i < 100; i++){
            for(int j = 0; j < 100; j++){
                if(A[i][j] && A[i+1][j] && A[i][j+1] && A[i+1][j+1]) cnt++;
            }
        }
        System.out.println(cnt);
    }
}