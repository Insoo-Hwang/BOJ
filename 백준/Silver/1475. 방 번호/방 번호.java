import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringTokenizer st = new StringTokenizer(br.readLine());

        String N = br.readLine();
        int [] A = new int[10];
        for(char c : N.toCharArray()){
            int n = c-'0';
            if(n == 9) n = 6;
            A[n]++;
        }
        A[6] = A[6]/2+A[6]%2;
        Arrays.sort(A);
        System.out.println(A[9]);
    }
}