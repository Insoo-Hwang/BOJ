import java.io.*;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int R = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        char [][] A = new char[R][C];
        for(int i = 0; i < R; i++){
            String s = br.readLine();
            for(int j = 0; j < C; j++){
                A[i][j] = s.charAt(j);
            }
        }

        for(int i = 0; i < R; i++){
            for(int j = 0; j < C; j++){
                if(A[i][j] == '.'){
                    boolean up = false;
                    boolean down = false;
                    boolean left = false;
                    boolean right = false;
                    if(i-1 > -1){
                        char Aup = A[i-1][j];
                        if(Aup == '|' || Aup == '+' || Aup == '1' || Aup == '4') up = true;
                    }
                    if(i+1 < R){
                        char Adown = A[i+1][j];
                        if(Adown == '|' || Adown == '+' || Adown == '2' || Adown == '3') down = true;
                    }
                    if(j-1 > -1){
                        char Aleft = A[i][j-1];
                        if(Aleft == '-' || Aleft == '+' || Aleft == '1' || Aleft == '2') left = true;
                    }
                    if(j+1 < C){
                        char Aright = A[i][j+1];
                        if(Aright == '-' || Aright == '+' || Aright == '3' || Aright == '4') right = true;
                    }


                    if(up && down && left && right){
                        i++;
                        j++;
                        System.out.println(i + " " + j + " +");
                        return;
                    }
                    if(up && down){
                        i++;
                        j++;
                        System.out.println(i + " " + j + " |");
                        return;
                    }
                    if(left && right){
                        i++;
                        j++;
                        System.out.println(i + " " + j + " -");
                        return;
                    }
                    if(down && right){
                        i++;
                        j++;
                        System.out.println(i + " " + j + " 1");
                        return;
                    }
                    if(up && right){
                        i++;
                        j++;
                        System.out.println(i + " " + j + " 2");
                        return;
                    }
                    if(left && up){
                        i++;
                        j++;
                        System.out.println(i + " " + j + " 3");
                        return;
                    }
                    if(left && down){
                        i++;
                        j++;
                        System.out.println(i + " " + j + " 4");
                        return;
                    }
                }
            }
        }
    }
}