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
        yoots[15].jump = 27; //맵

        DFS(0, 0);
        System.out.println(max);
    }

    static void DFS(int c, int s){ //c는 주사위를 굴릴 횟수, s는 얻을 수 있는 최대 점수
        if(c == 10){
            max = Math.max(max, s);
            return;
        }

        int dice = A[c]; //주사위를 굴렸을 때 나올 숫자
        for(int i = 0; i < 4; i++){ //말 4개를 움직일 수 있음
            int prev = mal[i]; //원래 말의 위치
            if(prev == 32) continue; //말이 결승점에 도달하면 움직이지 않음
            int now = move(prev, dice); //말이 주사위 숫자만큼 움직일시의 위치
            if(check(now)){ //현재 위치에 말을 놓을 수 있으면
                mal[i] = now;
                DFS(c+1, s+yoots[now].score); //계속 진행
                mal[i] = prev; //백트래킹
            }
        }
    }

    static int move(int o, int c){ //o는 현재 위치, c는 주사위 숫자
        int temp = o;
        if(yoots[o].jump != 0){ //파란 화살표로 이동 가능하면 이동
            temp = yoots[o].jump;
            for(int i = 1; i < c; i++){
                temp = yoots[temp].next;
                if(temp == 32) break; //결승점 도달시 더 이상 이동 x
            }
        }
        else{ //빨간 화살표로 이동
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
        int score; //점수
        int next; //다음 위치
        int jump; //이 자리에 위치한 경우 이동할 수 있는 다음 위치(파란 화살표)
        public Yoot(int score, int next, int jump){
            this.score = score;
            this.next = next;
            this.jump = jump;
        }
    }
}
