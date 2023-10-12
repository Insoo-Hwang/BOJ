import java.util.*;
import java.io.*;

public class Solution {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		for(int test_case = 1; test_case <= T; test_case++)
		{
			int N = sc.nextInt();
			int M = sc.nextInt();
			int [][] A = new int[N][N];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					A[i][j] = sc.nextInt();
				}
			}
			int max = 0;
			int maxK = N;
			if(N%2 == 0) maxK++;
			for(int k = 1; k <= maxK; k++) {
				for(int i = 0; i < N; i++) {
					for(int j = 0; j < N; j++) {
						int temp = 0;
						for(int i1 = 0; i1 < N; i1++) {
							for(int j1 = 0; j1 < N; j1++) {
								if(A[i1][j1] == 0) continue;
								if(Math.abs(i-i1)+Math.abs(j-j1) <= k-1) {
									temp++;
								}
							}
						}
						if(temp*M >= k*k+(k-1)*(k-1)) {
							max = Math.max(max, temp);
						}
					}
				}
			}
			System.out.println("#" + test_case + " " + max);
		}
	}
}