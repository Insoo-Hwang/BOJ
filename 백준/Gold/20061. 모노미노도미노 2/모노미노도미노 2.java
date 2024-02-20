import java.io.*;
import java.util.*;

public class Main {
    static boolean [][] A = new boolean[10][10];
    static int score = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            moveGreen(t, x, y);
            moveBlue(t, x, y);
            scoreGreen();
            scoreBlue();
            specialGreen();
            specialBlue();
        }
        int cnt = 0;
        for(int i = 4; i < 10; i++){
            for(int j = 0; j < 4; j++){
                if(A[i][j]) cnt++;
                if(A[j][i]) cnt++;
            }
        }
        System.out.println(score);
        System.out.println(cnt);
    }

    static void moveGreen(int t, int x, int y){
        if(t == 1){
            A[y][x] = true;
            for(int i = y; i < 9; i++){
                if(A[i+1][x]) break;
                A[i][x] = false;
                A[i+1][x] = true;
            }
        }
        else if(t == 2){
            A[y][x] = true;
            A[y+1][x] = true;
            for(int i = y+1; i < 9; i++){
                if(A[i+1][x]) break;
                A[i-1][x] = false;
                A[i+1][x] = true;
            }
        }
        else{
            A[y][x] = true;
            A[y][x+1] = true;
            for(int i = y; i < 9; i++){
                if(A[i+1][x] || A[i+1][x+1]) break;
                A[i][x] = false;
                A[i][x+1] = false;
                A[i+1][x] = true;
                A[i+1][x+1] = true;
            }
        }
    }

    static void moveBlue(int t, int x, int y){
        if(t == 1){
            A[y][x] = true;
            for(int i = x; i < 9; i++){
                if(A[y][i+1]) break;
                A[y][i] = false;
                A[y][i+1] = true;
            }
        }
        else if(t == 2){
            A[y][x] = true;
            A[y+1][x] = true;
            for(int i = x; i < 9; i++){
                if(A[y][i+1] || A[y+1][i+1]) break;
                A[y][i] = false;
                A[y+1][i] = false;
                A[y][i+1] = true;
                A[y+1][i+1] = true;
            }
        }
        else if(t == 3){
            A[y][x] = true;
            A[y][x+1] = true;
            for(int i = x+1; i < 9; i++){
                if(A[y][i+1]) break;
                A[y][i-1] = false;
                A[y][i+1] = true;
            }
        }
    }

    static void scoreGreen(){
        for(int i = 6; i < 10; i++){
            boolean check = true;
            for(int j = 0; j < 4; j++){
                if(!A[i][j]){
                    check = false;
                    break;
                }
            }
            if(!check) continue;
            for(int j = 0; j < 4; j++) A[i][j] = false;
            score++;
            for(int j = i-1; j > 3; j--){
                for(int k = 0; k < 4; k++){
                    A[j+1][k] = A[j][k];
                    A[j][k] = false;
                }
            }
        }
    }

    static void scoreBlue(){
        for(int i = 6; i < 10; i++){
            boolean check = true;
            for(int j = 0; j < 4; j++){
                if(!A[j][i]){
                    check = false;
                    break;
                }
            }
            if(!check) continue;
            for(int j = 0; j < 4; j++) A[j][i] = false;
            score++;
            for(int j = i-1; j > 3; j--){
                for(int k = 0; k < 4; k++){
                    A[k][j+1] = A[k][j];
                    A[k][j] = false;
                }
            }
        }
    }

    static void specialGreen(){
        int cnt = 0;
        for(int i = 5; i > 3; i--){
            for(int j = 0; j < 4; j++){
                if(A[i][j]){
                    cnt++;
                    break;
                }
            }
        }
        if(cnt == 0) return;
        for(int i = 9-cnt; i > 5-cnt; i--){
            for(int j = 0; j < 4; j++){
                A[i+cnt][j] = A[i][j];
                A[i][j] = false;
            }
        }
    }

    static void specialBlue(){
        int cnt = 0;
        for(int i = 5; i > 3; i--){
            for(int j = 0; j < 4; j++){
                if(A[j][i]){
                    cnt++;
                    break;
                }
            }
        }
        if(cnt == 0) return;
        for(int i = 9-cnt; i > 5-cnt; i--){
            for(int j = 0; j < 4; j++){
                A[j][i+cnt] = A[j][i];
                A[j][i] = false;
            }
        }
    }
}