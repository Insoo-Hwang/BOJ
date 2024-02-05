import java.io.*;
import java.util.*;

public class Main {
    static int [][] A;
    static int [][] B;
    static int [][] match = {{0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5}, {1, 2}, {1, 3}, {1, 4}, {1, 5}, {2, 3}, {2, 4}, {2, 5}, {3, 4}, {3, 5}, {4, 5}};
    static boolean check;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringTokenizer st = new StringTokenizer(br.readLine());

        int T = 4;
        while(T-->0){
            A = new int[6][3];
            B = new int[6][3];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i < 6; i++){
                for(int j = 0; j < 3; j++){
                    A[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            check = false;
            DFS(0);
            if(check) System.out.print("1 ");
            else System.out.print("0 ");
        }
    }

    static void DFS(int d){
        if(d == 15){
            for(int i = 0; i < 6; i++){
                for(int j = 0; j < 3; j++){
                    if(A[i][j] != B[i][j]) return;
                }
            }
            check = true;
            return;
        }

        B[match[d][0]][0]++;
        B[match[d][1]][2]++;
        DFS(d+1);
        B[match[d][0]][0]--;
        B[match[d][1]][2]--;
        if(check) return;

        B[match[d][1]][0]++;
        B[match[d][0]][2]++;
        DFS(d+1);
        B[match[d][1]][0]--;
        B[match[d][0]][2]--;
        if(check) return;

        B[match[d][0]][1]++;
        B[match[d][1]][1]++;
        DFS(d+1);
        B[match[d][0]][1]--;
        B[match[d][1]][1]--;
    }
}