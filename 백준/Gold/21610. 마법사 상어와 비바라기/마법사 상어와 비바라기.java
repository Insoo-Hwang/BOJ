import java.util.*;

public class Main {
	static int [] dy = {0, 0, -1, -1, -1, 0, 1, 1, 1};
	static int [] dx = {0, -1, -1, 0, 1, 1, 1, 0, -1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int [][] A = new int[N][N];
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				A[i][j] = sc.nextInt();
			}
		}
		Queue<int []> queue = new LinkedList<>();
		queue.add(new int [] {N-1, 0});
		queue.add(new int [] {N-1, 1});
		queue.add(new int [] {N-2, 0});
		queue.add(new int [] {N-2, 1});
		boolean [][] visited = new boolean[N][N];
		for(int i = 0; i < M; i++) {
			int d = sc.nextInt();
			int s = sc.nextInt();
			for(int j = 0; j < queue.size(); j++) {
				int [] c = queue.poll();
				int y = (c[0]+s*dy[d]+50*N)%N;
				int x = (c[1]+s*dx[d]+50*N)%N;
				A[y][x]++;
				queue.add(new int [] {y, x});
			}
			for(int [] c : queue) {
				int cnt = 0;
				visited[c[0]][c[1]] = true;
				for(int j = 2; j < 9; j+=2) {
					int y = c[0]+dy[j];
					int x = c[1]+dx[j];
					if(y > N-1 || y < 0 || x > N-1 || x < 0) continue;
					if(A[y][x] > 0) cnt++;
				}
				A[c[0]][c[1]]+=cnt;
			}
			queue.clear();
			for(int j = 0; j < N; j++) {
				for(int k = 0; k < N; k++) {
					if(visited[j][k]) {
						visited[j][k] = false;
						continue;
					}
					if(A[j][k] > 1) {
						A[j][k]-=2;
						queue.add(new int [] {j, k});
					}
				}
			}
		}
		int cnt = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				cnt+=A[i][j];
			}
		}
		System.out.print(cnt);
	}
}