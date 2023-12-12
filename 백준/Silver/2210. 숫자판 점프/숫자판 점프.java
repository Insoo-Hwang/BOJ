import java.util.*;
import java.io.*;

public class Main {
    static String [][] A;
    static int [] dy = {-1, 1, 0, 0};
    static int [] dx = {0, 0, -1, 1};
    static HashSet<String> set = new HashSet<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringTokenizer st = new StringTokenizer(br.readLine());

        A = new String[5][5];
        for(int i = 0; i < 5; i++){
            String [] s = br.readLine().split(" ");
            for(int j = 0; j < 5; j++){
                A[i][j] = s[j];
            }
        }

        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                DFS(i, j, 0, ""+A[i][j]);
            }
        }
        System.out.println(set.size());
    }

    static void DFS(int n, int m, int c, String s){
        if(c == 5){
            set.add(s);
            return;
        }

        for(int i = 0; i < 4; i++){
            int y = n+dy[i];
            int x = m+dx[i];
            if(y < 0 || y > 4 || x < 0 || x > 4) continue;
            DFS(y, x, c+1, s+A[y][x]);
        }
    }
}