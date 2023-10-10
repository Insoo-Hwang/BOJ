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
            int [][] A = new int[N+2][N+2];
            int [][][] wormhole = new int [5][2][2];
            for(int i = 1; i < N+1; i++) {
                for(int j = 1; j < N+1; j++) {
                    A[i][j] = sc.nextInt();
                    if(A[i][j] > 5) {
                        if(wormhole[A[i][j]-6][0][0] != 0) {
                            wormhole[A[i][j]-6][1][0] = i;
                            wormhole[A[i][j]-6][1][1] = j;
                        }
                        else {
                            wormhole[A[i][j]-6][0][0] = i;
                            wormhole[A[i][j]-6][0][1] = j;
                        }
                    }
                }
            }

            int [] dy = {1, 0, -1, 0};
            int [] dx = {0, 1, 0, -1};
            int max = 0;
            for(int i = 1; i < N+1; i++) {
                for(int j = 1; j < N+1; j++) {
                    if(A[i][j] != 0) continue;
                    for(int k = 0; k < 4; k++) {
                        int d = k;
                        int y = i+dy[d];
                        int x = j+dx[d];
                        int score = 0;
                        while(true) {
                            while(y > 0 && y < N+1 && x > 0 && x < N+1 && A[y][x] == 0) {
                                if(A[y][x] == -1 || (y == i && x == j)) break;
                                y+=dy[d];
                                x+=dx[d];
                            }
                            if(A[y][x] == -1 || (y == i && x == j)) {
                                break;
                            }
                            else if(y == 0 || y == N+1 || x == 0 || x == N+1) {
                                d = (d+2)%4;
                                score++;
                                y+=dy[d];
                                x+=dx[d];
                            }
                            else if(A[y][x] < 6) {
                                if(A[y][x] == 1) {
                                    if(d == 0) d = 1;
                                    else if(d == 1) d = 3;
                                    else if(d == 2) d = 0;
                                    else if(d == 3) d = 2;
                                }
                                else if(A[y][x] == 2) {
                                    if(d == 0) d = 2;
                                    else if(d == 1) d = 3;
                                    else if(d == 2) d = 1;
                                    else if(d == 3) d = 0;
                                }
                                else if(A[y][x] == 3) {
                                    if(d == 0) d = 2;
                                    else if(d == 1) d = 0;
                                    else if(d == 2) d = 3;
                                    else if(d == 3) d = 1;
                                }
                                else if(A[y][x] == 4) {
                                    if(d == 0) d = 3;
                                    else if(d == 1) d = 2;
                                    else if(d == 2) d = 0;
                                    else if(d == 3) d = 1;
                                }
                                else if(A[y][x] == 5) {
                                    if(d == 0) d = 2;
                                    else if(d == 1) d = 3;
                                    else if(d == 2) d = 0;
                                    else if(d == 3) d = 1;
                                }
                                y+=dy[d];
                                x+=dx[d];
                                score++;
                            }
                            else {
                                int x1 = wormhole[A[y][x]-6][0][1];
                                int x2 = wormhole[A[y][x]-6][1][1];
                                int y1 = wormhole[A[y][x]-6][0][0];
                                int y2 = wormhole[A[y][x]-6][1][0];
                                if(y == y1 && x == x1) {
                                    y = y2;
                                    x = x2;
                                }
                                else {
                                    y = y1;
                                    x = x1;
                                }
                                y+=dy[d];
                                x+=dx[d];
                            }
                        }
                        max = Math.max(max, score);
                    }
                }
            }
            System.out.println("#" + test_case + " " + max);
        }
	}
}