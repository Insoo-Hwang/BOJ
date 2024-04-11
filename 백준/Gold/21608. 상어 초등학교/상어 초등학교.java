import java.util.*;

public class Main {
	static int [] dy = {-1, 1, 0, 0};
	static int [] dx = {0, 0, -1, 1};
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int [][] A = new int [N*N+1][4];
		int [][] C = new int[N][N];
		PriorityQueue<Seat> queue = new PriorityQueue<>();
		for(int i = 1; i < N*N+1; i++) {
			int student = sc.nextInt();
			for(int j = 0; j < 4; j++) {
				int f = sc.nextInt();
				A[student][j] = f;
			}
			queue.clear();
			for(int j = 0; j < N; j++) {
				for(int k = 0; k < N; k++) {
					if(C[j][k] != 0) continue;
					int friend = 0;
					int empty = 0;
					for(int d = 0; d < 4; d++) {
						int y = j+dy[d];
						int x = k+dx[d];
						if(y > N-1 || y < 0 || x > N-1 || x < 0) continue;
						if(C[y][x] == 0) {
							empty++;
							continue;
						}
						for(int z = 0; z < 4; z++) {
							if(C[y][x] == A[student][z]) {
								friend++;
								break;
							}
						}
					}
					queue.add(new Seat(j, k, friend, empty));
				}
			}
			Seat now = queue.poll();
			C[now.r][now.c] = student; 
		}
		int ans = 0;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				int student = C[i][j];
				int cnt = 0;
				for(int k = 0; k < 4; k++) {
					int y = i+dy[k];
					int x = j+dx[k];
					if(y > N-1 || y < 0 || x > N-1 || x < 0) continue;
					int friend = C[y][x];
					for(int d = 0; d < 4; d++) {
						if(friend == A[student][d]) {
							cnt++;
							break;
						}
					}
				}
				if(cnt == 1) ans+=1;
				else if(cnt == 2) ans+=10;
				else if(cnt == 3) ans+=100;
				else if(cnt == 4) ans+=1000;
			}
		}
		System.out.print(ans);
	}
	
	static class Seat implements Comparable<Seat>{
		int r;
		int c;
		int friend;
		int empty;
		
		public Seat(int r, int c, int friend, int empty) {
			this.r = r;
			this.c = c;
			this.friend = friend;
			this.empty = empty;
		}
		
		@Override
		public int compareTo(Seat o) {
			if(this.friend == o.friend) {
				if(this.empty == o.empty) {
					if(this.r == o.r) {
						return this.c-o.c;
					}
					return this.r-o.r;
				}
				return o.empty-this.empty;
			}
			return o.friend-this.friend;
		}
	}
}