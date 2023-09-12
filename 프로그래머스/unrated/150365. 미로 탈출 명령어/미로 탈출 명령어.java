import java.util.*;
class Solution {
    static int N;
    static int M;
    static int startN;
    static int startM;
    static int endN;
    static int endM;
    static int K;
    static int [] dy = {1, 0, 0, -1};
    static int [] dx = {0, -1, 1, 0};
    static String [] ds = {"d", "l", "r", "u"};
    static String answer = "impossible";
    static boolean check = false;
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        N = n; M = m; startN = x; startM = y; endN = r; endM = c; K = k;
        
        DFS(startN, startM, 0, "");
        return answer;
    }
    
    static void DFS(int n, int m, int c, String s){
        if(c == K){
            if(n != endN || m != endM) return;
            answer = s;
            check = true;
            return;
        }
        for(int i = 0; i < 4; i++){
            int y = n + dy[i];
            int x = m + dx[i];
            if(y < 1 || y > N || x < 1 || x > N) continue;
            if(Math.abs(y-endN)+Math.abs(x-endM) > K-c-1) continue;
            if((K-c-1-Math.abs(y-endN)+Math.abs(x-endM))%2 == 1) continue;
            DFS(y, x, c+1, s+ds[i]);
            if(check) return;
        }
    }
}