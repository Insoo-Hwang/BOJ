import java.util.*;

public class Main {
	static int R;
	static int C;
	static int K;
	static char [][] A;
	static int cnt = 0;
	static boolean [][] visited;
	static int [] dy = {-1, 1, 0, 0};
	static int [] dx = {0, 0, -1, 1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		R = sc.nextInt();
		C = sc.nextInt();
		K = sc.nextInt();
		A = new char[R][C];
		for(int i = 0; i < R; i++) {
			String s = sc.next();
			for(int j = 0; j < C; j++) {
				A[i][j] = s.charAt(j);
			}
		}
		visited = new boolean[R][C];
		visited[R-1][0] = true;
		DFS(R-1, 0, 1);
		System.out.println(cnt);
	}
	
	static void DFS(int n, int m, int c) {
		if(n == 0 && m == C-1) {
			if(c == K) cnt++;
			return;
		}
		for(int i = 0; i < 4; i++) {
			int y = n+dy[i];
			int x = m+dx[i];
			if(y > R-1 || y < 0 || x > C-1 || x < 0 || visited[y][x] || A[y][x] == 'T') continue;
			visited[y][x] = true;
			DFS(y, x, c+1);
			visited[y][x] = false;
		}
	}
}