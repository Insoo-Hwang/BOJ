import java.util.*;
import java.io.*;

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
            int M = sc.nextInt();
            int K = sc.nextInt();
            int [][] A = new int [352][352];
            List<Cell> list = new ArrayList<>();
            for(int i = 176-(N/2); i < 176-(N/2)+N; i++){
            	for(int j = 176-(M/2); j < 176-(M/2)+M; j++){
                	A[i][j] = sc.nextInt();
                    list.add(new Cell(i, j, A[i][j], 0));
                }
            }
            Collections.sort(list);
            int [] dy = {-1, 1, 0, 0};
            int [] dx = {0, 0, -1, 1};
            for(int i = 1; i < K+1; i++){
                int [][] B = new int [352][352];
                for(int j = 0; j < 352; j++){
                	for(int k = 0; k < 352; k++){
                    	B[j][k] = A[j][k];
                    }
                } 
            	for(int j = 0; j < list.size(); j++){
                    Cell now = list.get(j);
                	if(now.t+now.start == i){
                    	for(int k = 0; k < 4; k++){
                        	int y = now.n+dy[k];
                            int x = now.m+dx[k];
                            if(B[y][x] > 0) continue;
                            list.add(new Cell(y, x, A[now.n][now.m], i+1));
                            B[y][x] = A[now.n][now.m];
                        }
                    }
                }                
                for(int j = 0; j < 352; j++){
                	for(int k = 0; k < 352; k++){
                    	A[j][k] = B[j][k];
                    }
                }
                Collections.sort(list);
            }
            int cnt = 0;
            for(Cell now : list){
            	if(now.start <= K && 2*now.t+now.start > K) cnt++;
            }
            System.out.println("#" + test_case + " " + cnt);
		}
	}
    
    static class Cell implements Comparable<Cell>{
        int n;
        int m;
    	int t;
        int start;
        public Cell(int n, int m, int t, int start){
            this.n = n;
            this.m = m;
        	this.t = t;
            this. start = start;
        }
        @Override
        public int compareTo(Cell o) {
            return o.t-this.t;
        }
    }
}