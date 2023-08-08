import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int count = 0;
        while(N > 0){
            if(N > 3){
                N-=3;
                count++;
            }
            else{
                N-=1;
                count++;
            }
        }
        if(count%2 == 1) System.out.println("SK");
        else System.out.println("CY");
    }
}