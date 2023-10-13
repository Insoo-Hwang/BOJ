import java.util.*;
import java.io.*;

public class Solution {
	static int N;
	static int [][] A;
	static Queue<M> queue;
	static int [] dy = {1, -1, 0, 0};
	static int [] dx = {0, 0, -1, 1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++)
		{
			N = sc.nextInt();
			int K = sc.nextInt();
			A = new int [N][N];
			int max = 0;
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					A[i][j] = sc.nextInt();
					max = Math.max(max, A[i][j]);
				}
			}
			int b = 0;
			int [][] bong = new int[5][2];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(A[i][j] == max) {
						bong[b][0] = i;
						bong[b][1] = j;
						b++;
					}
				}
			}
			int route = 1;
			for(int i = 0; i < b; i++) {
				route = Math.max(route, BFS(bong[i][0], bong[i][1]));
			}
			for(int k = 1; k < K+1; k++) {
				for(int i = 0; i < N; i++) {
					for(int j = 0; j < N; j++) {
						A[i][j]-=k;
						for(int h = 0; h < b; h++) {
							if(A[bong[h][0]][bong[h][1]] != max) continue;
							route = Math.max(route, BFS(bong[h][0], bong[h][1]));
						}
						A[i][j]+=k;
					}
				}
			}
			System.out.println("#" + test_case + " " + route);
		}
	}
	
	static int BFS(int n, int m) {
		queue = new LinkedList<>();
		queue.add(new M(n, m, A[n][m], 1));
		int max = 1;
		while(!queue.isEmpty()) {
			M now = queue.poll();
			max = Math.max(max, now.d);
			for(int i = 0; i < 4; i++) {
				int y = now.n+dy[i];
				int x = now.m+dx[i];
				if(y > N-1 || y < 0 || x > N-1 || x < 0 || now.h <= A[y][x]) continue;
				queue.add(new M(y, x, A[y][x], now.d+1));
			}
		}
		return max;
	}
	
	static class M{
		int n;
		int m;
		int h;
		int d;
		public M(int n, int m, int h, int d) {
			this.n = n;
			this.m = m;
			this.h = h;
			this.d = d;
		}
	}
}