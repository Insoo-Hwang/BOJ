import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int [] A;
    static Yoot [] yoots;
    static int [] mal = new int[4];
    static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        A = new int[10];
        for(int i = 0; i < 10; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }
        yoots = new Yoot[33];
        for(int i = 0; i < 20; i++){
            yoots[i] = new Yoot(i*2, i+1, 0);
        }
        yoots[20] = new Yoot(40, 32, 0);
        yoots[21] = new Yoot(13, 22, 0);
        yoots[22] = new Yoot(16, 23, 0);
        yoots[23] = new Yoot(19, 24, 0);
        yoots[24] = new Yoot(25, 30, 0);
        yoots[25] = new Yoot(22, 26, 0);
        yoots[26] = new Yoot(24, 24, 0);
        yoots[27] = new Yoot(28, 28, 0);
        yoots[28] = new Yoot(27, 29, 0);
        yoots[29] = new Yoot(26, 24, 0);
        yoots[30] = new Yoot(30, 31, 0);
        yoots[31] = new Yoot(35, 20, 0);
        yoots[32] = new Yoot(0, -1, 0);
        yoots[5].jump = 21;
        yoots[10].jump = 25;
        yoots[15].jump = 27;

        DFS(0, 0);
        System.out.println(max);
    }

    static void DFS(int c, int s){
        if(c == 10){
            max = Math.max(max, s);
            return;
        }

        int dice = A[c];
        for(int i = 0; i < 4; i++){
            int prev = mal[i];
            if(prev == 32) continue;
            int now = move(prev, dice);
            if(check(now)){
                mal[i] = now;
                DFS(c+1, s+yoots[now].score);
                mal[i] = prev;
            }
        }
    }

    static int move(int o, int c){
        int temp = o;
        if(yoots[o].jump != 0){
            temp = yoots[o].jump;
            for(int i = 1; i < c; i++){
                temp = yoots[temp].next;
                if(temp == 32) break;
            }
        }
        else{
            for(int i = 0; i < c; i++){
                temp = yoots[temp].next;
                if(temp == 32) break;
            }
        }
        return temp;
    }

    static boolean check(int c){
        for(int i = 0; i < 4; i++){
            if(mal[i] == c && c != 32) return false;
        }
        return true;
    }

    static class Yoot{
        int score;
        int next;
        int jump;
        public Yoot(int score, int next, int jump){
            this.score = score;
            this.next = next;
            this.jump = jump;
        }
    }
}