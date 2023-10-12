import java.util.*;
import java.io.*;

public class Solution {
	static int D;
	static int W;
	static int K;
	static int [][] A;
	static int min;
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++)
		{
			D = sc.nextInt();
			W = sc.nextInt();
			K = sc.nextInt();
			A = new int [D][W];
			for(int i = 0; i < D; i++) {
				for(int j = 0; j < W; j++) {
					A[i][j] = sc.nextInt();
				}
			}
			min = Integer.MAX_VALUE;
			DFS(0, 0);
			System.out.println("#" + test_case + " " + min);
		}
	}
	
	static void DFS(int cnt, int n) {
		if(cnt >= min) return;
		if(n == D) {
			if(TEST()) {
				min = Math.min(min, cnt);
			}
			return;
		}
		int [] B = new int[W];
		for(int j = 0; j < W; j++) {
			B[j] = A[n][j];
		}
		for(int j = 0; j < W; j++) {
			A[n][j] = 0;
		}
		DFS(cnt+1, n+1);
		for(int j = 0; j < W; j++) {
			A[n][j] = 1;
		}
		DFS(cnt+1, n+1);
		for(int j = 0; j < W; j++) {
			A[n][j] = B[j];
		}
		DFS(cnt, n+1);
	}
	
	static boolean TEST() {
		for(int i = 0; i < W; i++) {
			int max = 0;
			int cnt = 1;
			for(int j = 1; j < D; j++) {
				if(A[j][i] == A[j-1][i]) cnt++;
				else cnt = 1;
				max = Math.max(cnt, max);
				if(max >= K) break;
			}
			if(max < K) return false;
		}
		return true;
	}
}