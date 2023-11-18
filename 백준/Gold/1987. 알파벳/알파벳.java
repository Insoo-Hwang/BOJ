import java.util.*;

public class Main {
    static int [] x = {-1, 1, 0, 0};
    static int [] y = {0, 0, -1, 1};
    static int [][] A;
    static boolean [] visited;
    static int R;
    static int C;
    static int ans = 1;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        R = sc.nextInt();
        C = sc.nextInt();
        sc.nextLine();
        A = new int[R][C];
        for(int i = 0; i < R; i++){
            String temp = sc.nextLine();
            for(int j = 0; j < C; j++)
                A[i][j] = temp.charAt(j) - 'A';
        }
        visited = new boolean[26];
        DFS(0, 0, 0);
        System.out.println(ans);
    }
    public static void DFS(int a, int b, int c){
        if(visited[A[a][b]]) {
            ans = Math.max(ans, c);
            return;
        }
        visited[A[a][b]] = true;
        for(int i = 0; i < 4; i++){
            int tempx = a + x[i];
            int tempy = b + y[i];
            if(tempx > -1 && tempy > -1 && tempx < R && tempy < C)
                DFS(tempx, tempy, c+1);
        }
        visited[A[a][b]] = false;
    }
}
