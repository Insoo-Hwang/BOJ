import java.util.*;
import java.io.*;

public class Solution {
    static int [] dy = {0, -1, 1, 0, 0};
    static int [] dx = {0, 0, 0, -1, 1};
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T;
        T=sc.nextInt();
        for(int test_case = 1; test_case <= T; test_case++)
        {
            int N = sc.nextInt();
            int M = sc.nextInt();
            int K = sc.nextInt();
            List<Mi> list = new LinkedList<>();
            for(int i = 0; i < K; i++) {
                int n = sc.nextInt();
                int m = sc.nextInt();
                int c = sc.nextInt();
                int d = sc.nextInt();
                list.add(new Mi(i*n+m, n, m, c, d));
            }
            for(int i = 0; i < M; i++){
                for(int j = 0; j < list.size(); j++){
                    Mi now = list.get(j);
                    now.n+=dy[now.d];
                    now.m+=dx[now.d];
                    now.num = now.n*N+now.m;
                    if(now.n == 0 || now.n == N-1 || now.m == 0 || now.m == N-1) {
                        now.c /= 2;
                        if(now.d == 1) now.d = 2;
                        else if(now.d == 2) now.d = 1;
                        else if(now.d == 3) now.d = 4;
                        else now.d = 3;
                        if (now.c == 0) {
                            list.remove(j);
                            j--;
                        }
                    }
                }
                Collections.sort(list);
                for(int j = 0; j < list.size()-1; j++){
                    Mi now = list.get(j);
                    Mi next = list.get(j+1);
                    if(now.n == next.n && now.m == next.m){
                        now.c+=next.c;
                        list.remove(j+1);
                        j--;
                    }
                }
            }
            int cnt = 0;
            for(Mi now : list){
                cnt+=now.c;
            }
            System.out.println("#" + test_case + " " + cnt);
        }
    }

    static class Mi implements Comparable<Mi>{
        int num;
        int n;
        int m;
        int c;
        int d;

        public Mi(int num, int n, int m, int c, int d) {
            this.num = num;
            this.n = n;
            this.m = m;
            this.c = c;
            this.d = d;
        }

        @Override
        public int compareTo(Mi o) {
            if(this.num == o.num) {
                return o.c-this.c;
            }
            return this.num-o.num;
        }
    }
}