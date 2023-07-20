import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args){
        
        boolean[] A = new boolean[10001];

        for(int i = 1; i < 10001; i++){
            int c = selfNum(i);
            if(c < 10001)
                A[c] = true;
        }

        for(int i = 1; i < 10001; i++){
            if(A[i])
                continue;
            System.out.println(i);
        }
    }

    private static int selfNum(int n){
        int temp = n;
        while (n > 0){
            temp+=n%10;
            n/=10;
        }
        return temp;
    }
}
