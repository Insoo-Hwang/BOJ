import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringTokenizer st = new StringTokenizer(br.readLine());

        String N = br.readLine();
        int [] A = new int[N.length()];
        for(int i = 0; i < N.length(); i++){
            A[i] = N.charAt(i) - '0';
        }
        if(A[0] == 0){
            System.out.println(0);
            return;
        }

        int [][] D = new int[N.length()][2];
        D[0][0] = 1; D[0][1] = 0;
        for(int i = 1; i < N.length(); i++){
            if(A[i] != 0){
                D[i][0] = (D[i-1][0] + D[i-1][1])%1000000;
            }
            if(A[i-1]*10 + A[i] > 26){
                D[i][1] = 0;
            }
            else if(A[i-1]*10 + A[i] == 0){
                System.out.println(0);
                return;
            }
            else{
                D[i][1] = D[i-1][0];
            }
        }
        System.out.println((D[N.length()-1][0] + D[N.length()-1][1])%1000000);
    }
}