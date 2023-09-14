import jdk.dynalink.beans.StaticClass;

import java.beans.Introspector;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static char [][] R = new char[3][3]; //빨간색
    static char [][] W = new char[3][3]; //흰색
    static char [][] O = new char[3][3]; //주황색
    static char [][] G = new char[3][3]; //초록색
    static char [][] B = new char[3][3]; //파란색
    static char [][] Y = new char[3][3]; //노란색
    static int [][] connect = new int[6][4]; //각 면의 연결 정보
    static List<Character> list; //한 면이 돌아갈 때의 옆면
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(st.nextToken());
        while(T-->0){
            fill(); //큐브 면과 각 면의 연결 정보를 채움
            int N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine(), " ");
            for(int i = 0; i < N; i++){
                String s = st.nextToken();
                rotate(s.charAt(0), s.charAt(1)=='+');
            }
            for(int i = 0; i < 3; i++){
                for(int j = 0; j < 3; j++){
                    sb.append(W[i][j]);
                }
                sb.append("\n");
            }
        }
        System.out.println(sb);
    }

    static void fill(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                R[i][j] = 'r';
                W[i][j] = 'w';
                O[i][j] = 'o';
                G[i][j] = 'g';
                B[i][j] = 'b';
                Y[i][j] = 'y';
            }
        }
        connect[0][0] = 1;
        connect[0][1] = 4;
        connect[0][2] = 5;
        connect[0][3] = 3;
        connect[1][0] = 2;
        connect[1][1] = 4;
        connect[1][2] = 0;
        connect[1][3] = 3;
        connect[2][0] = 5;
        connect[2][1] = 4;
        connect[2][2] = 1;
        connect[2][3] = 3;
        connect[3][0] = 0;
        connect[3][1] = 5;
        connect[3][2] = 2;
        connect[3][3] = 1;
        connect[4][0] = 0;
        connect[4][1] = 1;
        connect[4][2] = 2;
        connect[4][3] = 5;
        connect[5][0] = 0;
        connect[5][1] = 4;
        connect[5][2] = 2;
        connect[5][3] = 3;
    }

    static void rotate(char cube, boolean d){
        int c = -1;
        char [][] k = new char[3][3];
        list = new ArrayList<>();
        switch(cube){
            case 'F' : R = clock(R, d); c = 0; break; //선택된 면이 돌아가면 그 면 또한 회전함
            case 'U' : W = clock(W, d); c = 1; break;
            case 'D' : Y = clock(Y, d); c = 5; break;
            case 'B' : O = clock(O, d); c = 2; break;
            case 'L' : G = clock(G, d); c = 3; break;
            case 'R' : B = clock(B, d); c = 4; break;
        }
        for(int i = 0; i < 4; i++){
            int temp = connect[c][i]; //연결된 면
            char [][] t = new char[3][3];
            switch(temp){
                case 0 : t = R; break;
                case 1 : t = W; break;
                case 2 : t = O; break;
                case 3 : t = G; break;
                case 4 : t = B; break;
                case 5 : t = Y; break;
            }
            if(connect[temp][0] == c){ //연결된 면과 맞닿은 부분이 위인 경우(추가)
                TOP(t, true);
            }
            else if(connect[temp][1] == c){ //연결된 면과 맞닿은 부분이 오른쪽인 경우(추가)
                RIGHT(t, true);
            }
            else if(connect[temp][2] == c){ //연결된 면과 맞닿은 부분이 아래인 경우(추가)
                BOTTOM(t, true);
            }
            else{ //연결된 면과 맞닿은 부분이 왼쪽인 경우(추가)
                LEFT(t, true);
            }
        }

        if(d){ //시계방향
            for(int i = 0; i < 3; i++){ //큐브면이 3개로 이루어져있으므로 
                char temp = list.remove(list.size()-1);
                list.add(0, temp);
            }
        }
        else{ //반시계방향
            for(int i = 0; i < 3; i++){
                char temp = list.remove(0);
                list.add(list.size(), temp);
            }
        }
        for(int i = 0; i < 4; i++){
            int temp = connect[c][i];
            char [][] t = new char[3][3];
            switch(temp){
                case 0 : t = R; break;
                case 1 : t = W; break;
                case 2 : t = O; break;
                case 3 : t = G; break;
                case 4 : t = B; break;
                case 5 : t = Y; break;
            }
            if(connect[temp][0] == c){ //연결된 면과 맞닿은 부분이 위인 경우(제거)
                TOP(t, false);
            }
            else if(connect[temp][1] == c){ //연결된 면과 맞닿은 부분이 오른쪽인 경우(제거)
                RIGHT(t, false);
            }
            else if(connect[temp][2] == c){ //연결된 면과 맞닿은 부분이 아래인 경우(제거)
                BOTTOM(t, false);
            }
            else{ //연결된 면과 맞닿은 부분이 왼쪽인 경우(제거)
                LEFT(t, false);
            }
        }
    }

    static char[][] clock(char[][] k, boolean c){
        if(c){
            char tt = k[0][1];
            k[0][1] = k[1][0];
            k[1][0] = k[2][1];
            k[2][1] = k[1][2];
            k[1][2] = tt;
            tt = k[0][2];
            k[0][2] = k[0][0];
            k[0][0] = k[2][0];
            k[2][0] = k[2][2];
            k[2][2] = tt;
        }
        else{
            char tt = k[0][1];
            k[0][1] = k[1][2];
            k[1][2] = k[2][1];
            k[2][1] = k[1][0];
            k[1][0] = tt;
            tt = k[0][2];
            k[0][2] = k[2][2];
            k[2][2] = k[2][0];
            k[2][0] = k[0][0];
            k[0][0] = tt;
        }
        return k;
    }

    static void LEFT(char[][] t, boolean c){
        for (int j = 0; j < 3; j++) {
            if(c) list.add(t[j][0]);
            else t[j][0] = list.remove(0);
        }
    }

    static void RIGHT(char[][] t, boolean c){
        for (int j = 2; j >= 0; j--) {
            if(c) list.add(t[j][2]);
            else t[j][2] = list.remove(0);
        }
    }

    static void TOP(char[][] t, boolean c){
        for (int j = 2; j >= 0; j--) {
            if(c) list.add(t[0][j]);
            else t[0][j] = list.remove(0);
        }
    }

    static void BOTTOM(char[][] t, boolean c){
        for (int j = 0; j < 3; j++) {
            if(c) list.add(t[2][j]);
            else t[2][j] = list.remove(0);
        }
    }
}
