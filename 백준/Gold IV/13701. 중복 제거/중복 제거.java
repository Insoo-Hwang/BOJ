import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        BitSet bitSet = new BitSet(1<<25);
        String [] s = br.readLine().split(" ");
        for(int i = 0; i < s.length; i++){
            if(bitSet.get(Integer.parseInt(s[i]))) continue;
            bitSet.set(Integer.parseInt(s[i]));
            sb.append(Integer.parseInt(s[i])).append(" ");
        }
        System.out.println(sb);
    }
}