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
            int M = sc.nextInt();
            int A = sc.nextInt();
            int [][]move = new int[2][M];
            for(int i = 0; i < 2; i++) {
                for(int j = 0; j < M; j++) {
                    move[i][j] = sc.nextInt();
                }
            }
            Charge [][] map = new Charge[10][10];
            for(int i = 0; i < A; i++) {
                int m = sc.nextInt()-1;
                int n = sc.nextInt()-1;
                int c = sc.nextInt();
                int p = sc.nextInt();
                int y1 = n-c;
                if(y1 < 0) y1 = 0;
                int x1 = m-c;
                if(x1 < 0) x1 = 0;
                int y2 = n+c;
                if(y2 > 9) y2 = 9;
                int x2 = m+c;
                if(x2 > 9) x2 = 9;
                for(int j = y1; j <= y2; j++) {
                    for(int k = x1; k <= x2; k++) {
                        if(Math.abs(j-n)+Math.abs(k-m) > c) continue;
                        if(map[j][k]!=null && map[j][k].p1 < p) {
                            map[j][k].p2 = map[j][k].p1;
                            map[j][k].p1 = p;
                            map[j][k].b2 = map[j][k].b1;
                            map[j][k].b1 = i;
                        }
                        else if(map[j][k]!=null && map[j][k].p2 < p) {
                            map[j][k].p2 = p;
                            map[j][k].b2 = i;
                        }
                        else if(map[j][k]==null) {
                            map[j][k] = new Charge(i, -1, p, 0);
                        }
                    }
                }
            }
            int An = 0;
            int Am = 0;
            int Bn = 9;
            int Bm = 9;
            int []dy = {0, -1, 0, 1, 0};
            int []dx = {0, 0, 1, 0, -1};
            int charge = 0;
            if(map[An][Am]!=null) charge+=map[An][Am].p1;
            if(map[Bn][Bm]!=null) charge+=map[Bn][Bm].p1;
            for(int i = 0; i < M; i++) {
                An+=dy[move[0][i]];
                Am+=dx[move[0][i]];
                Bn+=dy[move[1][i]];
                Bm+=dx[move[1][i]];
                if(map[An][Am]!=null && map[Bn][Bm]!=null && map[An][Am].b1 == map[Bn][Bm].b1){
                    if(map[An][Am].b2 == map[Bn][Bm].b2){
                        charge+=(map[An][Am].p1+map[An][Am].p2);
                    }
                    else{
                        charge+=(map[An][Am].p1+Math.max(map[An][Am].p2, map[Bn][Bm].p2));
                    }
                }
                else {
                    if (map[An][Am] != null) charge += map[An][Am].p1;
                    if (map[Bn][Bm] != null) charge += map[Bn][Bm].p1;
                }
            }
            System.out.println("#" + test_case + " " + charge);
        }
	}
    
        static class Charge{
        int b1;
        int p1;
        int b2;
        int p2;

        public Charge(int b1, int b2, int p1, int p2) {
            this.b1 = b1;
            this.b2 = b2;
            this.p1 = p1;
            this.p2 = p2;
        }
    }
}