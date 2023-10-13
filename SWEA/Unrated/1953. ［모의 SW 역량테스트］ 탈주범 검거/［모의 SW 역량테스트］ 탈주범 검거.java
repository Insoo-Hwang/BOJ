import java.util.*;
import java.io.*;

public class Solution {
	static int N;
	static int M;
	static int R;
	static int C;
	static int L;
	static int [][] A;
	static Queue<B> queue;
	static boolean [][] visited;
	static int [] dy = {-1, 1, 0, 0};
	static int [] dx = {0, 0, -1, 1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++)
		{
			N = sc.nextInt();
			M = sc.nextInt();
			R = sc.nextInt();
			C = sc.nextInt();
			L = sc.nextInt();
			A = new int[N][M];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < M; j++) {
					A[i][j] = sc.nextInt();
				}
			}
			System.out.println("#" + test_case + " " + BFS());
		}
	}
	
	static int BFS() {
		queue = new LinkedList<>();
		visited = new boolean[N][M];
		queue.add(new B(R, C, 1, A[R][C]));
		visited[R][C] = true;
		int cnt = 0;
		while(!queue.isEmpty()) {
			B now = queue.poll();
			if(now.time > L) break;
			cnt++;
			for(int i = 0; i < 4; i++) {
				int y = now.n+dy[i];
				int x = now.m+dx[i];
				if(y > N-1 || y < 0 || x > M-1 || x < 0 || visited[y][x] || A[y][x] == 0) continue;
				boolean up = false;
				boolean down = false;
				boolean left = false;
				boolean right = false;
				if(now.shape == 1) {
					up = true;
					down = true;
					left = true;
					right = true;
				}
				else if(now.shape == 2) {
					up = true;
					down = true;
				}
				else if(now.shape == 3) {
					left = true;
					right = true;
				}
				else if(now.shape == 4) {
					up = true;
					right = true;
				}
				else if(now.shape == 5) {
					right = true;
					down = true;
				}
				else if(now.shape == 6) {
					left = true;
					down = true;
				}
				else {
					up = true;
					left = true;
				}
				int shape = A[y][x];
				if(up && i == 0) {
					if(shape == 1 || shape == 2 || shape == 5 || shape == 6) {
						queue.add(new B(y, x, now.time+1, shape));
						visited[y][x] = true;
					}
				}
				if(down && i == 1) {
					if(shape == 1 || shape == 2 || shape == 4 || shape == 7) {
						queue.add(new B(y, x, now.time+1, shape));
						visited[y][x] = true;
					}
				}
				if(left && i == 2) {
					if(shape == 1 || shape == 3 || shape == 4 || shape == 5) {
						queue.add(new B(y, x, now.time+1, shape));
						visited[y][x] = true;
					}
				}
				if(right && i == 3) {
					if(shape == 1 || shape == 3 || shape == 6 || shape == 7) {
						queue.add(new B(y, x, now.time+1, shape));
						visited[y][x] = true;
					}
				}
			}
		}
		return cnt;
	}
	
	static class B{
		int n;
		int m;
		int time;
		int shape;
		
		public B(int n, int m, int time, int shape) {
			this.n = n;
			this.m = m;
			this.time = time;
			this.shape = shape;
		}
	}
}