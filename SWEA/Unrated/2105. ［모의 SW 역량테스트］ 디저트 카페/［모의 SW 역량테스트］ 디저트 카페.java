import java.util.*;
import java.io.*;

public class Solution {
	static int N;
	static int [][] A;
	static int max;
	static boolean [][] visited;
	static boolean [] dd;
	static int [] dy = {1, 1, -1, -1};
	static int [] dx = {-1, 1, -1, 1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++)
		{
			N = sc.nextInt();
			A = new int[N][N];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					A[i][j] = sc.nextInt();
				}
			}
			max = 1;
			visited = new boolean[N][N];
			dd = new boolean[101];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					dd[A[i][j]] = true;
					int y = i+dy[0];
					int x = j+dx[0];
					if(y > N-1 || y < 0 || x > N-1 || x < 0 || dd[A[y][x]]) {
						dd[A[i][j]] = false;
						continue;
					}
					visited[y][x] = true;
					dd[A[y][x]] = true;
					DFS(y, x, 1, i, j, 0);
					visited[y][x] = false;
					dd[A[y][x]] = false;
					dd[A[i][j]] = false;
				}
			}
			if(max != 1) System.out.println("#" + test_case + " " + max);
			else System.out.println("#" + test_case + " " + -1);
		}
	}
	
	static void DFS(int n, int m, int cnt, int sn, int sm, int d) {
		if(n == sn && m == sm) {
			max = Math.max(max, cnt);
			return;
		}
		
		if(d == 0) {
			for(int i = 0; i < 2; i++) {
				int y = n+dy[i];
				int x = m+dx[i];
				if(y > N-1 || y < 0 || x > N-1 || x < 0 || visited[y][x] || dd[A[y][x]]) continue;
				visited[y][x] = true;
				dd[A[y][x]] = true;
				DFS(y, x, cnt+1, sn, sm, i);
				dd[A[y][x]] = false;
				visited[y][x] = false;
			}
		}
		else if(d == 1) {
			for(int i = 1; i < 4; i++) {
				if(i == 2) continue;
				int y = n+dy[i];
				int x = m+dx[i];
				if(y > N-1 || y < 0 || x > N-1 || x < 0 || visited[y][x] || dd[A[y][x]]) continue;
				visited[y][x] = true;
				dd[A[y][x]] = true;
				DFS(y, x, cnt+1, sn, sm, i);
				dd[A[y][x]] = false;
				visited[y][x] = false;
			}
		}
		else if(d == 2) {
			for(int i = 2; i < 3; i++) {
				int y = n+dy[i];
				int x = m+dx[i];
				if(y > N-1 || y < 0 || x > N-1 || x < 0 || visited[y][x] || (dd[A[y][x]] && !(y == sn && x == sm))) continue;
				visited[y][x] = true;
				dd[A[y][x]] = true;
				DFS(y, x, cnt+1, sn, sm, i);
				dd[A[y][x]] = false;
				visited[y][x] = false;
				dd[A[sn][sm]] = true;
			}
		}
		else {
			for(int i = 2; i < 4; i++) {
				int y = n+dy[i];
				int x = m+dx[i];
				if(y > N-1 || y < 0 || x > N-1 || x < 0 || visited[y][x] || (dd[A[y][x]] && !(y == sn && x == sm))) continue;
				visited[y][x] = true;
				dd[A[y][x]] = true;
				DFS(y, x, cnt+1, sn, sm, i);
				dd[A[y][x]] = false;
				visited[y][x] = false;
				dd[A[sn][sm]] = true;
			}
		}
	}
}