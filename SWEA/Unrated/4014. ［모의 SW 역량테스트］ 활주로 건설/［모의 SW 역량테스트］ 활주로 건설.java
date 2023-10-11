import java.util.*;

class Solution
{
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			int N = sc.nextInt();
			int X = sc.nextInt();
			int [][] A = new int [N][N];
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					A[i][j] = sc.nextInt();
				}
			}
			int cnt = 0;
			boolean [][] B = new boolean [N][N];
			for(int i = 0; i < N; i++) {
				for(int j = 1; j < N; j++) {
					int prev = A[j-1][i];
					int now = A[j][i];
					if(Math.abs(prev-now) > 1) break;
					boolean check = false;
					if(prev-now == -1 && j-X > -1) {
						for(int k = 1; k < X+1; k++) {
							if(prev != A[j-k][i] || B[j-k][i]) {
								check = true;
								break;
							}
							B[j-k][i] = true;
						}
						if(check) break;
					}
					else if(prev-now == 1 && j+X-1 < N) {
						for(int k = 0; k < X; k++) {
							if(now != A[j+k][i] || B[j+k][i]) {
								check = true;
								break;
							}
							B[j+k][i] = true;
						}
						if(check) break;
					}
					else if(Math.abs(prev-now) == 1) break;
					if(j == N-1) cnt++;
				}
			}
            B = new boolean [N][N];
			for(int i = 0; i < N; i++) {
				for(int j = 1; j < N; j++) {
					int prev = A[i][j-1];
					int now = A[i][j];
					if(Math.abs(prev-now) > 1) break;
					boolean check = false;
					if(prev-now == -1 && j-X > -1) {
						for(int k = 1; k < X+1; k++) {
							if(prev != A[i][j-k] || B[i][j-k]) {
								check = true;
								break;
							}
							B[i][j-k] = true;
						}
						if(check) break;
					}
					else if(prev-now == 1 && j+X-1 < N) {
						for(int k = 1; k < X; k++) {
							if(now != A[i][j+k] || B[i][j+k]) {
								check = true;
								break;
							}
							B[i][j+k] = true;
						}
						if(check) break;
					}
					else if(Math.abs(prev-now) == 1) break;
					if(j == N-1) cnt++;
				}
			}
			System.out.println("#" + test_case + " " + cnt);
		}
	}
}