import java.util.*;
import java.io.*;

class Solution
{
    static int N;
    static int [] dy = {-1, 1, 0, 0};
    static int [] dx = {0, 0, -1, 1};
    static int [][]  A = new int [4001][4001];
    static Queue<Atom> queue = new LinkedList<>();
    static int energy;
	public static void main(String args[]) throws Exception
	{
		Scanner sc = new Scanner(System.in);
		int T;
		T=sc.nextInt();
		
		for(int test_case = 1; test_case <= T; test_case++)
		{
			N = sc.nextInt();
            energy = 0;
			for(int i = 0; i < N; i++) {
				int m = (sc.nextInt() + 1000) << 1;
				int n = 4000 - ((sc.nextInt() + 1000) << 1);
				int d = sc.nextInt();
				int e = sc.nextInt();
                A[n][m] = e;
				queue.add(new Atom(n, m, d, e));
			}
            BFS();
			System.out.println("#" + test_case + " " + energy);
		}
	}
    static void BFS(){
    	while(!queue.isEmpty()){
        	Atom now = queue.poll();
            if(A[now.n][now.m] != now.e && A[now.n][now.m] != 0){
            	energy+=A[now.n][now.m];
                A[now.n][now.m] = 0;
                continue;
            }
            int y = now.n+dy[now.d];
            int x = now.m+dx[now.d];
            if(y < 0 || y > 4000 || x < 0 || x > 4000) continue;
            if(A[y][x] == 0){
                A[y][x] = now.e;
            	queue.add(new Atom(y, x, now.d, now.e));
            }
            else{
            	A[y][x]+=now.e;
            }
            A[now.n][now.m] = 0;
        }
    }
    
    static class Atom{
		int n;
    	int m;
		int d;
		int e;
		public Atom(int n, int m, int d, int e) {
			this.n = n;
			this.m = m;
			this.d = d;
			this.e = e;
       }
	}
}